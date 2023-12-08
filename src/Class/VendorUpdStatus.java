/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class VendorUpdStatus {

    private VendorOrder vendorOrder;
    private static final String userFilePath = "src/Database/users.txt";
    private static final String orderFilePath = "src/Database/order.txt/";
    private static final String runnerFilePath = "src/Database/runnertask.txt";
    private static final String creditTransactionFilePath = "src/Database/credit_transaction.txt";
    DateTime dt = new DateTime();
    VendorOrder vo = new VendorOrder();

    public VendorUpdStatus() {

    }

    //update order status into file
    public void updateOrderFile(String orderID, String newStatus) {
        try {
            List<String> updatedOrders = new ArrayList<>();

            BufferedReader orderReader = new BufferedReader(new FileReader(orderFilePath));
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = orderReader.readLine()) != null) {
                String[] orderData = line.split(",");
                if (orderData.length > 0 && orderData[0].equals(orderID)) {
                    orderData[4] = newStatus;
                    line = String.join(",", orderData);
                }
                updatedOrders.add(line);
            }
            orderReader.close();

            BufferedWriter writer = new BufferedWriter(new FileWriter(orderFilePath));
            for (String updatedOrder : updatedOrders) {
                writer.write(updatedOrder);
                writer.newLine();
            }
            writer.close();
            generateUpdateStatusNotification(orderID, newStatus);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //generate update status notification
    public void generateUpdateStatusNotification(String orderID, String newStatus) {
        String customerUserID = vo.getCustomerUserID(orderID);
        int userID = Integer.parseInt(customerUserID);
        String dateTime = dt.getCurrentDateTime();
        String content = "";
        String foodprepared = "Order ID: " + orderID + " is now being prepared in the kitchen";
        String completed = "Order ID: " + orderID + " is completed";
        String refunded = "Order ID: " + orderID + " has been refunded";
        String category = "Order";

        if ("Food_Preparing".equals(newStatus)) {
            content = foodprepared;
        } else if ("Completed".equals(newStatus)) {
            content = completed;
        } else if ("Refunded".equals(newStatus)) {
            content = refunded;
        }

        String notifications = String.format("%d,%s,%s,%s",
                userID, content, dateTime, category);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/Database/notifications.txt", true))) {
            bw.write(notifications);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //get runnerid by orderid
    public String getRunnerID(String orderID) {
        try (BufferedReader runnerReader = new BufferedReader(new FileReader(runnerFilePath))) {
            String runnerLine;
            while ((runnerLine = runnerReader.readLine()) != null) {
                String[] runnerData = runnerLine.split(",");
                if (runnerData.length >= 3 && runnerData[2].equals(orderID)) {
                    return runnerData[1]; // Assuming RunnerID is at index 1
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "-1";
    }
}
