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


public class Review {
    private static final String reviewFilePath = "src/Database/review.txt";
    
    public Review(){
        
    }
    
    // generate ReviewID
    public String generateReviewID() {
        String reviewID = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(reviewFilePath));
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                reviewID = line.split(",")[0];
            }
            if (reviewID != null && reviewID.length() > 0) {
                int transactionID = Integer.parseInt(reviewID.substring(reviewID.length() - 5)) + 1;
                reviewID = String.format("R%05d", transactionID);
            } else {
                reviewID = "R00001";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CustomerCredit.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CustomerCredit.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reviewID;
    }
    
    // write the Review data into review.txt file
    public void generateReviewData(String orderID, String rating, String feedback){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reviewFilePath, true))) {
            String reviewData = generateReviewID() + "," + orderID + "," + rating + "," + feedback;
            writer.write(reviewData);
            writer.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    // return true if the review exist
    public Boolean existingReview(String orderID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(reviewFilePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4){
                    String existingOrderID = parts[1];
                    if (existingOrderID.equals(orderID)) {
                        return true; 
                    }
                }
            }
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    // get the rating and feedback data
    public List<String> getRatingAndFeedbackData(String orderID){
        List<String> ratingAndFeedbackInfo = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(reviewFilePath));
            String line;
            reader.readLine();
            while((line = reader.readLine())!=null){
                String[] reviewData = line.split(",");
                if (reviewData.length > 1 && reviewData[1].equals(orderID)){
                    for (int i =2; i<reviewData.length;i++){
                        ratingAndFeedbackInfo.add(reviewData[i]);
                    }
                    break;
                }
                
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ratingAndFeedbackInfo;
    }
     
    // update review data
    public void updateReviewData(String orderID, int rating, String feedback){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(reviewFilePath));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){
                String[] reviewData = line.split(",");
                if (reviewData.length > 0 && reviewData[1].equals(orderID)){
                    // clear the data after reviewDta[3]
                    if (reviewData.length >= 4){
                        String[] tempReviewData = new String[4];
                        System.arraycopy(reviewData, 0, tempReviewData, 0, 4);
                        reviewData = tempReviewData;
                    }
                    reviewData[2] = String.valueOf(rating);
                    reviewData[3] = feedback;
                }
                String modifiedLine = String.join(",", reviewData);
                content.append(modifiedLine).append("\n");
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(reviewFilePath));
            writer.write(content.toString());
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // getRatingData
    public String getRatingData(String orderID){
        List<String> ratingAndFeedbackData = getRatingAndFeedbackData(orderID);
        return ratingAndFeedbackData.get(0);
    }
    
    // get Feedback Data
    public String getFeedbackData(String orderID) {
        String feedback = null;
        List<String> ratingAndFeedbackData = getRatingAndFeedbackData(orderID);
        List<String> feedbackData = new ArrayList<>();
        for (int i = 1; i < ratingAndFeedbackData.size(); i++) {
            feedbackData.add(ratingAndFeedbackData.get(i));
        }
        StringBuilder feedbackBuilder = new StringBuilder();
        for (int i = 1; i < ratingAndFeedbackData.size(); i++) {
            if (i > 1) {
                feedbackBuilder.append(", ");
            }
            feedbackBuilder.append(ratingAndFeedbackData.get(i));
        }
        feedback = feedbackBuilder.toString().trim();
        return feedback;
    }
    
    
}
