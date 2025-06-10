package org.example.model.kuis;

import java.util.List;

public class Soal {
    private final String pertanyaan;
    private final List<String> pilihanJawaban;
    private final String jawabanBenar; 

    public Soal(String pertanyaan, List<String> pilihanJawaban, String jawabanBenar) {
        this.pertanyaan = pertanyaan;
        this.pilihanJawaban = pilihanJawaban;
        this.jawabanBenar = jawabanBenar;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public List<String> getPilihanJawaban() {
        return pilihanJawaban;
    }

    public boolean cekJawaban(String jawabanPengguna) {
        return jawabanPengguna != null && jawabanPengguna.trim().equalsIgnoreCase(this.jawabanBenar);
    }
}
