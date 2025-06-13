package latihan;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import pengguna.AktivitasHarian;
import pengguna.Pengguna;



public class HariBertarung implements AktivitasHarian {
    private int hariKe;
    private List<Latihan> daftarLatihanHarian;
    private String namaProgram;

    public HariBertarung(int hariKe, List<Latihan> daftarLatihanHarian, String namaProgram) {
        this.hariKe = hariKe;
        this.daftarLatihanHarian = daftarLatihanHarian;
        this.namaProgram = namaProgram;
    }

    @Override
    public void lakukanAktivitas(Pengguna pengguna, Scanner scanner) {
        System.out.println("\n--- HARI KE-" + hariKe + ": HARI BERTARUNG ---");
        int totalExpDidapat = 0;
        for (Latihan latihan : daftarLatihanHarian) {
            latihan.doLatihan();
            totalExpDidapat += latihan.getExp();
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.print("Tekan Enter untuk menyelesaikan latihan dan menyimpan riwayat...");
        scanner.nextLine();

        String latihanTercatat = daftarLatihanHarian.stream()
                                    .map(Latihan::getNamaLatihan)
                                    .collect(Collectors.joining(", "));
        
        CatatanLatihan catatanBaru = new CatatanLatihan(pengguna.getNama(), latihanTercatat, this.hariKe, this.namaProgram);
        pengguna.getRiwayat().tambahkanCatatan(catatanBaru);

        pengguna.addExp(totalExpDidapat);
        System.out.println("Latihan hari ini selesai dan berhasil dicatat di riwayat!");
    }
}