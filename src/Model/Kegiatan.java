/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.SQLException;
/**
 *
 * @author Danil Pratama
 */
public class Kegiatan {
    private String kode;
    private String bidang;
    private String kegiatan;
    private String anggaran;
    private String realisasi;
    private String sisaAnggaran;
    
    // Constructor untuk menginisialisasi objek Kegiatan dengan data
    public Kegiatan(String kode, String bidang, String kegiatan, String anggaran, String realisasi, String sisaAnggaran) {
        this.kode = kode;
        this.bidang = bidang;
        this.kegiatan = kegiatan;
        this.anggaran = anggaran;
        this.realisasi = realisasi;
        this.sisaAnggaran = sisaAnggaran;
    }
    
    // Getter dan Setter untuk masing-masing atribut
    public String getKode() { return kode; }
    public String getBidang() { return bidang; }
    public String getKegiatan() { return kegiatan; }
    public String getAnggaran() { return anggaran; }
    public String getRealisasi() { return realisasi; }
    public String getSisaAnggaran() { return sisaAnggaran; }
    
    // Menyimpan data ke database
public void simpanData() throws SQLException {
    // Membuat objek Kegiatan dengan data yang ingin disimpan
    Kegiatan kegiatan = new Kegiatan("01", "Penyelenggara", "Administrasi Pemerintahan", "50.000.000", "40.000.000", "10.000.000");

    // Membuat objek koneksi
    koneksi db = new koneksi();

    try {
        // Pastikan koneksi berhasil
        db.koneksiDatabase();

        // Menyimpan data ke database dengan memanggil metode simpanData dari kelas koneksi
        db.simpanData(kegiatan.getKode(), kegiatan.getBidang(), kegiatan.getKegiatan(), kegiatan.getAnggaran(), kegiatan.getRealisasi(), kegiatan.getSisaAnggaran());
        
    } catch (SQLException e) {
        // Menangani exception jika terjadi kesalahan saat menyimpan data
        System.out.println("Terjadi error saat menyimpan data: " + e.getMessage());
    } finally {
        // Menutup koneksi setelah selesai
        db.close();
    }
}
    
    
}
