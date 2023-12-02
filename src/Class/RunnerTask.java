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

    // Method to load tasks from the file
    public List<List<String>> getRunnerTask(String runnerID) {
        List<List<String>> taskItems = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> taskItem = new ArrayList<>(Arrays.asList(line.split(",")));
                if (taskItem.size() >= 2 && taskItem.get(1).trim().equals(runnerID)) {
                    taskItems.add(taskItem);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RunnerTask.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RunnerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        return taskItems;
    }

    public int getRunnerID(String username) {
        int userID = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(userFilePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                String userName = userData[1].trim();
                if (userName.equalsIgnoreCase(username)) {
                    userID = Integer.parseInt(userData[0].trim());
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RunnerTask.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RunnerTask.class.getName()).log(Level.SEVERE, null, ex);
        }
        return userID;
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

    public boolean assignTaskToRunner(String taskID, int newRunnerID) {
        List<String> updatedLines = new ArrayList<>();
        boolean taskAssigned = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                List<String> taskItem = new ArrayList<>(Arrays.asList(line.split(",")));

                if (taskItem.size() >= 1 && taskItem.get(0).trim().equals(taskID)) {
                    // Found the task, update its runner ID and status
                    taskItem.set(1, String.valueOf(newRunnerID)); // Update RunnerID
                    taskItem.set(taskItem.size() - 1, "Pending"); // Set status to "Pending"
                    taskAssigned = true;
                }

                updatedLines.add(String.join(",", taskItem));
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (taskAssigned) {
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

        return taskAssigned;
    }

    public List<Integer> getAvailableRunners() {
        List<Integer> availableRunners = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                int userID = Integer.parseInt(userData[0].trim());
                String userType = userData[3].trim();

                // Check if the user is a DeliveryRunner and has availability
                if ("DeliveryRunner".equals(userType) && userID != getRunnerID(getUsername())) {
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
}
