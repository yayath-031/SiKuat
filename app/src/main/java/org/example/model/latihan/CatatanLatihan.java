package org.example.model.latihan;

import java.text.SimpleDateFormat;

public class CatatanLatihan {
    private final Date tanggalLatihan;
    private final String namaProgram;
    private final String namaLatihan;
    private final int set;
    private final int rep;
    private final int hariKe;
    private final String tingkatKesulitan;

    public CatatanLatihan(Date tanggalLatihan, String namaProgram, String namaLatihan, int set, int rep, int hariKe, String tingkatKesulitan) {
        this.tanggalLatihan = tanggalLatihan;
        this.namaProgram = namaProgram;
        this.namaLatihan = namaLatihan;
        this.set = set;
        this.rep = rep;
        this.hariKe = hariKe;
        this.tingkatKesulitan = tingkatKesulitan;
    }

    public Date getTanggalLatihan() { return tanggalLatihan; }
    public String getNamaProgram() { return namaProgram; }
    public String getNamaLatihan() { return namaLatihan; }
    public int getSet() { return set; }
    public int getRep() { return rep; }
    public int getHariKe() { return hariKe; }
    public String getTingkatKesulitan() { return tingkatKesulitan; }

    public void tampilkanDetail() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String tanggalFormatted = formatter.format(tanggalLatihan);

        System.out.println(
            "[" + tanggalFormatted + "] " +
            "Program: " + namaProgram + " (Hari ke-" + hariKe + ") - " +
            "Latihan: " + namaLatihan + " (" + set + " set x " + rep + " rep)"
        );
    }
}
