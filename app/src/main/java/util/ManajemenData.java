package util; // Menambahkan deklarasi package

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import pengguna.AktivitasHarian; // Import kelas yang dibutuhkan
import latihan.HariBertarung;    // Import kelas yang dibutuhkan
import latihan.HariIstirahat;   // Import kelas yang dibutuhkan
import pengguna.Pengguna;        // Import kelas yang dibutuhkan

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class ManajemenData { // Nama kelas diubah menjadi ManajemenData (PascalCase)
    private static final String NAMA_FILE = "pengguna.json";
    private static Gson gson;

    static {
        // Gson perlu "diajari" cara menangani interface seperti AktivitasHarian.
        // Kita beritahu bahwa jika ia menemukan AktivitasHarian, itu bisa berupa
        // objek HariBertarung atau HariIstirahat.
        RuntimeTypeAdapterFactory<AktivitasHarian> adapter = RuntimeTypeAdapterFactory
                .of(AktivitasHarian.class, "tipeAktivitas")
                .registerSubtype(HariBertarung.class, "bertarung")
                .registerSubtype(HariIstirahat.class, "istirahat");

        // Membuat instance Gson dengan adapter yang sudah dikonfigurasi
        gson = new GsonBuilder()
                .registerTypeAdapterFactory(adapter)
                .setPrettyPrinting() // Membuat file JSON rapi dan mudah dibaca
                .create();
    }

    /**
     * Menyimpan seluruh data pengguna ke file pengguna.json.
     * @param data Peta (Map) dari semua pengguna yang akan disimpan.
     */
    public static void simpanDataPengguna(Map<String, Pengguna> data) {
        try (Writer writer = new FileWriter(NAMA_FILE)) {
            gson.toJson(data, writer);
            System.out.println("Data pengguna berhasil disimpan ke " + NAMA_FILE);
        } catch (IOException e) {
            System.err.println("Gagal menyimpan data: " + e.getMessage());
        }
    }

    /**
     * Memuat data pengguna dari file pengguna.json.
     * @return Peta (Map) dari semua pengguna yang berhasil dimuat.
     */
    public static Map<String, Pengguna> muatDataPengguna() {
        try (Reader reader = new FileReader(NAMA_FILE)) {
            // TypeToken diperlukan agar Gson tahu cara mengembalikan tipe data kompleks seperti Map<String, Pengguna>
            Type type = new TypeToken<HashMap<String, Pengguna>>(){}.getType();
            Map<String, Pengguna> data = gson.fromJson(reader, type);
            System.out.println("Data pengguna berhasil dimuat dari " + NAMA_FILE);
            // Jika file kosong, kembalikan map baru untuk menghindari null
            return data != null ? data : new HashMap<>();
        } catch (FileNotFoundException e) {
            System.out.println("File data tidak ditemukan. Database baru akan dibuat saat keluar.");
            return new HashMap<>();
        } catch (IOException e) {
            System.err.println("Gagal memuat data: " + e.getMessage());
            return new HashMap<>();
        }
    }
}