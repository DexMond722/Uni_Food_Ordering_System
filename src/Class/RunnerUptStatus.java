package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author User
 */
public class RunnerUptStatus extends User {

    private static final String runnertaskFilePath = "src/Database/runnertask.txt";
    private static final String userFilePath = "src/Database/users.txt";
    private static final String orderFilePath = "src/Database/order.txt";
    private static final String creditTransactionFilePath = "src/Database/credit_transaction.txt";

    public RunnerUptStatus() {
 
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

    public List<List<String>> getAcceptedOrderDetails(String runnerID) {
        List<List<String>> acceptedOrderDetailsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(runnertaskFilePath))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assuming the order of columns in the file is TaskID,RunnerID,OrderID,VendorID,TaskStatus
                if (values.length >= 5) {
                    String taskRunnerID = values[1].trim();
                    if (taskRunnerID.equals(runnerID)) {
                        int orderID = Integer.parseInt(values[2].trim());
                        String taskStatus = values[4].trim();

                        // Check if TaskStatus is "Accepted" before adding the Order details to the list
                        if ("Accepted".equals(taskStatus)) {
                            List<String> orderDetails = getOrderItemDetails(orderID);
                            if (orderDetails != null) {
                                acceptedOrderDetailsList.add(orderDetails);
                                System.out.println(orderDetails);
                            } else {
                                System.out.println("Order details not found for ID: " + orderID);
                            }
                        } else {
                            System.out.println("Order ID declined: " + orderID);
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return acceptedOrderDetailsList;
    }

    // get all order id accepted by runner
//  public List<Integer> getAcceptedOrderIDs(String runnerID) {
//        List<Integer> acceptedOrderIDs = new ArrayList<>();
//        try (BufferedReader br = new BufferedReader(new FileReader(runnertaskFilePath))) {
//            String line;
//            br.readLine();
//
//            while ((line = br.readLine()) != null) {
//                String[] values = line.split(",");
//                // Assuming the order of columns in the file is TaskID,RunnerID,OrderID,VendorID,TaskStatus
//                if (values.length >= 5){
//                    int orderID = Integer.parseInt(values[2].trim());
//                    String taskStatus = values[4].trim();
//
//                    // Check if TaskStatus is "Accepted" and add the OrderID to the list
//                    if (taskStatus.equals("Accepted")) {
//                        acceptedOrderIDs.add(orderID);
//                    }
//                }
//            }
//        } catch (IOException | NumberFormatException e) {
//            e.printStackTrace();
//        }
//
//        return acceptedOrderIDs;
//    }
//  
    public List<Integer> getAcceptedDeliveryOrderIDs(String orderFilePath) {
        List<Integer> acceptedDeliveryOrderIDs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
            String line;
            // Skip the header line if it exists
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assuming the order of columns in the file is OrderID,OrderPlacementTime,OrderItemID,OrderAmount,OrderStatus,CustomerUserID,VendorUserID,ServiceType,TransactionID
                int orderID = Integer.parseInt(values[0].trim());
                String orderStatus = values[4].trim();
                String serviceType = values[7].trim();

                // Check if OrderStatus is "Accepted" and ServiceType is "Delivery" and add the OrderID to the list
                if ("Accepted".equalsIgnoreCase(orderStatus) && "Delivery".equalsIgnoreCase(serviceType)) {
                    acceptedDeliveryOrderIDs.add(orderID);
                }
            }
        } catch (IOException | NumberFormatException e) {

        }

        return acceptedDeliveryOrderIDs;
    }

    public List<String> getOrderItemDetails(int orderID) {
        try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
            String line;
            // Skip the header line if it exists
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int currentOrderID = Integer.parseInt(values[0].trim());

                if (currentOrderID == orderID) {
                    return List.of(values);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return null; // Return null if order details are not found
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

    public String getVendorID(String orderID) {
        try (BufferedReader br = new BufferedReader(new FileReader(orderFilePath))) {
            String line;
            br.readLine(); // Skip header line

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                // Assuming the order of columns in the file is OrderID,OrderPlacementTime,OrderItemID,OrderAmount,OrderStatus,CustomerUserID,VendorUserID,ServiceType,TransactionID
                if (values.length >= 8) {
                    String currentOrderID = values[0].trim();
                    if (currentOrderID.equals(orderID)) {
                        return values[6].trim(); // Return the VendorID
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null; // Return null if the order ID is not found (handle appropriately in your code)
    }

    public void createCreditTransaction(String runnerID, String orderID) {
        double orderAmount = 4.00;
        try {
            String transactionID = generateTransactionID();
            String vendorID = getVendorID(orderID);
            String currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            BufferedWriter transactionWriter = new BufferedWriter(new FileWriter(creditTransactionFilePath, true));
            String newTransaction = transactionID + "," + runnerID + "," + orderAmount + ","
                    + currentDateTime + ",Debit,Delivery Fee Received";
            transactionWriter.write(newTransaction);
            transactionWriter.newLine();

            int lastTransactionID = Integer.parseInt(transactionID.substring(transactionID.length() - 5)) + 1;
            String lastTransactionId = String.format("T%05d", lastTransactionID);
            String creditTransaction = lastTransactionId + "," + vendorID + "," + orderAmount + ","
                    + currentDateTime + ",Credit,Delivery fees";
            transactionWriter.write(creditTransaction);
            transactionWriter.newLine();
            transactionWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void updateRunnerandVendorCredit(int runnerUserID, int vendorUserID, Boolean flag) {
        double runnerUpdatedCredit = getRunnerUpdatedCredit(runnerUserID, flag);
        double vendorUpdatedCredit = getVendorUpdatedCredit(vendorUserID, flag);
        updateCreditInFile(runnerUserID, runnerUpdatedCredit);
        updateCreditInFile(vendorUserID, vendorUpdatedCredit);
    }

    // updated credit for a customer
    private double getVendorUpdatedCredit(int vendorID, Boolean flag) {
        double totalAmount = 4.00;
        try {
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

    // updated credit for a vendor
    private double getRunnerUpdatedCredit(int runnerID, Boolean flag) {
        double totalAmount = 4.00;
        try {
            // Read the content of the file
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (flag) {
                    if (fields.length > 0 && Integer.parseInt(fields[0]) == runnerID) {
                        double originalCredit = Double.parseDouble(fields[4]);
                        double updatedCredit = originalCredit + totalAmount;
                        return updatedCredit;
                    }
                } else {
                    if (fields.length > 0 && Integer.parseInt(fields[0]) == runnerID) {
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
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath));
            writer.write(content.toString());
            // Close the writer
            writer.close();
        } catch (IOException | NumberFormatException e) {
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
            java.util.logging.Logger.getLogger(RunnerUptStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(RunnerUptStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        return lastTransactionID;
    }
}
