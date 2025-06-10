package org.example.model.kuis;

import java.util.List;
import java.util.Scanner;

public class SesiSoal {
    private final List<Soal> daftarSoal;
    private int skor;
    private final Scanner scanner;

    public SesiSoal(List<Soal> soalList) {
        this.daftarSoal = soalList;
        this.skor = 0;
        this.scanner = new Scanner(System.in);
    }

    public void mulaiSesi() {
        if (daftarSoal == null || daftarSoal.isEmpty()) {
            System.out.println("Tidak ada soal yang tersedia untuk sesi ini.");
            return;
        }

        System.out.println("\n--- Kuis Pengetahuan Dimulai! ---");
        for (int i = 0; i < daftarSoal.size(); i++) {
            Soal soalSaatIni = daftarSoal.get(i);
            System.out.println("\nSoal " + (i + 1) + ": " + soalSaatIni.getPertanyaan());

            // Tampilkan pilihan jawaban
            for (String pilihan : soalSaatIni.getPilihanJawaban()) {
                System.out.println(pilihan);
            }

            System.out.print("Jawaban Anda: ");
            String jawaban = scanner.nextLine();

            if (soalSaatIni.cekJawaban(jawaban)) {
                System.out.println("✅ Benar!");
                this.skor++;
            } else {
                System.out.println("❌ Salah.");
            }
        }
    }

    public void tampilkanHasil() {
        System.out.println("\n--- Kuis Selesai ---");
        System.out.println("Skor akhir Anda: " + this.skor + " dari " + daftarSoal.size() + " soal.");
    }

    public int getSkor() {
        return skor;
    }
}
