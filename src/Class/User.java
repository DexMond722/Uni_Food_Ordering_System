/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;


/**
 *
 * @author desmondcwf
 */
public class User {

    private int id;
    private String username;
    private String password;
    private String role;
    private double credit;

    public User(int id, String username, String password, String role, double credit) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.credit = credit;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public double getCredit() {
        return credit;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

}
