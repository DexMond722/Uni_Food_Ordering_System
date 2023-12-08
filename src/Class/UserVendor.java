package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserVendor extends User {

    private static final String userFilePath = "src/Database/users.txt";
    private static final String menuFolderPath = "src/Database/Menu/";

    public UserVendor() {

    }

    //get menu by vendorlist
    public List<List<String>> getVendorMenu(String vendorName) {
        List<List<String>> menuItems = new ArrayList<>();
        String menuFilePath = menuFolderPath + vendorName + "Menu.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(menuFilePath));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> menuItem = new ArrayList<>(Arrays.asList(line.split(",")));
                menuItems.add(menuItem);
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {
            Logger.getLogger(UserVendor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return menuItems;
    }

    //return menu item
    public String MenuItem(int foodID, String foodName, double price) {
        String formattedFoodID = "F" + String.format("%02d", foodID);
        return formattedFoodID + "," + foodName + "," + price;
    }

    //add new menu items
    public void addMenuItem(String vendorName, String foodName, double price) {
        String menuFilePath = menuFolderPath + vendorName + "Menu.txt";
        String newItem = MenuItem(getNextFoodID(menuFilePath), foodName, price);
        writeMenuItemToFile(menuFilePath, newItem);
    }

    //write menu items to file
    private void writeMenuItemToFile(String menuFilePath, String item) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(menuFilePath, true))) {
            writer.write(item);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //edit menu
    public void editMenuItem(String vendorName, String foodID, String editedFoodName, double editedPrice) {
        String menuFilePath = menuFolderPath + vendorName + "Menu.txt";
        try {
            List<String> menulines = Files.readAllLines(Paths.get(menuFilePath));
            for (int i = 0; i < menulines.size(); i++) {
                String menuline = menulines.get(i);
                String[] menudata = menuline.split(",");
                if (menudata[0].equals(foodID)) {
                    menulines.set(i, foodID + "," + editedFoodName + "," + editedPrice);
                    break;
                }
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(menuFilePath))) {
                for (String line : menulines) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //delete menu items
    public void deleteMenuItem(String vendorName, int selectedRow) {
        String menuFilePath = menuFolderPath + vendorName + "Menu.txt";

        if (selectedRow != -1) {
            try {
                List<String> menuLines = Files.readAllLines(Paths.get(menuFilePath));
                menuLines.remove(selectedRow + 1);

                Files.write(Paths.get(menuFilePath), menuLines);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    //get food name from table
    public String getCurrentFoodNameFromTable(int selectedRow, String vendorName) {
        String menuFilePath = "src/Database/Menu/" + vendorName + "Menu.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(menuFilePath))) {
            reader.readLine();

            String line;
            int currentRow = 0;
            while ((line = reader.readLine()) != null) {
                if (currentRow == selectedRow) {
                    String[] names = line.split(",");
                    return names[1];
                }
                currentRow++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    //get next food id
    private int getNextFoodID(String menuFilePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(menuFilePath))) {
            reader.readLine();
            String line;
            int lastFoodID = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int currentFoodID = Integer.parseInt(parts[0].substring(1));
                lastFoodID = Math.max(lastFoodID, currentFoodID);
            }

            return lastFoodID + 1;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 1;
    }

}
