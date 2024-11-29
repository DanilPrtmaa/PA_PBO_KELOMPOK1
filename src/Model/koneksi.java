/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Danil Pratama
 */
public class koneksi {
    private Connection conn;
    private String url = "jdbc:mysql://localhost:3306/datadesa";  // Ganti sesuai dengan database Anda
    private String user = "root";
    private String password = "";
    private Connection Con;
    private Statement St;
    
    // Constructor untuk melakukan koneksi ke database

    /**
     *
     * @throws SQLException
     * @throws SQLException
     */
    public void koneksiDatabase() throws SQLException, SQLException {
         try {
             // Memuat JDBC Driver MySQL
             Class.forName("com.mysql.cj.jdbc.Driver");  // Driver MySQL
             
             // Menjalin koneksi dengan database
             Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadesa", "root", "password");  // Ganti dengan username dan password yang sesuai
             St = Con.createStatement(); // Membuat statement untuk eksekusi query
             System.out.println("Koneksi Berhasil!");
         } catch (ClassNotFoundException | SQLException e) {
             System.out.println("Koneksi Gagal: " + e.getMessage());
             throw new SQLException("Koneksi gagal: " + e.getMessage());  // Throw exception untuk penanganan lebih lanjut
         }
         
    }
    // Menyimpan data ke database
    public void simpanData() throws SQLException {
        // Membuat objek Kegiatan
        Kegiatan kegiatan = new Kegiatan("01", "Penyelenggara", "Administrasi Pemerintahan", "50.000.000", "40.000.000", "10.000.000");
        
        // Mendapatkan koneksi dan statement dari kelas koneksi
        koneksi db = new koneksi();
        db.koneksiDatabase();  // Pastikan koneksi berhasil
        
        // Menyimpan data ke database dengan memanggil metode simpanData
        db.simpanData(kegiatan.getKode(), kegiatan.getBidang(), kegiatan.getKegiatan(), kegiatan.getAnggaran(), kegiatan.getRealisasi(), kegiatan.getSisaAnggaran());
    }
    
    // Mendapatkan koneksi ke database
    public Connection getConnection() {
        return Con;
    }
        // Mendapatkan statement untuk eksekusi query
    public Statement getStatement() {
        return St;
    }
    
    // Menutup koneksi dan statement
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
            if (Con != null) Con.close();
        } catch (SQLException e) {
            System.out.println("Error saat menutup koneksi: " + e.getMessage());
        }
    }

    public void simpanData(String kode, String bidang, String kegiatan, String anggaran, String realisasi, String sisaAnggaran) {
        try {
            // Query SQL untuk memasukkan data
            String query = "INSERT INTO kegiatan (kode, bidang, kegiatan, anggaran, realisasi, sisaAnggaran) VALUES ('"
                    + kode + "', '" + bidang + "', '" + kegiatan + "', '" + anggaran + "', '" + realisasi + "', '" + sisaAnggaran + "')";
            St.executeUpdate(query);  // Menjalankan query untuk menyimpan data
            System.out.println("Data berhasil disimpan!");
        } catch (SQLException e) {
            System.out.println("Error saat menyimpan data: " + e.getMessage());
        }
    }

}

