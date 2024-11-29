/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DesaGUI;

import DesaGUI.Riwayat;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author Danil Pratama
 */
public class SekretarisDesa extends javax.swing.JFrame {
    private Statement St;
    private Connection Con;
    private ResultSet rs;
    private String sql="";

    /**
     * Creates new form DashboardGUI
     */
    public SekretarisDesa() {
        initComponents();
        tampilData(); // Tampilkan data saat program dijalankan
        
        // Mengatur aksi tombol
        tambah.addActionListener(evt -> tambahData());
        edit.addActionListener(evt -> editData());
        hapus.addActionListener(evt -> hapusData());
        cari.addActionListener(evt -> cariData());
    }
    

// Fungsi untuk menambah data
    private void tambahData() {
        koneksiDatabase();
        try {
            double anggaranDbl = Double.parseDouble(anggaranD.getText());
            double realisasiDbl = Double.parseDouble(realisasiD.getText());
            double sisaAnggaranDbl = anggaranDbl - realisasiDbl;
            
            String sql = "INSERT INTO keuangan_desa (kode, bidang, kegiatan, anggaran, realisasi, sisa_anggaran) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.setString(1, kode.getText());
            pst.setString(2, bidang.getText());
            pst.setString(3, kegiatan.getText());
            pst.setDouble(4, anggaranDbl);
            pst.setDouble(5, realisasiDbl);
            pst.setDouble(6, sisaAnggaranDbl);
            
            int rowsInserted = pst.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
                tampilData();  // Refresh tabel
                simpanRiwayat(kode.getText(), bidang.getText(), kegiatan.getText(),
                    anggaranDbl, realisasiDbl, sisaAnggaranDbl, "Tambah");
            } else {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan data!");
            }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menambahkan data!", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Harap masukkan nilai numerik yang valid untuk anggaran dan realisasi!");
            }
    }
    
    // Fungsi untuk mengedit data
    private void editData() {
        koneksiDatabase();
        try {
            double anggaranDbl = Double.parseDouble(anggaranD.getText());
            double realisasiDbl = Double.parseDouble(realisasiD.getText());
            double sisaAnggaranDbl = anggaranDbl - realisasiDbl;
            
            String sql = "UPDATE keuangan_desa SET bidang=?, kegiatan=?, anggaran=?, realisasi=?, sisa_anggaran=? WHERE kode=?";
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.setString(1, bidang.getText());
            pst.setString(2, kegiatan.getText());
            pst.setDouble(3, anggaranDbl);
            pst.setDouble(4, realisasiDbl);
            pst.setDouble(5, sisaAnggaranDbl);
            pst.setString(6, kode.getText());
            
            int rowsUpdated = pst.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
                tampilData();
                simpanRiwayat(kode.getText(), bidang.getText(), kegiatan.getText(),
                    anggaranDbl, realisasiDbl, sisaAnggaranDbl, "Edit");
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan untuk diperbarui.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memperbarui data!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harap masukkan nilai numerik yang valid untuk anggaran dan realisasi!");
        }
    }
    
    // Fungsi untuk menghapus data
    private void hapusData() {
        koneksiDatabase();
        try {
            String sql = "DELETE FROM keuangan_desa WHERE kode=?";
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.setString(1, kode.getText());
            int rowsDeleted = pst.executeUpdate(); // Eksekusi query dan simpan jumlah baris yang dihapus
            
            if (rowsDeleted > 0) {
                tampilData(); // Refresh data di tabel
                // Simpan riwayat penghapusan
                simpanRiwayat(kode.getText(), bidang.getText(), kegiatan.getText(),
                        Double.parseDouble(anggaranD.getText()),
                        Double.parseDouble(realisasiD.getText()),
                        Double.parseDouble(sisaanggaran.getText()), "Hapus");
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan atau tidak ada yang dihapus.");
            }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data!", "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            }
    
    private void catatRiwayat(String aksi) {
        try {
            String sql = "INSERT INTO riwayat (kode, bidang, kegiatan, anggaran, realisasi, sisa_anggaran, aksi, waktu) VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.setString(1, kode.getText());
            pst.setString(2, bidang.getText());
            pst.setString(3, kegiatan.getText());
            pst.setDouble(4, Double.parseDouble(anggaranD.getText()));
            pst.setDouble(5, Double.parseDouble(realisasiD.getText()));
            pst.setDouble(6, Double.parseDouble(sisaanggaran.getText()));
            pst.setString(7, aksi);
            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencatat riwayat!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Fungsi untuk mencari data
    private void cariData() {
        koneksiDatabase();
        try {
            String sql = "SELECT * FROM keuangan_desa WHERE kode=?";
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.setString(1, kode.getText());
            rs = pst.executeQuery();
            if(rs.next()) {
                bidang.setText(rs.getString("bidang"));
                kegiatan.setText(rs.getString("kegiatan"));
                anggaranD.setText(rs.getString("anggaran"));
                realisasiD.setText(rs.getString("realisasi"));
                sisaanggaran.setText(rs.getString("sisa_anggaran"));
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak ditemukan!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mencari data!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    // Fungsi untuk menampilkan data ke tabel
    private void tampilData() {
        koneksiDatabase();
        try {
            String sql = "SELECT * FROM keuangan_desa";
            St = Con.createStatement();
            rs = St.executeQuery(sql);
            tbsekretaris.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void simpanRiwayat(String kode, String bidang, String kegiatan, double anggaran, double realisasi, double sisaAnggaran, String aksi) {
        koneksiDatabase();
        try {
            String sql = "INSERT INTO riwayat (kode, bidang, kegiatan, anggaran, realisasi, sisa_anggaran, aksi) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = Con.prepareStatement(sql);
            pst.setString(1, kode);
            pst.setString(2, bidang);
            pst.setString(3, kegiatan);
            pst.setDouble(4, anggaran);
            pst.setDouble(5, realisasi);
            pst.setDouble(6, sisaAnggaran);
            pst.setString(7, aksi); // Aksi bisa berupa "Tambah", "Edit", atau "Hapus"
            pst.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan riwayat!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private void koneksiDatabase() {
        try {
            // Memuat driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");  // Pastikan JDBC driver sudah ada
            
            // Membuat koneksi ke database
            Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datadesa", "root", "");
            System.out.println("Koneksi Berhasil!"); // Optional: untuk memverifikasi koneksi berhasil
        } catch (ClassNotFoundException e) {
            // Menangani jika driver MySQL tidak ditemukan
            JOptionPane.showMessageDialog(this, "Driver MySQL tidak ditemukan!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            // Menangani jika koneksi gagal
            JOptionPane.showMessageDialog(this, "Koneksi ke database gagal!", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    
    private double hitungSisaAnggaran(double anggaran, double realisasi) {
        return anggaran - realisasi;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField5 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbsekretaris = new javax.swing.JTable();
        kode = new javax.swing.JTextField();
        bidang = new javax.swing.JTextField();
        kegiatan = new javax.swing.JTextField();
        anggaranD = new javax.swing.JTextField();
        realisasiD = new javax.swing.JTextField();
        sisaanggaran = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tambah = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        hapus = new javax.swing.JButton();
        cari = new javax.swing.JButton();
        riwayat = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        logout = new javax.swing.JButton();

        jTextField5.setText("jTextField5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/KOK9 (2).jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4);
        jPanel4.setBounds(0, 40, 220, 460);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        tbsekretaris.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        tbsekretaris.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"01", "Penyelenggara", "Administrasi Pemerintahan", "50.000.000", "40.000.000", "10.000.000"},
                {"02", "Pelaksanaan Pembangunan Desa", "Pembangunan Jalan Desa", "150.000.000", "100.000.000	", "50.000.000"},
                {"03", "Pelaksanaan Pembangunan Desa", "Pembangunan Fasilitas Air", "100.000.000", "70.000.000", "30.000.000"},
                {"04", "Pembinaan Kemasyarakatan", "Pembinaan PKK", "20.000.000", "15.000.000", "5.000.000"},
                {"05", "Pembinaan Kemasyarakatan", "Pembinaan Karang Taruna", "25.000.000", "20.000.000", "5.000.000"},
                {"06", "Pemberdayaan Masyarakat Desa", "Pengembangan UMKM Desa", "60.000.000", "50.000.000", "10.000.000"},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Kode Bidang & Kegiatan", "Nama Bidang", "Nama Kegiatan", "Anggaran", "Realisasi", "Sisa Anggaran"
            }
        ));
        jScrollPane1.setViewportView(tbsekretaris);

        kegiatan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kegiatanActionPerformed(evt);
            }
        });

        anggaranD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                anggaranDActionPerformed(evt);
            }
        });

        realisasiD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                realisasiDActionPerformed(evt);
            }
        });

        sisaanggaran.setEditable(false);
        sisaanggaran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sisaanggaranActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel1.setText("Kode Bidang & Kegiatan");

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel2.setText("Nama Bidang");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel4.setText("Nama Kegiatan");

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel5.setText("Anggaran");

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel6.setText("Realisasi");

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 0, 14)); // NOI18N
        jLabel7.setText("Sisa Anggaran");

        tambah.setBackground(new java.awt.Color(255, 165, 0));
        tambah.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        tambah.setText("Tambah");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        edit.setBackground(new java.awt.Color(255, 165, 0));
        edit.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        hapus.setBackground(new java.awt.Color(255, 165, 0));
        hapus.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        hapus.setText("Hapus");
        hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusActionPerformed(evt);
            }
        });

        cari.setBackground(new java.awt.Color(255, 165, 0));
        cari.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        cari.setText("Cari");
        cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariActionPerformed(evt);
            }
        });

        riwayat.setBackground(new java.awt.Color(255, 165, 0));
        riwayat.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        riwayat.setText("Riwayat");
        riwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                riwayatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(tambah)))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(kode, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                            .addComponent(bidang)
                            .addComponent(kegiatan))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(realisasiD)
                            .addComponent(sisaanggaran)
                            .addComponent(anggaranD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(edit)
                        .addGap(36, 36, 36)
                        .addComponent(hapus)
                        .addGap(42, 42, 42))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(374, 374, 374)
                        .addComponent(cari))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(489, 489, 489)
                        .addComponent(riwayat)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(anggaranD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(realisasiD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bidang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(sisaanggaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(kegiatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tambah)
                    .addComponent(edit)
                    .addComponent(hapus)
                    .addComponent(cari)
                    .addComponent(riwayat))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        getContentPane().add(jPanel5);
        jPanel5.setBounds(220, 40, 580, 460);

        jPanel6.setBackground(new java.awt.Color(255, 165, 0));

        jTextField1.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        jTextField1.setText("PENGELOLAAN KEUANGAN DESA");

        logout.setBackground(new java.awt.Color(255, 0, 0));
        logout.setFont(new java.awt.Font("Tw Cen MT", 1, 14)); // NOI18N
        logout.setForeground(new java.awt.Color(255, 255, 255));
        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(226, 226, 226)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                .addComponent(logout)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6);
        jPanel6.setBounds(0, 0, 800, 40);

        setSize(new java.awt.Dimension(814, 507));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kegiatanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kegiatanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kegiatanActionPerformed

    private void hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hapusActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        String kodeBidang = kode.getText();
        String namaBidang = bidang.getText();
        String namaKegiatan = kegiatan.getText();
        String anggaran = anggaranD.getText();
        String realisasi = realisasiD.getText();
        
        try {
            double anggaranDbl = Double.parseDouble(anggaran);
            double realisasiDbl = Double.parseDouble(realisasi);
            double sisaAnggaranDbl = anggaranDbl - realisasiDbl;
            String sisaAnggaranFormatted = String.format("%.2f", sisaAnggaranDbl);
            
            koneksiDatabase();
            
            String sql = "INSERT INTO kegiatan (kode, bidang, kegiatan, anggaran, realisasi, sisa_anggaran) " +
                "VALUES ('" + kodeBidang + "', '" + namaBidang + "', '" + namaKegiatan + "', '" + anggaran + "', '" + realisasi + "', '" + sisaAnggaranFormatted + "')";
            St = Con.createStatement();
            St.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal menambahkan data: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harap masukkan nilai yang valid untuk anggaran dan realisasi!");
        }
    }//GEN-LAST:event_tambahActionPerformed

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        String kodeBidang = kode.getText();
        String namaBidang = bidang.getText();
        String namaKegiatan = kegiatan.getText();
        String anggaran = anggaranD.getText();
        String realisasi = realisasiD.getText();
        
        try {
            double anggaranDbl = Double.parseDouble(anggaran);
            double realisasiDbl = Double.parseDouble(realisasi);
            double sisaAnggaranDbl = anggaranDbl - realisasiDbl;
            String sisaAnggaranFormatted = String.format("%.2f", sisaAnggaranDbl);
            
            koneksiDatabase();
            
            String sql = "UPDATE kegiatan SET bidang = '" + namaBidang + "', kegiatan = '" + namaKegiatan + "', anggaran = '" + anggaran + 
                "', realisasi = '" + realisasi + "', sisa_anggaran = '" + sisaAnggaranFormatted + "' WHERE kode = '" + kodeBidang + "'";
            St = Con.createStatement();
            St.executeUpdate(sql);
            
            JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Gagal mengupdate data: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Harap masukkan nilai yang valid untuk anggaran dan realisasi!");
        }
    }//GEN-LAST:event_editActionPerformed

    private void cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariActionPerformed

    private void riwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_riwayatActionPerformed
        Riwayat riwayatFrame = new Riwayat("sekretaris");
        riwayatFrame.setVisible(true);
        this.dispose(); // Menutup halaman SekretarisDesa
    }//GEN-LAST:event_riwayatActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        this.dispose();
        new LoginGUI().setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

    private void anggaranDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_anggaranDActionPerformed
        hitungDanTampilkanSisaAnggaran();
    }//GEN-LAST:event_anggaranDActionPerformed

    private void realisasiDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_realisasiDActionPerformed
        hitungDanTampilkanSisaAnggaran();
    }//GEN-LAST:event_realisasiDActionPerformed

    private void sisaanggaranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sisaanggaranActionPerformed
        try {
            double anggaranDbl = Double.parseDouble(anggaranD.getText());
            double realisasiDbl = Double.parseDouble(realisasiD.getText());
            double sisaAnggaranDbl = hitungSisaAnggaran(anggaranDbl, realisasiDbl);
            
            String sisaAnggaranFormatted = String.format("%.2f", sisaAnggaranDbl);
            sisaanggaran.setText(sisaAnggaranFormatted);
        } catch (NumberFormatException e) {
            sisaanggaran.setText("0.00");
        }
    }//GEN-LAST:event_sisaanggaranActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SekretarisDesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SekretarisDesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SekretarisDesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SekretarisDesa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SekretarisDesa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField anggaranD;
    private javax.swing.JTextField bidang;
    private javax.swing.JButton cari;
    private javax.swing.JButton edit;
    private javax.swing.JButton hapus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField kegiatan;
    private javax.swing.JTextField kode;
    private javax.swing.JButton logout;
    private javax.swing.JTextField realisasiD;
    private javax.swing.JButton riwayat;
    private javax.swing.JTextField sisaanggaran;
    private javax.swing.JButton tambah;
    private javax.swing.JTable tbsekretaris;
    // End of variables declaration//GEN-END:variables

    private void hitungDanTampilkanSisaAnggaran() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
