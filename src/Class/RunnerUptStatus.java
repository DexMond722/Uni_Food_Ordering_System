package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class RunnerUptStatus extends User {

    private static final String runnertaskFilePath = "src/Database/runnertask.txt";
    private static final String userFilePath = "src/Database/users.txt";
    private static final String orderFilePath = "src/Database/order.txt";
    private static final String creditTransactionFilePath = "src/Database/credit_transaction.txt";
    DateTime dt = new DateTime();
    UserCredit uc = new UserCredit();
    CustomerCredit cc = new CustomerCredit();

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
                            String orderStatus = orderDetails.get(4);
                            if (orderDetails != null) {
                                if (orderStatus.equals("Accepted") || orderStatus.equals("Pick-Up")) {
                                    acceptedOrderDetailsList.add(orderDetails);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        return acceptedOrderDetailsList;
    }

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
            String transactionID = uc.generateTransactionID();
            String vendorID = getVendorID(orderID);
            String currentDateTime = dt.getCurrentDateTime();

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
        cc.updateCreditInFile(runnerUserID, runnerUpdatedCredit);
        cc.updateCreditInFile(vendorUserID, vendorUpdatedCredit);
    }

    public void generatePickUpNotification(int userId, String orderID) {
        String dateTime = dt.getCurrentDateTime();
        String content = "Order ID: " + orderID + " has been picked up by Runner";
        String category = "Order";

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

    public void generateDeliveredNotification(int userId, String orderID) {
        String dateTime = dt.getCurrentDateTime();
        String content = "Order ID: " + orderID + " has been delivered";
        String category = "Order";

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

    public void generateDebitFeeNotification(int userId, String orderID) {
        String dateTime = dt.getCurrentDateTime();
        String content = "Delivery Fee for Order ID: " + orderID + " has been debited";
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

    public String getUserID(String orderID) {
        String userID = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(orderFilePath));
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] orderData = line.split(",");
                if (orderData[0].equals(orderID)) {
                    userID = orderData[6];
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userID;
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
}
