package Login;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserCustomer extends User {
    
    
    // declare file path
    private static final String userFilePath = "src/login/users.txt";
    
    public UserCustomer(String username, String password, String role) {
        super(username, password, role);
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
    
}
