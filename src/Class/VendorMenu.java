/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author desmondcwf
 */
public class VendorMenu {

    private static final String MENU_FOLDER_PATH = "src/Database/Menu/";

    //create vendor menu text file
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

    //delete vnndormenu text file
    public static void deleteVendorMenu(String vendorName) {
        String menuFilePath = MENU_FOLDER_PATH + vendorName + "Menu.txt";
        Path path = Paths.get(menuFilePath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
