package Class;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserCustomer extends User {
    
    
    // declare file path
    private static final String userFilePath = "src/Database/users.txt";
    private static final String creditFilePath = "src/Database/credit.txt";
    private static final String menuFolderPath = "src/Database/Menu/";
    private static final String orderFilePath = "src/Database/order.txt";
            
    public UserCustomer(int id, String username, String password, String role, double credit) {
        super(id,username, password, role,credit);
    }
        
    public UserCustomer() {}    

    // get Vendor in a list
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
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendorList;
    }
    
    // get menu by specific vendor name
    public List<List<String>> getMenuOfVendor(String vendorName){
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
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);     
        }
        return menuItems;
    }
    
    // get vendor user id by vendorname
    public int getUserID(String username){
        int userID = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                String userName = userData[1].trim();
                if(userName.equalsIgnoreCase(username)){
                    userID = Integer.parseInt(userData[0].trim());
                    break;
                }                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userID;
    }
    
    public String getVendorUserName(String vendorID){
        String vendorUserName = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                if(userData[0].equals(vendorID)){
                    vendorUserName = userData[1];
                }                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendorUserName;
    }
    
    
    
 
}
