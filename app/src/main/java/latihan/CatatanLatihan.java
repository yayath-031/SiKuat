package latihan;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CatatanLatihan {
    private final Date tanggal;
    private final String namaPengguna;
    private final String daftarLatihan;
    private final int hariKe;
    private final String namaProgram;

    public CatatanLatihan(String namaPengguna, String daftarLatihan, int hariKe, String namaProgram) {
        this.tanggal = new Date();
        this.namaPengguna = namaPengguna;
        this.daftarLatihan = daftarLatihan;
        this.hariKe = hariKe;
        this.namaProgram = namaProgram;
    }

    public void tampilkan() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        System.out.printf("| %-10s | %-70s | Hari ke-%-2d | %s\n",
            namaPengguna, daftarLatihan, hariKe, namaProgram);
    }
}