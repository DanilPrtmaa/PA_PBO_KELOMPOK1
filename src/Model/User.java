/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Model;

/**
 *
 * @author Danil Pratama
 */
public abstract class User {
    private final String Username;
    private final String Password;
    private final String Status;

    public User(String username, String password, String status){
        this.Username = username;
        this.Password = password;
        this.Status = status;
    }


    public abstract String getRole();
}
