package org.example.model.jadwal;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.example.interfaces.AktivitasHarian;
import org.example.model.kuis.SesiSoal;
import org.example.model.kuis.Soal;
import org.example.model.pengguna.Pengguna;

public class HariIstirahat implements AktivitasHarian{

    private final int hariKe;

    public HariIstirahat(int hariKe) {
        this.hariKe = hariKe;
    }

    @Override
    public void lakukanAktivitas(Pengguna pengguna) {
        System.out.println("==========================================");
        System.out.println("😴 HARI ISTIRAHAT 😴 - Hari ke-" + this.hariKe);
        System.out.println("Gunakan hari ini untuk pemulihan. Otot tumbuh saat Anda beristirahat.");
        System.out.println("==========================================");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingin mengikuti kuis pengetahuan kebugaran untuk bonus EXP? (y/n): ");
        String pilihan = scanner.nextLine();

        if (pilihan.equalsIgnoreCase("y")) {
            List<Soal> soalList = Arrays.asList(
                new Soal("Otot apa yang menjadi target utama dari latihan Bench Press?",
                         Arrays.asList("1. Pectoralis Major", "2. Biceps", "3. Quadriceps"), "1"),
                new Soal("Apa fungsi utama dari otot Latissimus Dorsi?",
                         Arrays.asList("1. Mendorong", "2. Menarik", "3. Mengangkat kaki"), "2")
            );

            SesiSoal sesiKuis = new SesiSoal(soalList);
            sesiKuis.mulaiSesi();
            sesiKuis.tampilkanHasil();

            int skor = sesiKuis.getSkor();
            int expBonus = skor * 25; 
            if (expBonus > 0) {
                System.out.println("Anda mendapatkan bonus " + expBonus + " EXP dari kuis!");
                pengguna.addExp(expBonus);
            }
        } else {
            System.out.println("Baiklah, selamat beristirahat!");
        }
        System.out.println("==========================================");
    }
}
