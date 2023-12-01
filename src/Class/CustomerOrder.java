package Class;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

public class CustomerOrder extends UserCustomer{
    
    private static final String orderFilePath = "src/Database/order.txt";
    private static final String orderItemsFilePath = "src/Database/orderItems.txt";
    
    private CustomerCredit customerCredit;

    public CustomerOrder(int id, String username, String password, String role, double credit) {
        super(id, username, password, role, credit);
    }
    
    public CustomerOrder(CustomerCredit customerCredit){
        this.customerCredit = customerCredit;
    }
    
    public CustomerOrder(){
        
    }
    
    // place order 
    public void placeOrder(double orderAmount, int customerUserID, int vendorUserID, String serviceType, List<List<String>> orderItems){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);
        String orderStatus = "pending";
        int lastOrderItemsID = getLastOrderItemsID();
        String data = getLastOrderID() + "," + currentTime + "," + lastOrderItemsID + "," + orderAmount + "," + orderStatus + "," + customerUserID + "," + vendorUserID + "," + serviceType;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderFilePath, true))) {
            writer.write(data);
            writer.newLine(); // Add a new line for the next entry
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        writeOrderItemsData(orderItems,lastOrderItemsID);
        customerCredit.updateCustomerandVendorCredit(customerUserID, vendorUserID, orderAmount);
    }
    
    // Write Order items data inside the text file
    private void writeOrderItemsData(List<List<String>> orderItems, int lastOrderItemsID){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(orderItemsFilePath,true))){
            for (List<String> orderItem : orderItems){
                String orderItemData = lastOrderItemsID+","+orderItem.get(0)+","+orderItem.get(1);
                writer.write(orderItemData);
                writer.newLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    // calculate last orderID
    private int getLastOrderID() {
        int lastOrderID = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(orderFilePath));
            reader.readLine();
            String lastLine = null;
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
            reader.close();

            if (lastLine != null) {
                String[] parts = lastLine.split(",");
                lastOrderID = Integer.parseInt(parts[0]);
                return lastOrderID + 1;
            } else {
                return 1;
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UserCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastOrderID;
    }

    // calculate lastorderItemsID and it should be same with last order ID
    private int getLastOrderItemsID(){
        return getLastOrderID();
    }
    
}
