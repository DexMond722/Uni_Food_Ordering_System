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

    private static final String userFilePath = "src/Database/users.txt";
    private static final String orderFilePath = "src/Database/order.txt/";
    private static final String runnerFilePath = "src/Database/runnertask.txt";
    private static final String creditTransactionFilePath = "src/Database/credit_transaction.txt";

    public VendorUpdStatus() {

    }

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCustomerUserID(String orderID) {
        try (BufferedReader orderReader = new BufferedReader(new FileReader(orderFilePath))) {
            String orderLine;
            while ((orderLine = orderReader.readLine()) != null) {
                String[] orderData = orderLine.split(",");
                if (orderData[0].equals(orderID)) {
                    return orderData[5]; // Assuming CustomerUserID is at index 5
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "-1"; // Return default value if CustomerUserID is not found
    }

    public String getVendorID(String orderID) {
        try (BufferedReader orderReader = new BufferedReader(new FileReader(orderFilePath))) {
            String orderLine;
            while ((orderLine = orderReader.readLine()) != null) {
                String[] orderData = orderLine.split(",");
                if (orderData[0].equals(orderID)) {
                    return orderData[6]; // Assuming CustomerUserID is at index 5
                }
            }  
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return "-1"; // Return default value if CustomerUserID is not found
    }

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
        return "-1"; // Return default value if RunnerID is not found for the given OrderID
    }

    public String generateNewTransactionID() {
        String lastTransactionID = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(creditTransactionFilePath));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                lastTransactionID = line.split(",")[0];
            }
            if (lastTransactionID != null) {
                int transactionID = Integer.parseInt(lastTransactionID.substring(lastTransactionID.length() - 5)) + 1;
                lastTransactionID = String.format("T%05d", transactionID);
            } else {
                // If no line is found, set the default value
                lastTransactionID = "T00001";
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return lastTransactionID;
    }

    public void createCreditTransaction(String orderID, String orderAmount) {
        try {
            String transactionID = generateNewTransactionID();
            String customerUserID = getCustomerUserID(orderID);
            String vendorUserID = getVendorID(orderID);
            String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            BufferedWriter transactionWriter = new BufferedWriter(new FileWriter(creditTransactionFilePath, true));
            String newTransaction = transactionID + "," + customerUserID + "," + orderAmount + ","
                    + currentDateTime + ",Debit,Payment Refunded";
            transactionWriter.write(newTransaction);
            transactionWriter.newLine();
            
            int lastTransactionID = Integer.parseInt(transactionID.substring(transactionID.length() - 5)) + 1;
            String lastTransactionId = String.format("T%05d", lastTransactionID);
            String creditTransaction = lastTransactionId + "," + vendorUserID + "," + orderAmount + ","
                    + currentDateTime + ",Credit,Vendor Refunded";
            transactionWriter.write(creditTransaction);
            transactionWriter.newLine();
            transactionWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateCustomerandVendorCredit(String customerUserID, int vendorUserID, double totalAmount, Boolean flag) {
        double customerUpdatedCredit = getCustomerUpdatedCredit(customerUserID, totalAmount, flag);
        double vendorUpdatedCredit = getVendorUpdatedCredit(vendorUserID, totalAmount, flag);
        updateCreditInFile(customerUserID, customerUpdatedCredit);
        String vendorID = String.valueOf(vendorUserID);
        updateCreditInFile(vendorID, vendorUpdatedCredit);
    }

    public double getCustomerUpdatedCredit(String customerID, double totalAmount, Boolean flag) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (flag) {
                    if (fields.length > 0 && fields[0].equals(customerID)) {
                        double originalCredit = Double.parseDouble(fields[4]);
                        double updatedCredit = originalCredit + totalAmount;
                        return updatedCredit;
                    }
                } else {
                    if (fields.length > 0 && fields[0].equals(customerID)) {
                        double originalCredit = Double.parseDouble(fields[4]);
                        double updatedCredit = originalCredit - totalAmount;
                        return updatedCredit;
                    }
                }
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private double getVendorUpdatedCredit(int vendorID, double totalAmount, Boolean flag) {
        try {
            // Read the content of the file
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (flag) {
                    if (fields.length > 0 && Integer.parseInt(fields[0]) == vendorID) {
                        double originalCredit = Double.parseDouble(fields[4]);
                        double updatedCredit = originalCredit - totalAmount;
                        return updatedCredit;
                    }
                } else {
                    if (fields.length > 0 && Integer.parseInt(fields[0]) == vendorID) {
                        double originalCredit = Double.parseDouble(fields[4]);
                        double updatedCredit = originalCredit + totalAmount;
                        return updatedCredit;
                    }
                }
            }
            reader.close();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateCreditInFile(String userID, double updatedCredit) {
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length > 0 && userData[0].equals(userID)) {
                    userData[4] = String.valueOf(updatedCredit);
                }
                String modifiedLine = String.join(",", userData);
                content.append(modifiedLine).append("\n");
            }
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath))) {
                writer.write(content.toString());
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Or log the error
        }
    }
}
