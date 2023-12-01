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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Goh Ee Cheng
 */
public class CustomerCredit extends UserCustomer {
    
    // File Path
    private static final String userFilePath = "src/Database/users.txt";
    private static final String creditTransactionFilePath = "src/Database/credit_transaction.txt";
    
    public CustomerCredit(int id, String username, String password, String role, double credit) {
        super(id, username, password, role, credit);
    }

    public CustomerCredit() {
    }
    
    // get Credit of Customer
    public double getCustomerCredit(String userName){
        double credit = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line; 
            while((line = reader.readLine()) != null){
                String[] creditData = line.split(",");
                String customerName = creditData[1];
                if (customerName.equals(userName)){
                    credit = Double.parseDouble(creditData[4].trim());
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
    
    // check credit
    public boolean checkCredit(double totalAmount, String username){
        // get Customer Credit 
        double availableCredit = getCustomerCredit(username);
        return availableCredit >= totalAmount;          
    }
    
    // update customer and vendor credit after placing an order
    public void updateCustomerandVendorCredit(int customerUserID, int vendorUserID, double totalAmount){
        double customerUpdatedCredit = getCustomerUpdatedCredit(customerUserID,totalAmount);
        double vendorUpdatedCredit = getVendorUpdatedCredit(vendorUserID,totalAmount);
        updateCreditInFile(customerUserID, customerUpdatedCredit);
        updateCreditInFile(vendorUserID, vendorUpdatedCredit);
    }
    
    // updated credit for a customer
    private double getCustomerUpdatedCredit(int customerID, double totalAmount) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0 && Integer.parseInt(fields[0]) == customerID) {
                    double originalCredit = Double.parseDouble(fields[4]);
                    double updatedCredit = originalCredit - totalAmount;
                    return updatedCredit;
                }
            }
            reader.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // updated credit for a vendor
    private double getVendorUpdatedCredit(int vendorID, double totalAmount) {
        try {
            // Read the content of the file
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0 && Integer.parseInt(fields[0]) == vendorID) {
                    double originalCredit = Double.parseDouble(fields[4]);
                    double updatedCredit = originalCredit + totalAmount;
                    return updatedCredit;
                }
            }
            reader.close();

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    // update credit information in the user file
    private void updateCreditInFile(int userID, double updatedCredit) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 0 && Integer.parseInt(fields[0]) == userID) {
                    fields[4] = String.valueOf(updatedCredit);
                }
                String modifiedLine = String.join(",", fields);
                content.append(modifiedLine).append("\n");
            }
            // Close the reader
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath));
            writer.write(content.toString());
            // Close the writer
            writer.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
 
    // write customer transaction data into credit_transaction.txt file
    public void generateCustomerTransactionData(String transactionID, int customerID, int vendorID, double transactionAmount, String dateTime, String serviceType) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(creditTransactionFilePath, true))) {
            String transactionCustomerData = transactionID + "," + customerID + "," + transactionAmount + "," + dateTime + "," + "Credit" + "," + serviceType;
            writer.write(transactionCustomerData);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String lastTransactionID = generateLastTransactionID();
        String transactionVendorData = transactionID + "," + vendorID + "," + transactionAmount + "," + dateTime + "," + "Debit" + "," + "Payment Received";
        generateVendorTransactionData(transactionVendorData);
    }

    // write vendor transaction data into credit_transaction.txt file
    private void generateVendorTransactionData(String transactionVendorData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(creditTransactionFilePath, true))) {
            writer.write(transactionVendorData);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public String generateLastTransactionID() {
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
            Logger.getLogger(CustomerCredit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lastTransactionID;
    }

    

}

