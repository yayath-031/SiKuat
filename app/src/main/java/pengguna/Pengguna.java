package pengguna;
import latihan.RiwayatLatihan;
import latihan.SiklusMingguan;


public class Pengguna {
    private String username;
    private String password;
    private String nama;
    private int exp;
    private int level;
    private String badge;
    private RiwayatLatihan riwayat;
    private SiklusMingguan siklusSaatIni;

    public Pengguna(String username, String password) {
        this.username = username;
        this.password = password;
        this.nama = username;
        this.exp = 0;
        this.level = 1;
        this.badge = "Pemula";
        this.riwayat = new RiwayatLatihan();
        this.siklusSaatIni = null;
    }
    
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNama() { return nama; }
    public RiwayatLatihan getRiwayat() { return riwayat; }
    public SiklusMingguan getSiklusSaatIni() { return siklusSaatIni; }
    public void setSiklusSaatIni(SiklusMingguan siklus) { this.siklusSaatIni = siklus; }
    
    public void addExp(int jumlah) {
        this.exp += jumlah;
        System.out.println("\nSelamat, Anda mendapatkan " + jumlah + " EXP!");
        checkLevelUp();
    }

    public void checkLevelUp() {
        int expDibutuhkan = level * 100;
        if (this.exp >= expDibutuhkan) {
            this.level++;
            this.exp -= expDibutuhkan;
            System.out.println(">>> LEVEL UP! Anda sekarang Level " + this.level + " <<<");
        }
    }

    public void tampilkanProfil() {
        System.out.println("\n--- PROFIL PENGGUNA ---");
        System.out.println("Nama   : " + getNama());
        System.out.println("Level  : " + this.level);
        System.out.println("EXP    : " + this.exp + " / " + (this.level * 100));
        System.out.println("Badge  : " + this.badge);
        System.out.println("-----------------------");
    }

    public void login() { System.out.println("Pengguna '" + username + "' berhasil login."); }
    public void registrasi() { System.out.println("Pengguna '" + username + "' berhasil diregistrasi."); }
    public void keluarAplikasi() { System.out.println("Terima kasih, " + nama + "! Sampai jumpa lagi."); }
}