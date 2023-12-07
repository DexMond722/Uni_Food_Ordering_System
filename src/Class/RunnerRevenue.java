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
public class RunnerRevenue extends User {

    private static final String creditTransactionFilePath = "src/Database/credit_transaction.txt";
    private static final String userFilePath = "src/Database/users.txt";
    private String username;
    
    public RunnerRevenue(String username){
        this.username = username;
    }
    
    public String getRunnerUserIdByUsername(String runnerID) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String userName = userData[1].trim();
                String userID = userData[0].trim(); // Assuming UserID is at index 0
                if (userName.equalsIgnoreCase(runnerID)) {
                    return userID;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null; // Return -1 if the username is not found (handle appropriately in your code)
    }


    public List<List<String>> getDebitTransaction(String runnerID) {
        List<List<String>> debitTransactions = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(creditTransactionFilePath));
            reader.readLine(); // Skipping the header
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> transactionDetails = Arrays.asList(line.split(","));
                String transactionType = transactionDetails.get(4); // Assuming TransactionType is at index 4
                String transactionUserID = transactionDetails.get(1); // Assuming UserID is at index 1

                if (transactionType.equals("Debit") && transactionUserID.equals(String.valueOf(getRunnerUserIdByUsername(runnerID)))) {
                    debitTransactions.add(transactionDetails);
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return debitTransactions;
    }
}

