package Login;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserCustomer extends User {
    
    
    // declare file path
    private static final String userFilePath = "src/login/users.txt";
    private static final String creditFilePath = "src/login/credit.txt";
    private static final String menuFolderPath = "src/login/menu/";
            
    public UserCustomer(String username, String password, String role) {
        super(username, password, role);
    }
        
    public UserCustomer() {}    
   
    // get Credit of Customer
    public double getCustomerCredit(String userName){
        double credit = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(creditFilePath));
            String line; 
            while((line = reader.readLine()) != null){
                String[] creditData = line.split(",");
                String customerName = creditData[0];
                if (customerName.equals(userName)){
                    credit = Double.parseDouble(creditData[1].trim());
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return credit;
    }
    
    // get Vendor in a list
    public List<String> getVendorList() {
        List<String> vendorList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while((line = reader.readLine()) != null){
                String[] userData = line.split(",");
                String userName = userData[0].trim();
                String userRole = userData[2].trim();
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
    
    
}
