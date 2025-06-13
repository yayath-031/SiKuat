package latihan;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pengguna.AktivitasHarian;
import pengguna.Pengguna;


public class SiklusMingguan {
    private List<AktivitasHarian> jadwalHarian;
    private int hariSaatIniIndex;

    public SiklusMingguan() {
        this.jadwalHarian = new ArrayList<>();
        this.hariSaatIniIndex = 0;
    }

    public void aturJadwalMingguan(List<AktivitasHarian> jadwal) {
        this.jadwalHarian = jadwal;
    }
    
    public void jalankanAktivitasHariIni(Pengguna pengguna, Scanner scanner) {
        if (!isSelesai()) {
            getHariSaatIni().lakukanAktivitas(pengguna, scanner);
        } else {
            System.out.println("Siklus mingguan telah selesai!");
        }
    }

    public void majuKeHariBerikutnya() {
        if (!isSelesai()) {
            this.hariSaatIniIndex++;
        }
    }

    public AktivitasHarian getHariSaatIni() {
        return jadwalHarian.get(hariSaatIniIndex);
    }

    public int getHariSaatIniIndex() {
        return hariSaatIniIndex;
    }
    
    public boolean isSelesai() {
        return hariSaatIniIndex >= jadwalHarian.size();
    }
}