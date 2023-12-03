package Class;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    
    public Boolean existingReview(String orderID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(reviewFilePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String existingOrderID = parts[1];
                if (existingOrderID.equals(orderID)) {
                    return true; 
                }
            }
        } catch (FileNotFoundException ex) { 
            Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Review.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
}
