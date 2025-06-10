package org.example.model.latihan;

public class Latihan {
    private final String namaLatihan;
    private final int set;
    private final int rep;
    private final String tingkatKesulitan;

    public Latihan(String namaLatihan, int set, int rep, String tingkatKesulitan) {
        this.namaLatihan = namaLatihan;
        this.set = set;
        this.rep = rep;
        this.tingkatKesulitan = tingkatKesulitan;
    }

    public String getNamaLatihan() {
        return namaLatihan;
    }

    public int getSet() {
        return set;
    }

    public int getRep() {
        return rep;
    }

    public String getTingkatKesulitan() {
        return tingkatKesulitan;
    }

    public void tampilkanInfo() {
        System.out.println("Latihan: " + namaLatihan + " | Target: " + set + " set x " + rep + " rep | Kesulitan: " + tingkatKesulitan);
    }
}
