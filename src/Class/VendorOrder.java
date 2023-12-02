/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class VendorOrder extends User{
    private static final String userFilePath = "src/Database/users.txt";
    private static final String orderFolderPath = "src/Database/order.txt/";
    
    public VendorOrder(int id, String username, String password, String role){

    }
    
    public VendorOrder(){
    }
    
    public List<List<String>> getVendorOrder(String vendorName) {
        List<List<String>> orderItems = new ArrayList<>();

        try {
        BufferedReader reader = new BufferedReader(new FileReader(orderFolderPath));
        reader.readLine(); // Skip header
        String line;
        while ((line = reader.readLine()) != null) {
            List<String> orderItem = new ArrayList<>(Arrays.asList(line.split(",")));
            
            // Assuming the VendorUserID is at index 6 in the order data
            int orderVendorID = Integer.parseInt(orderItem.get(6));

            // Check if the order is related to the specific vendor
            if (orderVendorID == getVendorUserIdByUsername(vendorName)) {
                orderItems.add(orderItem);
                System.out.println(orderVendorID);
            }
        }
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    } catch (IOException ex) {
        ex.printStackTrace();
    }
    return orderItems;
    }
    
    public int getVendorUserIdByUsername(String vendorName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String userName = userData[1].trim();
                int userID = Integer.parseInt(userData[0].trim()); // Assuming UserID is at index 0
                if (userName.equalsIgnoreCase(vendorName)) {
                    return userID;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return -1; // Return -1 if the username is not found (handle appropriately in your code)
    }
    
    
    public int generateNewTaskID(String filePath) throws IOException {
        int newTaskID = 1; // Default value for the first TaskID

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        // Skip the header if needed
        reader.readLine();

        // Count the existing TaskIDs
        while (reader.readLine() != null) {
            newTaskID++;
        }
        } catch (FileNotFoundException e) {
        // Handle file not found exception
        e.printStackTrace();
        }

        return newTaskID;
    }
    
}
