/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package oodj_assignment;

import Login.LoginPage;

/**
 *
 * @author desmondcwf
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginPage Login = new LoginPage();
        Login.setVisible(true);
        Login.pack();
        Login.setLocationRelativeTo(null);
        Login.txt_Username.requestFocus();
        
    }
    
}
