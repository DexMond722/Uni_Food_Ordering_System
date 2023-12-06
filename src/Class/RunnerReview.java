/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class RunnerReview extends User {

    private static final String runnertaskFilePath = "src/Database/runnertask.txt";
    private static final String userFilePath = "src/Database/users.txt";
    private static final String orderFilePath = "src/Database/order.txt";
    private static final String reviewFilePath = "src/Database/review.txt";

    public RunnerReview() {

    }

    public static int getRunnerId(int taskId) {
        int runnerId = -1; // Default value in case the task ID is not found

        try (BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath))) {
            String line;

            // Skip the header line
            reader.readLine();

            // Read each line in the file
            while ((line = reader.readLine()) != null) {
                String[] taskInfo = line.split(",");

                // Check if the TaskID matches the provided taskId
                if (taskInfo.length >= 2 && Integer.parseInt(taskInfo[0]) == taskId) {
                    runnerId = Integer.parseInt(taskInfo[1]);
                    break; // Exit the loop once the TaskID is found
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return runnerId;
    }

    // Method to get OrderIDs from runnertask.txt based on RunnerID
    public List<Integer> getOrderIdsByRunnerId(int runnerId) {
        List<Integer> orderIds = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(runnertaskFilePath))) {
            String line;

            // Skip the header line
            reader.readLine();

            // Read each line in the file
            while ((line = reader.readLine()) != null) {
                String[] taskInfo = line.split(",");

                // Check if the RunnerID matches the provided runnerId
                if (taskInfo.length >= 2 && Integer.parseInt(taskInfo[1]) == runnerId) {
                    orderIds.add(Integer.parseInt(taskInfo[2]));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return orderIds;
    }

    public List<String[]> getReviewDataByOrderID(List<Integer> orderIds) {
        List<String[]> reviewDataList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(reviewFilePath))) {
            String line;

            // Skip the header line
            reader.readLine();

            // Read each line in the file
            while ((line = reader.readLine()) != null) {
                String[] reviewInfo = line.split(",");

                // Check if the OrderID is in the provided list of order IDs
                if (reviewInfo.length >= 2 && orderIds.contains(Integer.parseInt(reviewInfo[1]))) {
                    reviewDataList.add(reviewInfo);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }

        return reviewDataList;
    }
}
