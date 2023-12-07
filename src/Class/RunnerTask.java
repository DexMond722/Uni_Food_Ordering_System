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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class RunnerTask extends User {

    private static final String runnertaskFilePath = "src/Database/runnertask.txt";
    private static final String userFilePath = "src/Database/users.txt";
    private static final String orderFilePath = "src/Database/order.txt";
    UserCustomer uc = new UserCustomer();
    VendorOrder vo = new VendorOrder();
    DateTime dt = new DateTime();

    public RunnerTask() {

    }

    // Method to load tasks from the file
    public List<List<String>> getRunnerTask(String runnerID, Boolean flag) {
        List<List<String>> taskItems = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> taskItem = new ArrayList<>(Arrays.asList(line.split(",")));
                if (taskItem.size() >= 2 && taskItem.get(1).trim().equals(runnerID)) {
                    if (flag) {
                        if (taskItem.get(4).equals("Pending")) {
                            taskItems.add(taskItem);
                        }
                    } else {
                        taskItems.add(taskItem);
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RunnerTask.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RunnerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taskItems;
    }

    public boolean updateTaskStatus(String taskID, String newStatus) {
        List<String> updatedLines = new ArrayList<>();
        boolean taskUpdated = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> taskItem = new ArrayList<>(Arrays.asList(line.split(",")));

                if (taskItem.size() >= 1 && taskItem.get(0).trim().equals(taskID)) {
                    // Found the task, update its status
                    taskItem.set(taskItem.size() - 1, newStatus);
                    taskUpdated = true;
                }

                updatedLines.add(String.join(",", taskItem));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (taskUpdated) {
            // Write the updated lines back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(runnertaskFilePath))) {
                for (String updatedLine : updatedLines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return taskUpdated;
    }

    public void generateDeliveryAssignedNotification(int userId, String orderID) {
        String dateTime = dt.getCurrentDateTime();
        String content = "A Delivery Runner has been assigned to Order ID: " + orderID;
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

    public void declineTask(String taskID) {
        String orderID = null;
        try {
            // Read and modify the task information
            BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(taskID)) {
                    fields[4] = String.valueOf("Declined");
                    orderID = fields[2];
                }
                String modifiedLine = String.join(",", fields);
                content.append(modifiedLine).append("\n");
            }
            reader.close(); // Close the reader

            // If an order ID was found, proceed with assigning a new task
            if (orderID != null) {
                // Generate a new task ID
                int newTaskID = generateNewTaskID(runnertaskFilePath);
                List<Integer> declinedRunnerID = getDeclinedRunnerIDsForOrder(orderID);
                // Assign the new task to a randomly chosen runner
                int availableRunnerID = assignTaskToAvailableRunner(findAvailableRunnersExcluding(declinedRunnerID));
                String vendorID = getVendorIDForOrder(orderID);
                // Create a new line for the declined task

                if (availableRunnerID == -1) {
                    // No runner available, update order status to "No_runner"
                    updateOrderStatusToNoRunner(orderID);
                } else {
                    String newTaskLine = String.format("%d,%d,%s,%s,Pending\n", newTaskID, availableRunnerID, orderID, vendorID);
                    content.append(newTaskLine);

                    // Write the modified content (including the new task line) back to the file
                    BufferedWriter writer = new BufferedWriter(new FileWriter(runnertaskFilePath));
                    writer.write(content.toString());
                    writer.close(); // Close the writer

                    vo.generateTaskAssignedNotification(availableRunnerID);

                }
            }

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> getDeclinedRunnerIDsForOrder(String orderID) {
        List<Integer> declinedRunnerIDs = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> taskItem = Arrays.asList(line.split(","));

                // Check if the task has the same orderID and taskStatus is "Declined"
                if (taskItem.size() >= 3 && taskItem.get(2).trim().equals(orderID)
                        && taskItem.get(taskItem.size() - 1).trim().equals("Declined")) {
                    int runnerID = Integer.parseInt(taskItem.get(1).trim());
                    declinedRunnerIDs.add(runnerID);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return declinedRunnerIDs;
    }

    public List<Integer> findAvailableRunnersExcluding(List<Integer> declinedRunnerIDs) {
        List<Integer> availableRunners = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                int userID = Integer.parseInt(userData[0].trim());
                String userType = userData[3].trim();

                // Check if the user is a DeliveryRunner and has availability
                if ("DeliveryRunner".equals(userType) && !declinedRunnerIDs.contains(userID)) {
                    availableRunners.add(userID);
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return availableRunners;
    }

    public int assignTaskToAvailableRunner(List<Integer> availableRunners) {
        int availableRunnerID = 0;
        if (!availableRunners.isEmpty()) {
            int randomIndex = (int) (Math.random() * availableRunners.size());
            availableRunnerID = availableRunners.get(randomIndex);
        } else {
            availableRunnerID = -1;
        }
        return availableRunnerID;
    }

    private void updateOrderStatusToNoRunner(String orderID) {
        // Read and modify the order.txt file to update the order status to "No_runner"
        try {
            BufferedReader reader = new BufferedReader(new FileReader(orderFilePath));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 1 && fields[0].equals(orderID)) {
                    // Assuming OrderStatus is in the 5th column (index 4)
                    fields[4] = "No_runner";
                }
                String modifiedLine = String.join(",", fields);
                content.append(modifiedLine).append("\n");
            }
            reader.close(); // Close the reader

            // Write the modified content back to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(orderFilePath));
            writer.write(content.toString());
            writer.close(); // Close the writer

            int customerID = Integer.parseInt(vo.getCustomerUserID(orderID));
            generateNoRunnerNotification(customerID, orderID);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateNoRunnerNotification(int userId, String orderID) {
        String dateTime = dt.getCurrentDateTime();
        String content = "No runners have accepted Order ID: " + orderID;
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



    public int generateNewTaskID(String runnertaskFilePath) throws IOException {
        int newTaskID = 1; // Default value for the first TaskID

        try (BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath))) {
            // Skip the header if needed
            reader.readLine();

            // Count the existing TaskIDs
            while (reader.readLine() != null) {
                newTaskID++;
            }
        } catch (FileNotFoundException e) {
            // Handle file not found exception
            e.printStackTrace();
        } catch (IOException e) {
            // Handle other IO exceptions, including IOException from FileReader
            e.printStackTrace();
        }

        return newTaskID;
    }

    private String getVendorIDForOrder(String orderID) {
        // Read the runnertask.txt file and find the VendorID based on OrderID
        try (BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 4 && fields[2].equals(orderID)) {
                    // Assuming VendorID is in the 4th column (index 3)
                    return fields[3];
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return a default value or handle the case when the OrderID is not found
        return "DefaultVendor";
    }

    public String getUsernameForUserID(String userID) {
        String username = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String currentUserID = userData[0].trim();
                if (currentUserID.equals(userID)) {
                    username = userData[1].trim();
                    break;
                }
            }
            reader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return username;
    }

    public String getOrderIdForTaskId(String taskId) {
        try (BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length >= 1 && fields[0].trim().equals(taskId)) {
                    // Assuming OrderID is in the 3rd column (index 2)
                    return fields[2].trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Return a default value or handle the case when the TaskID is not found
        return "DefaultOrderID";
    }
}
