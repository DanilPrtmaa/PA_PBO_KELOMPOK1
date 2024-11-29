/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import javax.swing.JOptionPane;
/**
 *
 * @author Danil Pratama
 */
public class Sekretaris extends Akun {

    // Atribut khusus Sekretaris
    private final String role = "Sekretaris";

    // Constructor
    public Sekretaris(String username, String password, String status) {
        super(username, password, status);  // Memanggil constructor superclass
    }

    // Override metode getRole untuk mengembalikan role Sekretaris
    @Override
    public String getRole() {
        return this.role;
    }

    // Metode login untuk Sekretaris
    public static final boolean loginSekretaris(String username, String password) {
        try {
            String query = "SELECT * FROM sekretaris WHERE username = ? AND password = ?";
            Database.preparedStatement = Database.getPreparedStatement(query);
            Database.preparedStatement.setString(1, username);
            Database.preparedStatement.setString(2, password);
            Database.resultSet = Database.preparedStatement.executeQuery();

            if (Database.resultSet.next()) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Username atau password salah!");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Login gagal! " + e.getMessage());
        } finally {
            Database.closeResources();
        }
        return false;
    }

    // Metode untuk mendapatkan status Sekretaris berdasarkan username
    public static final String getStatus(String username) {
        try {
            String query = "SELECT status FROM sekretaris WHERE username = ?";
            Database.preparedStatement = Database.getPreparedStatement(query);
            Database.preparedStatement.setString(1, username);
            Database.resultSet = Database.preparedStatement.executeQuery();

            if (Database.resultSet.next()) {
                return Database.resultSet.getString("status");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal mendapatkan status! " + e.getMessage());
        } finally {
            Database.closeResources();
        }
        return null;
    }
}
