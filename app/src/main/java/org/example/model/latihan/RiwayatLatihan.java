package org.example.model.latihan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RiwayatLatihan {
    private final List<CatatanLatihan> daftarCatatan;

    public RiwayatLatihan() {
        this.daftarCatatan = new ArrayList<>();
    }

    public void tambahkanCatatan(CatatanLatihan catatan) {
        this.daftarCatatan.add(catatan);
        System.out.println("-> Catatan latihan untuk '" + catatan.getNamaLatihan() + "' telah disimpan ke riwayat.");
    }

    public void tampilkanSemuaRiwayat() {
        System.out.println("\n--- RIWAYAT LATIHAN ANDA ---");
        if (daftarCatatan.isEmpty()) {
            System.out.println("Anda belum memiliki riwayat latihan. Mulailah berlatih!");
        } else {
            for (CatatanLatihan catatan : daftarCatatan) {
                catatan.tampilkanDetail();
            }
        }
        System.out.println("----------------------------");
    }

    public List<CatatanLatihan> getCatatanBerdasarkanProgram(String namaProgram) {
        return daftarCatatan.stream()
                .filter(catatan -> catatan.getNamaProgram().equalsIgnoreCase(namaProgram))
                .collect(Collectors.toList());
    }

    public List<CatatanLatihan> getSemuaCatatan() {
        return new ArrayList<>(daftarCatatan); 
    }
    
}
