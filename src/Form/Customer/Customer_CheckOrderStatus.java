package Form.Customer;

import Class.CustomerOrder;
import Class.CustomerCredit;
import Class.Review;
import java.awt.Font;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;

public class Customer_CheckOrderStatus extends javax.swing.JFrame {

    private CustomerDashboard customerDashboard;
    private CustomerOrder customerOrder;
    private CustomerCredit customerCredit;
    private Review review;
    private String username;
    private String orderID;
    private int userID;

    public Customer_CheckOrderStatus(String username, int userID) {
        customerCredit = new CustomerCredit();
        customerOrder = new CustomerOrder(customerCredit);
        review = new Review();
        initComponents();
        this.username = username;
        this.userID = userID;
        DefaultTableModel tableOrderHistoryModel = (DefaultTableModel) table_OrderHistory.getModel();
        clearTable();
        JTableHeader tableHeader1 = table_OrderHistory.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 14);
        tableHeader1.setFont(headerFont1);
        JTableHeader tableHeader2 = table_OrderItems.getTableHeader();
        tableHeader2.setFont(headerFont1);
        displayOrderHistory(username);

        table_OrderHistory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table_OrderHistory.getSelectedRow() != -1) {
                    int selectedRowIndex = table_OrderHistory.getSelectedRow();
                    String selectedOrderID = String.valueOf(table_OrderHistory.getValueAt(selectedRowIndex, 0));
                    orderID = selectedOrderID;
                    // if existingReview == true display rating and feedback if not do nothing
                    List<List<String>> orderItemsData = customerOrder.getOrderItemsData(orderID);
                    displayOrderItems(orderItemsData);
                    if (review.existingReview(orderID)) {
                        String rating = review.getRatingData(orderID);
                        String feedback = review.getFeedbackData(orderID);
                        spinner_OrderRating.setValue(Integer.parseInt(rating));
                        txtArea_OrderFeedback.setText(feedback);
                    } else {
                        spinner_OrderRating.setValue(0);
                        txtArea_OrderFeedback.setText("");
                    }
                    String orderStatus = (String) table_OrderHistory.getValueAt(selectedRowIndex, 3);
                    if (orderStatus.equals("Refunded")) {
                        btn_ChangingOrderStatus.setEnabled(true);
                    } else {
                        btn_ChangingOrderStatus.setEnabled(false);
                    }
                }
            }
        });
        table_OrderHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_OrderHistory = new javax.swing.JTable();
        lbl_OrderItemsDetails = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_OrderItems = new javax.swing.JTable();
        lbl_OrderHistory = new javax.swing.JLabel();
        btn_CancelOrder = new javax.swing.JButton();
        btn_Reorder = new javax.swing.JButton();
        btn_Review = new javax.swing.JButton();
        lbl_OrderRating = new javax.swing.JLabel();
        lbl_OrdeFeedback = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtArea_OrderFeedback = new javax.swing.JTextArea();
        spinner_OrderRating = new javax.swing.JSpinner();
        btn_ModifyReview = new javax.swing.JButton();
        lbl_OrderAgain = new javax.swing.JLabel();
        btn_ChangingOrderStatus = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setMinimumSize(new java.awt.Dimension(1150, 700));
        setPreferredSize(new java.awt.Dimension(1150, 730));
        setResizable(false);
        setSize(new java.awt.Dimension(1150, 700));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setMinimumSize(new java.awt.Dimension(1150, 750));
        panel.setName(""); // NOI18N
        panel.setPreferredSize(new java.awt.Dimension(1150, 750));

        table_OrderHistory.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_OrderHistory.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "OrderID", "Order Placement Time", "Order Amount", "Order Status", "Service Type"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_OrderHistory.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        table_OrderHistory.setRowHeight(30);
        table_OrderHistory.getTableHeader().setResizingAllowed(false);
        table_OrderHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_OrderHistory);
        if (table_OrderHistory.getColumnModel().getColumnCount() > 0) {
            table_OrderHistory.getColumnModel().getColumn(0).setMinWidth(35);
            table_OrderHistory.getColumnModel().getColumn(0).setPreferredWidth(35);
            table_OrderHistory.getColumnModel().getColumn(1).setPreferredWidth(140);
        }

        lbl_OrderItemsDetails.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lbl_OrderItemsDetails.setText("Order Items Details:");

        table_OrderItems.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_OrderItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "FoodName", "Quantity ", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_OrderItems.setRowHeight(30);
        table_OrderItems.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_OrderItems);
        if (table_OrderItems.getColumnModel().getColumnCount() > 0) {
            table_OrderItems.getColumnModel().getColumn(0).setResizable(false);
            table_OrderItems.getColumnModel().getColumn(1).setResizable(false);
            table_OrderItems.getColumnModel().getColumn(2).setResizable(false);
        }

        lbl_OrderHistory.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lbl_OrderHistory.setText("Order History:");

        btn_CancelOrder.setBackground(new java.awt.Color(255, 51, 51));
        btn_CancelOrder.setFont(new java.awt.Font("Georgia", 1, 13)); // NOI18N
        btn_CancelOrder.setForeground(new java.awt.Color(255, 255, 255));
        btn_CancelOrder.setText("Cancel Order");
        btn_CancelOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelOrderActionPerformed(evt);
            }
        });

        btn_Reorder.setBackground(new java.awt.Color(0, 204, 204));
        btn_Reorder.setFont(new java.awt.Font("Georgia", 1, 13)); // NOI18N
        btn_Reorder.setForeground(new java.awt.Color(255, 255, 255));
        btn_Reorder.setText("Reorder");
        btn_Reorder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Reorder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReorderActionPerformed(evt);
            }
        });

        btn_Review.setBackground(new java.awt.Color(255, 204, 102));
        btn_Review.setFont(new java.awt.Font("Georgia", 1, 13)); // NOI18N
        btn_Review.setForeground(new java.awt.Color(255, 255, 255));
        btn_Review.setText("Make Review");
        btn_Review.setBorder(new javax.swing.border.MatteBorder(null));
        btn_Review.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReviewActionPerformed(evt);
            }
        });

        lbl_OrderRating.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        lbl_OrderRating.setText("Order Rating:");

        lbl_OrdeFeedback.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        lbl_OrdeFeedback.setText("Order Feedback:");

        txtArea_OrderFeedback.setColumns(20);
        txtArea_OrderFeedback.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        txtArea_OrderFeedback.setRows(5);
        jScrollPane3.setViewportView(txtArea_OrderFeedback);

        spinner_OrderRating.setModel(new javax.swing.SpinnerNumberModel(0, 0, 5, 1));

        btn_ModifyReview.setBackground(new java.awt.Color(0, 153, 153));
        btn_ModifyReview.setFont(new java.awt.Font("Georgia", 1, 13)); // NOI18N
        btn_ModifyReview.setForeground(new java.awt.Color(255, 255, 255));
        btn_ModifyReview.setText("Modify Review");
        btn_ModifyReview.setBorder(new javax.swing.border.MatteBorder(null));
        btn_ModifyReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ModifyReviewActionPerformed(evt);
            }
        });

        lbl_OrderAgain.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        lbl_OrderAgain.setText("Order Again For Refunded Order:");

        btn_ChangingOrderStatus.setBackground(new java.awt.Color(153, 255, 153));
        btn_ChangingOrderStatus.setFont(new java.awt.Font("Georgia", 1, 13)); // NOI18N
        btn_ChangingOrderStatus.setForeground(new java.awt.Color(255, 255, 255));
        btn_ChangingOrderStatus.setText("Order Again");
        btn_ChangingOrderStatus.setBorder(new javax.swing.border.MatteBorder(null));
        btn_ChangingOrderStatus.setEnabled(false);
        btn_ChangingOrderStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChangingOrderStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(421, 421, 421)
                        .addComponent(btn_Review, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_ModifyReview, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbl_OrderHistory)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_OrderAgain)
                                    .addGroup(panelLayout.createSequentialGroup()
                                        .addComponent(btn_CancelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(btn_Reorder, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(btn_ChangingOrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbl_OrderItemsDetails)
                            .addComponent(lbl_OrdeFeedback)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(lbl_OrderRating)
                                .addGap(18, 18, 18)
                                .addComponent(spinner_OrderRating, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_OrderHistory)
                    .addComponent(lbl_OrderItemsDetails))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbl_OrderRating)
                            .addComponent(spinner_OrderRating, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(lbl_OrdeFeedback)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_ModifyReview, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Review, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Reorder, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_CancelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lbl_OrderAgain)
                .addGap(18, 18, 18)
                .addComponent(btn_ChangingOrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayOrderHistory(String username) {
        DefaultTableModel model = (DefaultTableModel) table_OrderHistory.getModel();
        model.setRowCount(0);
        List<List<String>> orderHistoryData = new ArrayList<>();
        orderHistoryData = customerOrder.getOrderHistoryData(username);
        for (List<String> orderInfo : orderHistoryData) {
            model.addRow(orderInfo.toArray());
        }
    }

    private void displayOrderItems(List<List<String>> orderItemsData) {
        DefaultTableModel model = (DefaultTableModel) table_OrderItems.getModel();
        model.setRowCount(0);
        for (List<String> orderItems : orderItemsData) {
            model.addRow(orderItems.toArray());
        }

    }

    private void clearTable() {
        DefaultTableModel tableOrderItemsModel = (DefaultTableModel) table_OrderItems.getModel();
        tableOrderItemsModel.setRowCount(0);
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        customerDashboard = new CustomerDashboard(username, userID);
        customerDashboard.setLocationRelativeTo(null);
        customerDashboard.setVisible(true);
        dispose();
    }//GEN-LAST:event_formWindowClosing

    private void btn_CancelOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelOrderActionPerformed
        int selectedRowIndex = table_OrderHistory.getSelectedRow();
        if (selectedRowIndex != -1) {
            String orderID = table_OrderHistory.getValueAt(selectedRowIndex, 0).toString();
            String orderStatus = table_OrderHistory.getValueAt(selectedRowIndex, 3).toString();
            switch (orderStatus) {
                case "Pending":
                    customerOrder.cancelOrder(orderID);
                    JOptionPane.showMessageDialog(this, "Order has been cancelled.", "Order Cancelled", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Cancelled":
                    JOptionPane.showMessageDialog(this, "Order is already cancelled.", "Order Cancelled", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Pick-Up":
                    JOptionPane.showMessageDialog(this, "Order is delivering.", "Order Delivered", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "No_Runner":
                    JOptionPane.showMessageDialog(this, "No Runner Delivers This Order.", "No Runner", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Refunded":
                    JOptionPane.showMessageDialog(this, "No Runner Delivers This Order.", "No Runner", JOptionPane.INFORMATION_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "The food is prepared by the vendor, so the order cannot be cancelled.", "Order Cannot be Cancelled", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order.", "No Order Selected", JOptionPane.INFORMATION_MESSAGE);
        }
        displayOrderHistory(username);
        clearTable();
    }//GEN-LAST:event_btn_CancelOrderActionPerformed

    private void btn_ReorderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReorderActionPerformed
        int selectedRowIndex = table_OrderHistory.getSelectedRow();
        if (selectedRowIndex != -1) {
            String orderID = table_OrderHistory.getValueAt(selectedRowIndex, 0).toString();
            int customerID = Integer.parseInt(customerOrder.getCustomerID(orderID));
            int vendorID = Integer.parseInt(customerOrder.getVendorID(orderID));
            double orderAmount = Double.parseDouble(table_OrderHistory.getValueAt(selectedRowIndex, 2).toString());
            String serviceType = table_OrderHistory.getValueAt(selectedRowIndex, 4).toString();
            if (customerCredit.checkCredit(orderAmount, username)) {
                if (serviceType.equals("Delivery")) {
                    orderAmount -= 4.00;
                }
                List<List<String>> orderItemsData = customerOrder.getOrderItemsDataForReorder(orderID);
                customerOrder.placeOrder(orderAmount, customerID, vendorID, serviceType, orderItemsData);
            } else {
                JOptionPane.showMessageDialog(this, "Please top up credit, credit is not enough for the order", "Credit Insufficient", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order.", "No Order Selected", JOptionPane.INFORMATION_MESSAGE);
        }
        displayOrderHistory(username);
    }//GEN-LAST:event_btn_ReorderActionPerformed

    private void btn_ReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReviewActionPerformed
        int selectedRowIndex = table_OrderHistory.getSelectedRow();
        if (selectedRowIndex != -1) {
            String orderID = table_OrderHistory.getValueAt(selectedRowIndex, 0).toString();
            String orderStatus = table_OrderHistory.getValueAt(selectedRowIndex, 3).toString();
            if (orderStatus.equals("Completed")) {
                provideReview(orderID);
            } else if (orderStatus.equals("Delivered")) {
                JOptionPane.showMessageDialog(this, "The Order is Delivered", "Order Delivered", JOptionPane.INFORMATION_MESSAGE);
            } else if (orderStatus.equals("Cancelled") || orderStatus.equals("Declined")) {
                JOptionPane.showMessageDialog(this, "The Order is Cancelled", "Order Cancelled", JOptionPane.INFORMATION_MESSAGE);
            } else if (orderStatus.equals("No_Runner")) {
                JOptionPane.showMessageDialog(this, "No Runner Accept the Task", "No Runner", JOptionPane.INFORMATION_MESSAGE);
            } else if (orderStatus.equals("Refunded")) {
                JOptionPane.showMessageDialog(this, "The Order Payment is Refunded", "Payment Refunded", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "The Order Doesn't Completed", "Order No Completed", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order.", "No Order Selected", JOptionPane.INFORMATION_MESSAGE);
        }

        clearTable();
    }//GEN-LAST:event_btn_ReviewActionPerformed

    private void btn_ModifyReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ModifyReviewActionPerformed
        int selectedRowIndex = table_OrderHistory.getSelectedRow();
        if (selectedRowIndex != -1) {
            String orderID = table_OrderHistory.getValueAt(selectedRowIndex, 0).toString();
            int rating = (Integer) spinner_OrderRating.getValue();
            String feedback = (String) txtArea_OrderFeedback.getText();
            if (rating != 0 && feedback != null) {
                review.updateReviewData(orderID, rating, feedback);
                JOptionPane.showMessageDialog(this, "Successfully Review Modification", "Successfully Review Modification", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "You Do Not Provide Review For This Order", "No Review", JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an order.", "No Order Selected", JOptionPane.INFORMATION_MESSAGE);
        }


    }//GEN-LAST:event_btn_ModifyReviewActionPerformed

    private void btn_ChangingOrderStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChangingOrderStatusActionPerformed
        int selectedRowIndex = table_OrderHistory.getSelectedRow();
        if (selectedRowIndex != -1) {
            String orderID = table_OrderHistory.getValueAt(selectedRowIndex, 0).toString();
            Object[] options = {"Dine In", "Take Away", "No"};
            int result = JOptionPane.showOptionDialog(
                    this,
                    "Do you want to change service type of this order or dont want this order",
                    "Options",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    options,
                    options[2]);
            String serviceType = null;
            if (result == 0) {
                serviceType = "DineIn";
                replaceRefundedOrder(selectedRowIndex, serviceType);
            } else if (result == 1) {
                serviceType = "TakeAway";
                replaceRefundedOrder(selectedRowIndex, serviceType);
            } else if (result == 2) {
                customerOrder.updateOrderDataToSettled(orderID);
            } else {
                return;
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please select an order.", "No Order Selected", JOptionPane.INFORMATION_MESSAGE);
        }
        displayOrderHistory(username);
    }//GEN-LAST:event_btn_ChangingOrderStatusActionPerformed

    private void replaceRefundedOrder(int selectedRowIndex, String serviceType) {
        int customerID = Integer.parseInt(customerOrder.getCustomerID(orderID));
        int vendorID = Integer.parseInt(customerOrder.getVendorID(orderID));
        double orderAmount = Double.parseDouble(table_OrderHistory.getValueAt(selectedRowIndex, 2).toString()) - 4.00;
        List<List<String>> orderItemsData = customerOrder.getOrderItemsDataForReorder(orderID);
        customerOrder.updateOrderDataToSettled(orderID);
        customerOrder.placeOrder(orderAmount, customerID, vendorID, serviceType, orderItemsData);
    }

    private void provideReview(String orderID) {
        if (review.existingReview(orderID)) {
            JOptionPane.showMessageDialog(this, "You Have Provided Review To This Order Already, But You Can Modify Your Review At The Right Side", "Review Provided", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String ratingString = (String) JOptionPane.showInputDialog(this, "Enter your rating (1-5):");
            if (ratingString.equals("")) {
                ratingString = "0";
            }
            String feedback = (String) JOptionPane.showInputDialog(this, "Enter your feedback:");
            review.generateReviewData(orderID, ratingString, feedback);
            spinner_OrderRating.setValue(Integer.parseInt(ratingString));
            txtArea_OrderFeedback.setText(feedback);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_CheckOrderStatus("", -1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CancelOrder;
    private javax.swing.JButton btn_ChangingOrderStatus;
    private javax.swing.JButton btn_ModifyReview;
    private javax.swing.JButton btn_Reorder;
    private javax.swing.JButton btn_Review;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_OrdeFeedback;
    private javax.swing.JLabel lbl_OrderAgain;
    private javax.swing.JLabel lbl_OrderHistory;
    private javax.swing.JLabel lbl_OrderItemsDetails;
    private javax.swing.JLabel lbl_OrderRating;
    private javax.swing.JPanel panel;
    private javax.swing.JSpinner spinner_OrderRating;
    private javax.swing.JTable table_OrderHistory;
    private javax.swing.JTable table_OrderItems;
    private javax.swing.JTextArea txtArea_OrderFeedback;
    // End of variables declaration//GEN-END:variables
}
