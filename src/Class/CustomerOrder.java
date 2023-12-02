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
    private static final String userFilePath = "src/Database/users.txt";
    private static final String menuFolderPath = "src/Database/Menu/";
    
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
    
    // get corressponding Customer Order History 
    public List<List<String>> getOrderHistoryData(String username){
        List<List<String>> orderHistoryData = new ArrayList<>();
        String customerUserID = String.valueOf(super.getCustomerUserID(username));
        try {
            BufferedReader reader = new BufferedReader(new FileReader(orderFilePath));
            String line;
            reader.readLine();
            while((line = reader.readLine())!= null){
                String[] orderData = line.split(",");
                if (orderData[5].equals(customerUserID)){
                    List<String> orderInfo = new ArrayList<>();
                    orderInfo.add(orderData[0]);  // OrderID
                    orderInfo.add(orderData[1]);  // OrderPlacementTime
                    orderInfo.add(orderData[3]);  // OrderAmount
                    orderInfo.add(orderData[4]);  // OrderStatus
                    orderInfo.add(orderData[7]);  // ServiceType
                    orderHistoryData.add(orderInfo);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderHistoryData;
    }
    
    // get Order Items Data
    public List<List<String>> getOrderItemsData(String orderID){
        List<List<String>> orderItemsData = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(orderItemsFilePath));
            String line;
            reader.readLine();
            while((line = reader.readLine())!=null){
                String[] orderItems = line.split(",");
                if (orderItems[0].equals(orderID)){
                    List<String> orderItemsInfo = new ArrayList<>();
                    String vendorUsername = super.getVendorUserName(getVendorID(orderItems[0]));
                    orderItemsInfo.add(getOrderItemName(vendorUsername,orderItems[1]));
                    orderItemsInfo.add(orderItems[2]);
                    orderItemsInfo.add(String.valueOf(calculateOrderItemTotalPrice(vendorUsername,orderItems[1],orderItems[2])));
                    orderItemsData.add(orderItemsInfo);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderItemsData;
    }
    
    
    private String getOrderItemName(String vendorUsername, String FoodID){
        String itemName = null;
        String menuFilePath = menuFolderPath + vendorUsername + "Menu.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(menuFilePath));
            String line;
            reader.readLine();
            while((line = reader.readLine())!=null){
                String[] ItemData = line.split(",");
                if (ItemData[0].equals(FoodID)){
                    itemName = ItemData[1];
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return itemName;
    }
    
    private double calculateOrderItemTotalPrice(String vendorUsername, String FoodID, String itemQuantities){
        double totalPrice = 0;
        String menuFilePath = menuFolderPath + vendorUsername + "Menu.txt";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(menuFilePath));
            String line;
            reader.readLine();
            while((line = reader.readLine())!=null){
                String[] ItemData = line.split(",");
                if(ItemData[0].equals(FoodID)){
                    double itemPrice = Double.parseDouble(ItemData[2]);
                    double itemQuantity = Double.parseDouble(itemQuantities);
                    totalPrice = itemPrice * itemQuantity;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalPrice; 
    }
    
    private String getVendorID(String orderID){
        String vendorID = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(orderFilePath));
            String line;
            reader.readLine();
            while((line = reader.readLine())!=null){
                String[] orderData = line.split(",");
                if(orderData[0].equals(orderID)){
                    vendorID = orderData[6];
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vendorID;
    }
    
    
    
}
