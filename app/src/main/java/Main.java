
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import latihan.FullBody;
import latihan.HariIstirahat;
import latihan.LowerBody;
import latihan.OtotKhusus;
import latihan.ProgramLatihan;
import latihan.SiklusMingguan;
import latihan.UpperBody;
import pengguna.AktivitasHarian;
import pengguna.Pengguna;
import util.ManajemenData;

public class Main {
    // DIUBAH: Tidak lagi diinisialisasi langsung, tapi akan diisi dari file JSON
    private static Map<String, Pengguna> basisDataPengguna;
    private static Pengguna penggunaSaatIni = null;

    public static void main(String[] args) {
        // DIUBAH: Memuat data dari file JSON saat aplikasi pertama kali dijalankan
        basisDataPengguna = ManajemenData.muatDataPengguna();
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("==============================================");
        System.out.println("   SELAMAT DATANG DI APLIKASI FITNES 270T");
        System.out.println("==============================================");
        
        while (true) {
            if (penggunaSaatIni == null) {
                tampilkanMenuAwal(scanner);
            } else {
                tampilkanMenuUtama(scanner);
            }
        }
    }

    private static void tampilkanMenuAwal(Scanner scanner) {
        System.out.println("\n--- HALAMAN PERTAMA ---");
        System.out.println("1. Login\n2. Registrasi\n3. Exit");
        System.out.print("Pilihan Anda: ");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1": 
                prosesLogin(scanner); 
                break;
            case "2": 
                prosesRegistrasi(scanner); 
                break;
            case "3": 
                // DIUBAH: Menyimpan data sebelum keluar aplikasi
                ManajemenData.simpanDataPengguna(basisDataPengguna);
                System.out.println("Terima kasih. Data telah disimpan."); 
                System.exit(0); 
                break;
            default: 
                System.out.println("Pilihan tidak valid."); 
                break;
        }
    }
    
    private static void prosesLogin(Scanner scanner) {
        System.out.println("\n--- LOGIN ---");
        System.out.print("Masukkan username: ");
        String username = scanner.nextLine();
        System.out.print("Masukkan password: ");
        String password = scanner.nextLine();
        Pengguna pengguna = basisDataPengguna.get(username);
        if (pengguna != null && pengguna.getPassword().equals(password)) {
            penggunaSaatIni = pengguna;
            penggunaSaatIni.login();
        } else {
            System.out.println("Login gagal! Username atau password salah.");
        }
    }

    private static void prosesRegistrasi(Scanner scanner) {
        System.out.println("\n--- REGISTRASI AKUN BARU ---");
        System.out.print("Buat username: ");
        String username = scanner.nextLine();
        if (basisDataPengguna.containsKey(username)) {
            System.out.println("Registrasi gagal! Username '" + username + "' sudah digunakan.");
            return;
        }
        System.out.print("Buat password (minimal 8 karakter, harus ada huruf dan angka): ");
        String password = scanner.nextLine();
        if (!isPasswordValid(password)) {
            System.out.println("Registrasi dibatalkan.");
            return;
        }
        Pengguna penggunaBaru = new Pengguna(username, password);
        basisDataPengguna.put(username, penggunaBaru);
        penggunaBaru.registrasi();
        System.out.println("Registrasi berhasil! Silakan login dengan akun baru Anda.");
    }

    private static void tampilkanMenuUtama(Scanner scanner) {
        SiklusMingguan siklusSaatIni = penggunaSaatIni.getSiklusSaatIni();
        System.out.println("\n--- MENU UTAMA ---");
        System.out.println("Halo, " + penggunaSaatIni.getNama() + "!");
        System.out.println("1. Tampilkan Profile");
        if (siklusSaatIni == null) {
            System.out.println("2. Buat Program Latihan");
        } else {
            System.out.println("2. Lanjutkan Program (" + (siklusSaatIni.isSelesai() ? "Selesai" : "Hari ke-" + (siklusSaatIni.getHariSaatIniIndex() + 1)) + ")");
        }
        System.out.println("3. Tampilkan Riwayat Latihan");
        System.out.println("4. Logout");
        System.out.println("5. Exit");
        System.out.print("Pilihan Anda: ");
        String pilihan = scanner.nextLine();
        
        switch (pilihan) {
            case "1": 
                penggunaSaatIni.tampilkanProfil(); 
                break;
            case "2":
                if (siklusSaatIni == null) {
                    ProgramLatihan programPilihan = buatProgramBaru(scanner);
                    if (programPilihan != null) {
                        siklusSaatIni = programPilihan.getSiklusMingguan();
                        penggunaSaatIni.setSiklusSaatIni(siklusSaatIni);
                        System.out.println("\nProgram latihan '" + programPilihan.getNamaProgram() + "' telah berhasil dibuat!");
                        tampilkanHalamanJadwal(scanner, true, siklusSaatIni);
                        siklusSaatIni.jalankanAktivitasHariIni(penggunaSaatIni, scanner);
                        siklusSaatIni.majuKeHariBerikutnya();
                    }
                } else {
                    if (siklusSaatIni.isSelesai()) {
                        System.out.println("Anda telah menyelesaikan siklus mingguan ini! Program akan direset.");
                        penggunaSaatIni.setSiklusSaatIni(null);
                        break;
                    }
                    AktivitasHarian aktivitasHariIni = siklusSaatIni.getHariSaatIni();
                    if (aktivitasHariIni instanceof HariIstirahat) {
                        System.out.print("\nIni adalah hari istirahat (kuis). Mau mengerjakan? (y/n): ");
                        if (scanner.nextLine().equalsIgnoreCase("n")) {
                            System.out.println("Hari istirahat dilewati. Kembali ke menu utama.");
                            siklusSaatIni.majuKeHariBerikutnya();
                            break;
                        }
                    }
                    tampilkanHalamanJadwal(scanner, false, siklusSaatIni);
                    siklusSaatIni.jalankanAktivitasHariIni(penggunaSaatIni, scanner);
                    siklusSaatIni.majuKeHariBerikutnya();
                }
                break;
            case "3": 
                penggunaSaatIni.getRiwayat().tampilkanSemuaRiwayat(); 
                break;
            case "4":
                // DIUBAH: Menyimpan data saat logout untuk keamanan
                ManajemenData.simpanDataPengguna(basisDataPengguna);
                penggunaSaatIni = null; 
                System.out.println("Anda telah logout. Data disimpan."); 
                break;
            case "5": 
                // DIUBAH: Menyimpan data sebelum keluar aplikasi
                ManajemenData.simpanDataPengguna(basisDataPengguna);
                penggunaSaatIni.keluarAplikasi(); 
                System.exit(0); 
                break;
            default: 
                System.out.println("Pilihan tidak valid."); 
                break;
        }
    }

    private static ProgramLatihan buatProgramBaru(Scanner scanner) {
        System.out.println("\n--- BUAT PROGRAM BARU ---");
        System.out.println("1. Otot Khusus\n2. Upper Body Program\n3. Lower Body Program\n4. Full Body Program\n5. Kembali");
        System.out.print("Pilihan Anda: ");
        String pilihan = scanner.nextLine();
        switch (pilihan) {
            case "1": return pilihOtotKhusus(scanner);
            case "2": return new UpperBody();
            case "3": return new LowerBody();
            case "4": return new FullBody();
            case "5": return null;
            default: System.out.println("Pilihan tidak valid."); return null;
        }
    }
    
    private static ProgramLatihan pilihOtotKhusus(Scanner scanner) {
        System.out.println("\n--- PILIH OTOT ---");
        System.out.println("1. Dada\n2. Punggung\n3. Bisep\n4. Trisep\n5. Bahu\n6. Perut\n7. Kaki\n8. Lengan\n9. Kembali");
        System.out.print("Pilihan Anda: ");
        String pilihanOtot = scanner.nextLine();
        switch (pilihanOtot) {
            case "1": return new OtotKhusus("Dada");
            case "2": return new OtotKhusus("Punggung");
            case "3": return new OtotKhusus("Bisep");
            case "4": return new OtotKhusus("Trisep");
            case "5": return new OtotKhusus("Bahu");
            case "6": return new OtotKhusus("Perut");
            case "7": return new OtotKhusus("Kaki");
            case "8": return new OtotKhusus("Lengan");
            case "9": return null;
            default: System.out.println("Pilihan tidak valid."); return null;
        }
    }

    private static void tampilkanHalamanJadwal(Scanner scanner, boolean isProgramBaru, SiklusMingguan siklus) {
        System.out.println("\n--- JADWAL HARIAN ANDA ---");
        System.out.println("HARI 1: Bertarung\nHARI 2: Istirahat\nHARI 3: Bertarung\nHARI 4: Istirahat\nHARI 5: Bertarung\nHARI 6: Istirahat\nHARI 7: Bertarung");
        System.out.println("--------------------------");
        if (isProgramBaru) {
            System.out.print("Tekan Enter untuk memulai HARI 1...");
        } else {
            System.out.print("Tekan Enter untuk melanjutkan ke HARI " + (siklus.getHariSaatIniIndex() + 1) + "...");
        }
        scanner.nextLine();
    }
    
    private static boolean isPasswordValid(String password) {
        if (password.length() < 8) {
            System.out.println("Password gagal! Minimal harus 8 karakter.");
            return false;
        }
        if (!Pattern.compile(".*[a-zA-Z].*").matcher(password).matches()) {
            System.out.println("Password gagal! Harus mengandung setidaknya satu huruf.");
            return false;
        }
        if (!Pattern.compile(".*[0-9].*").matcher(password).matches()) {
            System.out.println("Password gagal! Harus mengandung setidaknya satu angka.");
            return false;
        }
        return true;
    }
}