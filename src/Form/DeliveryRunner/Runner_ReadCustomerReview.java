/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.DeliveryRunner;

import Class.Review;
import Class.RunnerReview;
import Class.RunnerTask;
import Class.UserCustomer;
import java.awt.Font;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class Runner_ReadCustomerReview extends javax.swing.JFrame {

    /**
     * Creates new form Runner_ReadCustomerReview
     */
    private Review review;
    private RunnerReview runnerReview;
    private String username;
    private UserCustomer userCustomer;

    public Runner_ReadCustomerReview(String username) {
        this.username = username;
        this.userCustomer = new UserCustomer();
        initComponents();
        review = new Review();
        runnerReview = new RunnerReview();
        int runnerID = userCustomer.getUserID(username);
        runnerReview.getOrderIdsByRunnerId(runnerID);
        JTableHeader tableHeader1 = table_customerreview.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 14);
        tableHeader1.setFont(headerFont1);
        displayCustomerReviews(runnerID);
    }

    private void displayCustomerReviews(int runnerID) {
        DefaultTableModel model = (DefaultTableModel) table_customerreview.getModel();

        // Clear existing rows from the table
        model.setRowCount(0);

        // Use the getOrderIdsByRunnerId method to get the list of order IDs
        List<Integer> orderIds = runnerReview.getOrderIdsByRunnerId(runnerID);

        // Use the getReviewDataByOrderID method to get the review data for the list of order IDs
        List<String[]> reviewDataList = runnerReview.getReviewDataByOrderID(orderIds);

        // Add the review data to the table
        for (String[] reviewData : reviewDataList) {
            model.addRow(new Object[]{
                reviewData[1], // OrderID
                reviewData[2], // Rating
                reviewData[3] // Feedback
            });
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_customerreview = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(767, 483));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(767, 483));

        table_customerreview.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_customerreview.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "OrderID", "Rating", "Review"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_customerreview.setRowHeight(30);
        table_customerreview.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_customerreview);
        if (table_customerreview.getColumnModel().getColumnCount() > 0) {
            table_customerreview.getColumnModel().getColumn(0).setResizable(false);
            table_customerreview.getColumnModel().getColumn(0).setPreferredWidth(20);
            table_customerreview.getColumnModel().getColumn(1).setResizable(false);
            table_customerreview.getColumnModel().getColumn(1).setPreferredWidth(30);
            table_customerreview.getColumnModel().getColumn(2).setResizable(false);
            table_customerreview.getColumnModel().getColumn(2).setPreferredWidth(150);
        }

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 204));
        jLabel1.setText("Customer Review");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(196, 196, 196))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Runner_ReadCustomerReview("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_customerreview;
    // End of variables declaration//GEN-END:variables
}
