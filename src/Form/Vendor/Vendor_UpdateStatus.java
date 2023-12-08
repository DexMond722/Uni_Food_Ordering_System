/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.Vendor;

import Class.VendorOrder;
import Class.VendorUpdStatus;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Vendor_UpdateStatus extends javax.swing.JFrame {

    private VendorOrder vendorOrder;
    private VendorUpdStatus vendorUpdStatus;
    private String username;

    public Vendor_UpdateStatus(String username) {
        initComponents();
        this.username = username;
        vendorOrder = new VendorOrder(username);
        vendorUpdStatus = new VendorUpdStatus();
        displayOrder(vendorOrder.getVendorOrder(username));
        preventMenuEdited();

        
    }

    private void displayOrder(List<List<String>> orderItems) {
        DefaultTableModel model = (DefaultTableModel) table_OrderStatus.getModel();
        model.setRowCount(0);
        table_OrderStatus.setFont(new Font("Segoe UI", Font.PLAIN, 13));


        for (List<String> orderItem : orderItems) {
            String orderStatus = orderItem.get(4); // Assuming OrderStatus is at index 4
            String serviceType = orderItem.get(7);

            if (("Accepted".equals(orderStatus) || "Food_Preparing".equals(orderStatus))
                && ("DineIn".equals(serviceType) || "TakeAway".equals(serviceType))
                || (("No_Runner".equals(orderStatus) || "Delivered".equals(orderStatus))
                && "Delivery".equals(serviceType))) {
                
                List<String> displayData = new ArrayList<>();
                displayData.add(orderItem.get(0));  // OrderID
                displayData.add(orderItem.get(1));  // OrderPlacementTime
                displayData.add(orderItem.get(2));  // OrderItemID
                displayData.add(orderItem.get(3));  // OrderAmount
                displayData.add(orderStatus);       // OrderStatus
                displayData.add(orderItem.get(5));  // CustomerUserID
                displayData.add(serviceType);       // ServiceType
                model.addRow(displayData.toArray());
            }
        }
    }

    private void updateOrderStatus() {
        int selectedRow = table_OrderStatus.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) table_OrderStatus.getModel();
            String orderID = (String) model.getValueAt(selectedRow, 0); // Assuming OrderID is at index 0
            String orderStatus = (String) model.getValueAt(selectedRow, 4); // Assuming OrderStatus is at index 4
            String serviceType = (String) model.getValueAt(selectedRow, 6); // Assuming ServiceType is at index 6

            if ("Accepted".equals(orderStatus) && ("DineIn".equals(serviceType) || "TakeAway".equals(serviceType))) {
                model.setValueAt("Food_Preparing", selectedRow, 4); // Updating OrderStatus to Food_Preparing

                vendorUpdStatus.updateOrderFile(orderID, "Food_Preparing");
                JOptionPane.showMessageDialog(this, "Status Updated");

            } else if ("Food_Preparing".equals(orderStatus) && ("DineIn".equals(serviceType) || "TakeAway".equals(serviceType))) {
                model.setValueAt("Completed", selectedRow, 4); // Updating OrderStatus to Order Completed

                vendorUpdStatus.updateOrderFile(orderID, "Completed");
                JOptionPane.showMessageDialog(this, "Order Completed");

            } else if ("Delivered".equals(orderStatus) && ("Delivery".equals(serviceType))) {
                model.setValueAt("Completed", selectedRow, 4); // Updating OrderStatus to Order Completed

                vendorUpdStatus.updateOrderFile(orderID, "Completed");
                JOptionPane.showMessageDialog(this, "Order Completed");

            } else if ("No_Runner".equals(orderStatus) && ("Delivery".equals(serviceType))) {
                model.setValueAt("Refunded", selectedRow, 4); // Updating OrderStatus to Order Completed

                vendorUpdStatus.updateOrderFile(orderID, "Refunded");
                JOptionPane.showMessageDialog(this, "Order Refunded");
            }

        } else {
            JOptionPane.showMessageDialog(this, "Please select a specific row to update status");
        }
    }

    private void refreshOrderTable() {
        DefaultTableModel model = (DefaultTableModel) table_OrderStatus.getModel();
        model.setRowCount(0); // Clear the existing rows

        List<List<String>> orderItems = vendorOrder.getVendorOrder(username);
        displayOrder(orderItems);
    }

    private void preventMenuEdited() {
        table_OrderStatus.setDefaultEditor(Object.class, null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_OrderStatus = new javax.swing.JTable();
        btn_UpdateStatus = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(700, 500));
        setMinimumSize(new java.awt.Dimension(700, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(700, 500));

        jPanel1.setMaximumSize(new java.awt.Dimension(700, 500));
        jPanel1.setMinimumSize(new java.awt.Dimension(700, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 500));
        jPanel1.setLayout(null);

        table_OrderStatus.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "OrderID", "PlacementTime", "OrderItemID", "Amount", "OrderStatus", "CustomerID", "ServiceType"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_OrderStatus.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_OrderStatus);
        if (table_OrderStatus.getColumnModel().getColumnCount() > 0) {
            table_OrderStatus.getColumnModel().getColumn(0).setResizable(false);
            table_OrderStatus.getColumnModel().getColumn(0).setPreferredWidth(40);
            table_OrderStatus.getColumnModel().getColumn(1).setResizable(false);
            table_OrderStatus.getColumnModel().getColumn(1).setPreferredWidth(110);
            table_OrderStatus.getColumnModel().getColumn(2).setResizable(false);
            table_OrderStatus.getColumnModel().getColumn(2).setPreferredWidth(40);
            table_OrderStatus.getColumnModel().getColumn(3).setResizable(false);
            table_OrderStatus.getColumnModel().getColumn(3).setPreferredWidth(30);
            table_OrderStatus.getColumnModel().getColumn(4).setResizable(false);
            table_OrderStatus.getColumnModel().getColumn(5).setResizable(false);
            table_OrderStatus.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(20, 60, 660, 300);

        btn_UpdateStatus.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btn_UpdateStatus.setText("Update Status");
        btn_UpdateStatus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_UpdateStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_UpdateStatusActionPerformed(evt);
            }
        });
        jPanel1.add(btn_UpdateStatus);
        btn_UpdateStatus.setBounds(190, 400, 300, 50);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 153, 255));
        jLabel1.setText("Update Order Status");
        jLabel1.setToolTipText("");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(270, 20, 180, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_UpdateStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_UpdateStatusActionPerformed
        int selectedRow = table_OrderStatus.getSelectedRow();
        if (selectedRow != -1) {
            String orderStatus = (String) table_OrderStatus.getValueAt(selectedRow, 4);
            if (orderStatus.equals("No_Runner")) {
                String orderID = (String) table_OrderStatus.getValueAt(selectedRow, 0); // Assuming OrderID is in the first column
                String customerID = (String) table_OrderStatus.getValueAt(selectedRow, 5);
                int vendorID = Integer.parseInt(vendorUpdStatus.getVendorID(orderID));
                String orderAmount = vendorOrder.getOrderAmount(orderID);
                Double doubleAmount = Double.valueOf(vendorOrder.getOrderAmount(orderID));

                updateOrderStatus();
                vendorUpdStatus.createCreditTransaction(orderID, orderAmount);
                vendorUpdStatus.updateCustomerandVendorCredit(customerID, vendorID, doubleAmount, true);
            } else if (orderStatus.equals("Accepted") || orderStatus.equals("Food_Preparing") || orderStatus.equals("Delivered")) {
                updateOrderStatus();
            }

            refreshOrderTable();
        }

    }//GEN-LAST:event_btn_UpdateStatusActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(Vendor_UpdateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendor_UpdateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendor_UpdateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendor_UpdateStatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendor_UpdateStatus("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_UpdateStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_OrderStatus;
    // End of variables declaration//GEN-END:variables
}
