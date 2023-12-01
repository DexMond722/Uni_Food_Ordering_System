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
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(serviceType.equals("Delivery")){
        orderAmount += 4;
        }
        LocalDateTime now = LocalDateTime.now();
        String currentTime = dtf.format(now);
        String orderStatus = "Pending";
        int lastOrderItemsID = getLastOrderItemsID();
        String lastTransactionID = customerCredit.generateLastTransactionID();
        String data = getLastOrderID() + "," + currentTime + "," + lastOrderItemsID + "," + orderAmount + "," + orderStatus + "," + customerUserID + "," + vendorUserID + "," + serviceType+","+lastTransactionID;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(orderFilePath, true))) {
            writer.write(data);
            writer.newLine(); // Add a new line for the next entry
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        writeOrderItemsData(orderItems,lastOrderItemsID);
        customerCredit.updateCustomerandVendorCredit(customerUserID, vendorUserID, orderAmount);
        customerCredit.generateCustomerTransactionData(lastTransactionID, customerUserID, vendorUserID, orderAmount, currentTime, serviceType);
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
    public int getLastOrderID() {
        int lastOrderID = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(orderFilePath))) {
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (!fields[0].isEmpty()) {
                    lastOrderID = Integer.parseInt(fields[0]) + 1;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }

        return lastOrderID;
    }

    // calculate lastorderItemsID and it should be same with last order ID
    private int getLastOrderItemsID(){
        return getLastOrderID();
    }
    
}
