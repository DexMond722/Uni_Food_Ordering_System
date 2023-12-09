/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.Vendor;

import Class.VendorOrder;
import java.awt.Font;
import java.io.IOException;
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
public class Vendor_OrderManagement extends javax.swing.JFrame {

    private VendorOrder vendorOrder;
    private final String username;

    public Vendor_OrderManagement(String username) {
        initComponents();
        this.username = username;
        this.vendorOrder = new VendorOrder(username);
        DefaultTableModel tableFoodDetailsModel = (DefaultTableModel) table_FoodDetails.getModel();
        displayOrder(vendorOrder.getVendorOrder(username));
        preventMenuEdited();
        JTableHeader tableHeader1 = table_FoodDetails.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 13);
        tableHeader1.setFont(headerFont1);
        JTableHeader tableHeader2 = table_Order.getTableHeader();
        tableHeader2.setFont(headerFont1);
        table_FoodDetails.setEnabled(false);
        tableFoodDetailsModel.setRowCount(0);

        // Add ListSelectionListener to table_Order
        table_Order.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table_Order.getSelectedRow();
                    if (selectedRow != -1) {
                        String orderItemID = (String) table_Order.getValueAt(selectedRow, 2);
                        try {
                            vendorOrder.displayFoodDetails((DefaultTableModel) table_FoodDetails.getModel(), orderItemID);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    private void displayOrder(List<List<String>> orderItems) {
        DefaultTableModel model = (DefaultTableModel) table_Order.getModel();
        model.setRowCount(0);
        table_Order.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        for (List<String> orderItem : orderItems) {
            String orderStatus = orderItem.get(4); 
            if ("Pending".equals(orderStatus)) {
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
    }

    private void refreshOrderTable() {
        DefaultTableModel model = (DefaultTableModel) table_Order.getModel();
        model.setRowCount(0); 

        List<List<String>> orderItems = vendorOrder.getVendorOrder(username);
        displayOrder(orderItems);
    }

    private void refreshFoodDetailsTable() {
        DefaultTableModel foodDetailsModel = (DefaultTableModel) table_FoodDetails.getModel();
        foodDetailsModel.setRowCount(0);
    }

    private void preventMenuEdited() {
        table_Order.setDefaultEditor(Object.class, null);
        table_FoodDetails.setDefaultEditor(Object.class, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Order = new javax.swing.JTable();
        btn_Accept = new javax.swing.JButton();
        btn_Cancel = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_FoodDetails = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(750, 660));
        setMinimumSize(new java.awt.Dimension(750, 660));
        setResizable(false);
        setSize(new java.awt.Dimension(750, 660));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(750, 626));
        jPanel1.setMinimumSize(new java.awt.Dimension(750, 626));
        jPanel1.setLayout(null);

        table_Order.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_Order.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "OrderID", "PlacementTime", "OrderItemID", "Amount", "Status", "CustomerID", "ServiceType"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Order.setRowHeight(30);
        table_Order.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_Order);
        if (table_Order.getColumnModel().getColumnCount() > 0) {
            table_Order.getColumnModel().getColumn(0).setPreferredWidth(50);
            table_Order.getColumnModel().getColumn(1).setPreferredWidth(140);
            table_Order.getColumnModel().getColumn(3).setPreferredWidth(50);
            table_Order.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(40, 100, 670, 240);

        btn_Accept.setBackground(new java.awt.Color(255, 255, 153));
        btn_Accept.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_Accept.setText("Accept");
        btn_Accept.setBorder(new javax.swing.border.MatteBorder(null));
        btn_Accept.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AcceptActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Accept);
        btn_Accept.setBounds(440, 470, 100, 40);

        btn_Cancel.setBackground(new java.awt.Color(255, 51, 51));
        btn_Cancel.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_Cancel.setText("Reject");
        btn_Cancel.setBorder(new javax.swing.border.MatteBorder(null));
        btn_Cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_CancelActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Cancel);
        btn_Cancel.setBounds(590, 470, 100, 40);

        table_FoodDetails.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_FoodDetails.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "OrderItemID", "FoodName", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_FoodDetails.setRowHeight(30);
        table_FoodDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_FoodDetails);
        if (table_FoodDetails.getColumnModel().getColumnCount() > 0) {
            table_FoodDetails.getColumnModel().getColumn(0).setResizable(false);
            table_FoodDetails.getColumnModel().getColumn(0).setPreferredWidth(40);
            table_FoodDetails.getColumnModel().getColumn(1).setResizable(false);
            table_FoodDetails.getColumnModel().getColumn(1).setPreferredWidth(100);
            table_FoodDetails.getColumnModel().getColumn(2).setResizable(false);
            table_FoodDetails.getColumnModel().getColumn(2).setPreferredWidth(40);
        }

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(40, 390, 370, 210);

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel1.setText("Ordered Items Details");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 350, 220, 30);

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setText("Order Details");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 60, 190, 30);

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Order Management");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(170, 10, 430, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 643, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AcceptActionPerformed
        int selectedRow = table_Order.getSelectedRow();
        if (selectedRow != -1) {
            String orderID = (String) table_Order.getValueAt(selectedRow, 0);
            vendorOrder.assignRunnerForDelivery(orderID);
            vendorOrder.updateOrderStatus(orderID, "Accepted");
            refreshOrderTable();
            refreshFoodDetailsTable();
            JOptionPane.showMessageDialog(this, "Order Accepted");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a specific row on the Order Table");
        }
    }//GEN-LAST:event_btn_AcceptActionPerformed

    private void btn_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_CancelActionPerformed
        int selectedRow = table_Order.getSelectedRow();
        if (selectedRow != -1) {
            String orderID = (String) table_Order.getValueAt(selectedRow, 0); 
            String customerID = (String) table_Order.getValueAt(selectedRow, 5);
            int vendorID = Integer.parseInt(vendorOrder.getVendorID(orderID));
            String orderAmount = vendorOrder.getOrderAmount(orderID);
            Double doubleAmount = Double.valueOf(vendorOrder.getOrderAmount(orderID));
            vendorOrder.updateOrderStatus(orderID, "Declined");
            vendorOrder.createCreditTransaction(orderID, orderAmount);
            vendorOrder.updateCustomerandVendorCredit(customerID, vendorID, doubleAmount, true);

            refreshOrderTable();
            refreshFoodDetailsTable();
            JOptionPane.showMessageDialog(this, "Order Rejected");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a specific row on the Order Table");
        }
    }//GEN-LAST:event_btn_CancelActionPerformed

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
            java.util.logging.Logger.getLogger(Vendor_OrderManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendor_OrderManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendor_OrderManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendor_OrderManagement.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendor_OrderManagement("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Accept;
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_FoodDetails;
    private javax.swing.JTable table_Order;
    // End of variables declaration//GEN-END:variables
}
