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
public class ProsesLogin {

    public static void login(String username, String password) {
        Akun akun = null;

        if (username.equals("danil")) {
            akun = new KepalaDesa(username, password, "Kepala Desa");
        } else if (username.equals("bilo")) {
            akun = new Sekretaris(username, password, "Sekretaris");
        }

        if (akun != null && akun.login(username, password)) {
            System.out.println("Login sukses sebagai " + akun.getRole());
        } else {
            JOptionPane.showMessageDialog(null, "Username atau password salah!");
        }
    }
}
