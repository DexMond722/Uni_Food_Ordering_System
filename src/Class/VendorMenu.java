/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author desmondcwf
 */
public class VendorMenu {

    private static final String MENU_FOLDER_PATH = "src/Database/Menu/";

    public static void createVendorMenu(String vendorName) {
        String menuFilePath = MENU_FOLDER_PATH + vendorName + "Menu.txt";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(menuFilePath))) {
            bw.write("FoodID,FoodName,Price");
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
