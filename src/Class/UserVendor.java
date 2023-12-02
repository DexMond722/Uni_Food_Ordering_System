package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserVendor extends User{
    private static final String userFilePath = "src/Database/users.txt";
    private static final String menuFolderPath = "src/Database/Menu/";
    
    public UserVendor(int id, String username, String password, String role){

    }
    
    public UserVendor(){
    }
    
    public List<String> getVendorList() {
        List<String> vendorList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                String userName = userData[1].trim();
                String userRole = userData[3].trim();
                if (userRole.equalsIgnoreCase("Vendor")){
                    vendorList.add(userName); 
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserVendor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserVendor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendorList;
    }
    
    //get menu by vendorlist
    public List<List<String>> getVendorMenu(String vendorName) {
        List<List<String>> menuItems = new ArrayList<>();
        String menuFilePath = menuFolderPath + vendorName + "Menu.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(menuFilePath));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null){
                List<String> menuItem = new ArrayList<>(Arrays.asList(line.split(",")));
                menuItems.add(menuItem);
            }          
        } catch (FileNotFoundException ex) {
            
        } catch (IOException ex) {
            Logger.getLogger(UserVendor.class.getName()).log(Level.SEVERE, null, ex);     
        }
        return menuItems;
    }
    
    public static void addMenuItem(String vendorName, String foodName, double price) {
        String menuFilePath = menuFolderPath + vendorName + "Menu.txt";
        MenuItem newItem = new MenuItem(getNextFoodID(menuFilePath), foodName, price);
        writeMenuItemToFile(menuFilePath, newItem);
}

    private static void writeMenuItemToFile(String menuFilePath, MenuItem item) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(menuFilePath, true))) {
            writer.write(item.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
     private static int getNextFoodID(String menuFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(menuFilePath))) {
            reader.readLine();
            String line;
            int lastFoodID = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int currentFoodID = Integer.parseInt(parts[0].substring(1)); // Extract the numeric part of FoodID
                lastFoodID = Math.max(lastFoodID, currentFoodID);
            }

            return lastFoodID + 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }
    
}



