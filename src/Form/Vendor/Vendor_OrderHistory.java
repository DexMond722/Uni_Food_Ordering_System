/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.Vendor;

import Class.Review;
import Class.VendorOrder;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class Vendor_OrderHistory extends javax.swing.JFrame {

    private VendorOrder vendorOrder;
    private Review review;
    private final String username;

    public Vendor_OrderHistory(String username) {
        initComponents();
        this.username = username;
        this.vendorOrder = new VendorOrder(username);
        this.review = new Review();
        displayOrder(vendorOrder.getVendorOrder(username));
        JTableHeader tableHeader1 = table_AllOrder.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 12);
        tableHeader1.setFont(headerFont1);
        preventMenuEdited();

        comboBox_Status.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String selectedStatus = (String) comboBox_Status.getSelectedItem();
                updateTableBasedOnStatus(selectedStatus);
            }
        });

        comboBox_Date.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                String selectedDateInterval = (String) comboBox_Date.getSelectedItem();
                String selectedStatus = (String) comboBox_Status.getSelectedItem();
                updateTableBasedOnDate(selectedStatus, selectedDateInterval);
            }
        });

        table_AllOrder.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table_AllOrder.getSelectedRow() != -1) {
                    int selectedRowIndex = table_AllOrder.getSelectedRow();
                    String orderStatus = (String) table_AllOrder.getValueAt(selectedRowIndex, 4);
                    if (orderStatus.equals("Completed")) {
                        btn_ReviewFeedback.setEnabled(true);
                    } else {
                        btn_ReviewFeedback.setEnabled(false);
                    }
                }
            }
        });
    }

    private void displayOrder(List<List<String>> orderItems) {
        DefaultTableModel model = (DefaultTableModel) table_AllOrder.getModel();
        model.setRowCount(0);
        table_AllOrder.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        for (List<String> orderItem : orderItems) {

            List<String> displayData = new ArrayList<>();
            displayData.add(orderItem.get(0));  
            displayData.add(orderItem.get(1)); 
            displayData.add(orderItem.get(2));  
            displayData.add(orderItem.get(3)); 
            displayData.add(orderItem.get(4));  
            displayData.add(orderItem.get(5));  
            displayData.add(orderItem.get(7)); 
            model.addRow(displayData.toArray());

        }
    }

    private void updateTableBasedOnStatus(String selectedStatus) {
        if (selectedStatus.equals("All")) {
            displayOrder(vendorOrder.getVendorOrder(username));
        } else {
            List<List<String>> filteredOrders = vendorOrder.getOrderStatusFromFile(selectedStatus);
            displayOrder(filteredOrders);
        }
    }

    private void updateTableBasedOnDate(String selectedStatus, String selectedDateInterval) {
        List<List<String>> filteredOrders;

        if (selectedStatus.equals("All")) {
            filteredOrders = vendorOrder.filterOrderByDateInterval(vendorOrder.getVendorOrder(username), selectedDateInterval);
        } else {
            filteredOrders = vendorOrder.getOrderStatusFromFile(selectedStatus);
            filteredOrders = vendorOrder.filterOrderByDateInterval(filteredOrders, selectedDateInterval);
        }
        displayOrder(filteredOrders);
    }

    private void preventMenuEdited() {
        table_AllOrder.setDefaultEditor(Object.class, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_AllOrder = new javax.swing.JTable();
        btn_ReviewFeedback = new javax.swing.JButton();
        comboBox_Status = new javax.swing.JComboBox<>();
        comboBox_Date = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(750, 580));
        setMinimumSize(new java.awt.Dimension(750, 580));
        setPreferredSize(new java.awt.Dimension(750, 565));
        setSize(new java.awt.Dimension(750, 580));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(750, 580));
        jPanel1.setMinimumSize(new java.awt.Dimension(750, 580));
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 580));

        table_AllOrder.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_AllOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "PlacementTime", "OrderItemID", "Amount", "OrderStatus", "CustomerID", "ServiceType"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_AllOrder.setRowHeight(30);
        table_AllOrder.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_AllOrder);
        if (table_AllOrder.getColumnModel().getColumnCount() > 0) {
            table_AllOrder.getColumnModel().getColumn(0).setResizable(false);
            table_AllOrder.getColumnModel().getColumn(0).setPreferredWidth(30);
            table_AllOrder.getColumnModel().getColumn(1).setResizable(false);
            table_AllOrder.getColumnModel().getColumn(1).setPreferredWidth(130);
            table_AllOrder.getColumnModel().getColumn(2).setResizable(false);
            table_AllOrder.getColumnModel().getColumn(3).setResizable(false);
            table_AllOrder.getColumnModel().getColumn(4).setResizable(false);
            table_AllOrder.getColumnModel().getColumn(5).setResizable(false);
            table_AllOrder.getColumnModel().getColumn(6).setResizable(false);
        }

        btn_ReviewFeedback.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_ReviewFeedback.setText("Check Review");
        btn_ReviewFeedback.setEnabled(false);
        btn_ReviewFeedback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ReviewFeedbackActionPerformed(evt);
            }
        });

        comboBox_Status.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        comboBox_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Pending", "Accepted", "Declined", "Food_Preparing", "Delivered", "Pick-Up", "No_Runner", "Refunded", "Completed" }));
        comboBox_Status.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        comboBox_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_StatusActionPerformed(evt);
            }
        });

        comboBox_Date.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        comboBox_Date.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Daily", "Monthly", "Quarterly", "Yearly" }));
        comboBox_Date.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Order History");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_ReviewFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboBox_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(comboBox_Date, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(40, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(219, 219, 219))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBox_Status, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBox_Date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_ReviewFeedback, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 750, 580);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ReviewFeedbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ReviewFeedbackActionPerformed
        int selectedRow = table_AllOrder.getSelectedRow();
        if (selectedRow != -1) {
            String orderStatus = (String) table_AllOrder.getValueAt(selectedRow, 4);
            String orderID = (String) table_AllOrder.getValueAt(selectedRow, 0);
            if (review.existingReview(orderID)) {
                if (orderStatus.equals("Completed")) {
                    String reviewFeedback = "";
                    List<String> reviewFeedbackData = review.getRatingAndFeedbackData(orderID);
                    for (int i = 0; i < reviewFeedbackData.size(); i++) {
                        reviewFeedback += reviewFeedbackData.get(i);
                        if (i < reviewFeedbackData.size() - 1) {
                            reviewFeedback += ", "; 
                        }
                    }
                    JOptionPane.showMessageDialog(this, reviewFeedback);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No review yet.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row first.");
        }
    }//GEN-LAST:event_btn_ReviewFeedbackActionPerformed

    private void comboBox_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBox_StatusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vendor_OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendor_OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendor_OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendor_OrderHistory.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendor_OrderHistory("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_ReviewFeedback;
    private javax.swing.JComboBox<String> comboBox_Date;
    private javax.swing.JComboBox<String> comboBox_Status;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_AllOrder;
    // End of variables declaration//GEN-END:variables
}
