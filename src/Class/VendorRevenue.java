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

/**
 *
 * @author User
 */
public class VendorRevenue {

    private String username;
    private static final String creditTransactionFilePath = "src/Database/credit_transaction.txt";
    private static final String userFilePath = "src/Database/users.txt";

    public VendorRevenue(String username) {
        this.username = username;
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

    public List<List<String>> getDebitTransaction(String vendorName) {
        List<List<String>> debitTransactions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(creditTransactionFilePath));
            reader.readLine(); // Skipping the header
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> transactionDetails = Arrays.asList(line.split(","));
                String transactionType = transactionDetails.get(4); // Assuming TransactionType is at index 4
                String transactionUserID = transactionDetails.get(1); // Assuming UserID is at index 1

                if (transactionType.equals("Debit") && transactionUserID.equals(String.valueOf(getVendorUserIdByUsername(vendorName)))) {
                    debitTransactions.add(transactionDetails);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return debitTransactions;
    }

    public List<List<String>> getCreditTransaction(String vendorName) {
        List<List<String>> creditTransactions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(creditTransactionFilePath));
            reader.readLine(); // Skipping the header
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> creditTransactionDetails = Arrays.asList(line.split(","));
                String transactionType = creditTransactionDetails.get(4); // Assuming TransactionType is at index 4
                String transactionUserID = creditTransactionDetails.get(1); // Assuming UserID is at index 1

                if (transactionType.equals("Credit") && transactionUserID.equals(String.valueOf(getVendorUserIdByUsername(vendorName)))) {
                    creditTransactions.add(creditTransactionDetails);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return creditTransactions;
    }

    public double getDebitTransactionAmount(String vendorName) {
        double debitAmount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(creditTransactionFilePath));
            reader.readLine(); // Skipping the header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] transactionDetails = line.split(",");
                String transactionType = transactionDetails[4]; // Assuming TransactionType is at index 4
                String transactionUserID = transactionDetails[1]; // Assuming UserID is at index 1
                String transactionAmountS = transactionDetails[2]; // Assuming TransactionAmount is at index 2
                if (transactionType.equals("Debit") && transactionUserID.equals(String.valueOf(getVendorUserIdByUsername(vendorName)))) {
                    double transactionAmount = Double.parseDouble(transactionAmountS); // Parse transaction amount to int
                    debitAmount += transactionAmount; // Accumulate transaction amount for the user
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return debitAmount;
    }
    
    public double getCreditTransactionAmount(String vendorName) {
        double creditAmount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(creditTransactionFilePath));
            reader.readLine(); // Skipping the header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] transactionDetails = line.split(",");
                String transactionType = transactionDetails[4]; // Assuming TransactionType is at index 4
                String transactionUserID = transactionDetails[1]; // Assuming UserID is at index 1
                String transactionAmountS = transactionDetails[2]; // Assuming TransactionAmount is at index 2
                if (transactionType.equals("Credit") && transactionUserID.equals(String.valueOf(getVendorUserIdByUsername(vendorName)))) {
                    double transactionAmount = Double.parseDouble(transactionAmountS); // Parse transaction amount to int
                    creditAmount += transactionAmount; // Accumulate transaction amount for the user
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
        return creditAmount;
    }

}
