package org.example.model.jadwal;

import java.util.ArrayList;
import java.util.List;

import org.example.interfaces.AktivitasHarian;
import org.example.model.pengguna.Pengguna;
import org.example.model.program.ProgramLatihan;

public class SiklusMingguan {
    private List<AktivitasHarian> jadwalHarian;
    private int hariSaatIniIndex; 

    public SiklusMingguan() {
        this.jadwalHarian = new ArrayList<>();
        this.hariSaatIniIndex = 0;
    }

    public void aturJadwalMingguan(ProgramLatihan program) {
        this.jadwalHarian.clear(); 

        this.jadwalHarian.add(new HariBertarung(program, 1)); 
        this.jadwalHarian.add(new HariIstirahat(2));           
        this.jadwalHarian.add(new HariBertarung(program, 3)); 
        this.jadwalHarian.add(new HariIstirahat(4));           
        this.jadwalHarian.add(new HariBertarung(program, 5)); 
        this.jadwalHarian.add(new HariIstirahat(6));           
        this.jadwalHarian.add(new Haribertarung(program, 7));           

        this.hariSaatIniIndex = 0; 
        System.out.println("Jadwal mingguan untuk program '" + program.getNamaProgram() + "' telah berhasil dibuat.");
    }

    public void jalankanHariBerikutnya(Pengguna pengguna) {
        if (jadwalHarian.isEmpty()) {
            System.out.println("Jadwal belum diatur! Silakan buat program latihan terlebih dahulu.");
            return;
        }

        AktivitasHarian hariIni = getHariSaatIni();

        hariIni.lakukanAktivitas(pengguna);

        this.hariSaatIniIndex = (this.hariSaatIniIndex + 1) % 7;
    }

    public AktivitasHarian getHariSaatIni() {
        return jadwalHarian.get(hariSaatIniIndex);
    }
    
    public int getHariSaatIniIndex() {
        return hariSaatIniIndex;
    }
}
