package org.example.model.jadwal;

import java.util.Scanner;

import org.example.interfaces.AktivitasHarian;
import org.example.model.latihan.CatatanLatihan;
import org.example.model.latihan.Latihan;
import org.example.model.pengguna.Pengguna;
import org.example.model.program.ProgramLatihan;

import java.util.Date;
import java.util.Scanner;


public class Haribertarung implements AktivitasHarian{
    private final ProgramLatihan programLatihanYangDilakukan;
    private final int hariKe;

    public HariBertarung(ProgramLatihan program, int hariKe) {
        this.programLatihanYangDilakukan = program;
        this.hariKe = hariKe;
    }

    @Override
    public void lakukanAktivitas(Pengguna pengguna) {
        System.out.println("==========================================");
        System.out.println("🔥 HARI BERTARUNG! 🔥 - Hari ke-" + this.hariKe);
        System.out.println("Program: " + programLatihanYangDilakukan.getNamaProgram());
        System.out.println("==========================================");

        Scanner scanner = new Scanner(System.in);

        for (Latihan latihan : programLatihanYangDilakukan.getListLatihan()) {
            System.out.println("\nLatihan berikutnya: " + latihan.getNamaLatihan());
            System.out.println("Target: " + latihan.getSet() + " set x " + latihan.getRep() + " rep");
            System.out.print("Tekan ENTER untuk memulai dan menyelesaikan latihan ini...");
            scanner.nextLine();

            System.out.println("Latihan " + latihan.getNamaLatihan() + " selesai!");

            CatatanLatihan catatan = new CatatanLatihan(
                new Date(), 
                programLatihanYangDilakukan.getNamaProgram(),
                latihan.getNamaLatihan(),
                latihan.getSet(),
                latihan.getRep(),
                this.hariKe,
                latihan.getTingkatKesulitan()
            );

            pengguna.getRiwayat().tambahkanCatatan(catatan);
            pengguna.addExp(50); 
        }

        System.out.println("\n🎉 Sesi latihan untuk hari ini telah selesai! Kerja bagus!");
        System.out.println("==========================================");
    }
}
