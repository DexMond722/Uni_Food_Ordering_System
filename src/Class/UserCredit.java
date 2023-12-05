/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author desmondcwf
 */
public class UserCredit extends UserManager {

    private UserManager userManager;

    public UserCredit(UserManager userManager) {
        super();
        this.userManager = userManager;
    }
    
    public UserCredit(){
        
    }

    public boolean topUpCredit(User user, double topUpAmount) {
        if (topUpAmount >= 0) {
            double currentCredit = user.getCredit();
            double updatedCredit = currentCredit + topUpAmount;
            user.setCredit(updatedCredit);

            generateTransactionRecord(user.getId(), topUpAmount);

            userManager.saveUsers();

            return true;
        }
        return false;
    }

    private void generateTransactionRecord(int userId, double transactionAmount) {
        String transactionID = generateTransactionID();
        String dateTime = getCurrentDateTime();
        String transactionType = "Debit";
        String remark = "CreditTopUp";

        String transactionRecord = String.format("%s,%d,%.2f,%s,%s,%s",
                transactionID, userId, transactionAmount, dateTime, transactionType, remark);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/Database/credit_transaction.txt", true))) {
            bw.write(transactionRecord);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String generateTransactionID() {
        int nextTransactionID = getNextTransactionID();
        return String.format("T%05d", nextTransactionID);
    }

    private int getNextTransactionID() {
        int lastTransactionID = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("src/Database/credit_transaction.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0) {
                    try {
                        String numericPart = data[0].substring(1);
                        int transactionID = Integer.parseInt(numericPart);
                        lastTransactionID = Math.max(lastTransactionID, transactionID);
                    } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastTransactionID + 1;
    }

    private String getCurrentDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
    
    private void generateTransactionNotification(int userId, double transactionAmount) {
        String dateTime = getCurrentDateTime();
        String content = "You have successfully topped up " + transactionAmount + " credits";
        String category = "Credit";

        String notifications = String.format("%d,%s,%s,%s",
                userId, content, dateTime, category);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/Database/notifications.txt", true))) {
            bw.write(notifications);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayTransactions(int userId, DefaultTableModel model) {
        String filePath = "src/Database/credit_transaction.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[1].trim().equals(String.valueOf(userId))) {
                    model.addRow(new Object[]{
                        data[0], 
                        Double.parseDouble(data[2]), 
                        data[3], 
                        data[4], 
                        data[5] 
                    });
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void displayTransactionsNotifications(int userId, DefaultTableModel model) {
        String filePath = "src/Database/notifications.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2 && data[0].trim().equals(String.valueOf(userId))) {
                    model.addRow(new Object[]{
                        data[1], 
                        data[2], 
                    });
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public void displaySelectedTransactionsNotifications(int userId, String category, DefaultTableModel model) {
        String filePath = "src/Database/notifications.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 4 && data[0].trim().equals(String.valueOf(userId))) {
                    if (category == null || data[3].trim().equalsIgnoreCase(category)) {
                        model.addRow(new Object[]{
                            data[1],
                            data[2], 
                        });
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
