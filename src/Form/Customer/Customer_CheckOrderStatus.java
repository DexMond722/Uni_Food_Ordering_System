package Form.Customer;

import Class.User;
import Class.UserCustomer;
import Class.CustomerOrder;
import Class.CustomerCredit;
import Form.Customer.CustomerDashboard;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;


public class Customer_CheckOrderStatus extends javax.swing.JFrame {

    private CustomerDashboard customerDashboard;
    private CustomerOrder customerOrder;
    private CustomerCredit customerCredit;
    private String username;
    private String orderID;
    public Customer_CheckOrderStatus(String username) {
        customerCredit = new CustomerCredit();
        customerOrder = new CustomerOrder(customerCredit);
        initComponents();
        this.username = username;
        DefaultTableModel tableOrderHistoryModel = (DefaultTableModel) table_OrderHistory.getModel();
        clearTable();
        displayOrderHistory(username);
        
        table_OrderHistory.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && table_OrderHistory.getSelectedRow() != -1) {
                    int selectedRowIndex = table_OrderHistory.getSelectedRow();
                    String selectedOrderID = String.valueOf(table_OrderHistory.getValueAt(selectedRowIndex, 0));                    
                    orderID = selectedOrderID;
                    List<List<String>> orderItemsData = customerOrder.getOrderItemsData(orderID);
                    displayOrderItems(orderItemsData);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1150, 675));
        setPreferredSize(new java.awt.Dimension(1150, 675));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setMinimumSize(new java.awt.Dimension(1150, 675));
        panel.setPreferredSize(new java.awt.Dimension(1150, 675));

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
        table_OrderHistory.getTableHeader().setResizingAllowed(false);
        table_OrderHistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_OrderHistory);
        if (table_OrderHistory.getColumnModel().getColumnCount() > 0) {
            table_OrderHistory.getColumnModel().getColumn(0).setResizable(false);
            table_OrderHistory.getColumnModel().getColumn(1).setResizable(false);
            table_OrderHistory.getColumnModel().getColumn(1).setPreferredWidth(140);
            table_OrderHistory.getColumnModel().getColumn(2).setResizable(false);
            table_OrderHistory.getColumnModel().getColumn(3).setResizable(false);
            table_OrderHistory.getColumnModel().getColumn(4).setResizable(false);
        }

        lbl_OrderItemsDetails.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_OrderItemsDetails.setText("Order Items Details:");

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
        jScrollPane2.setViewportView(table_OrderItems);
        if (table_OrderItems.getColumnModel().getColumnCount() > 0) {
            table_OrderItems.getColumnModel().getColumn(0).setResizable(false);
            table_OrderItems.getColumnModel().getColumn(1).setResizable(false);
            table_OrderItems.getColumnModel().getColumn(2).setResizable(false);
        }

        lbl_OrderHistory.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
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

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(lbl_OrderHistory))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_CancelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(49, 49, 49)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_OrderItemsDetails))
                .addGap(68, 68, 68))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_OrderHistory)
                    .addComponent(lbl_OrderItemsDetails))
                .addGap(8, 8, 8)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btn_CancelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(201, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void displayOrderHistory(String username){
        DefaultTableModel model = (DefaultTableModel) table_OrderHistory.getModel();
        model.setRowCount(0); 
        List<List<String>> orderHistoryData = new ArrayList<>();
        orderHistoryData = customerOrder.getOrderHistoryData(username);
        for (List<String> orderInfo : orderHistoryData) {
            model.addRow(orderInfo.toArray());
        }
    }
    
    private void displayOrderItems(List<List<String>> orderItemsData){
        DefaultTableModel model = (DefaultTableModel) table_OrderItems.getModel();
        model.setRowCount(0); 
        for (List<String> orderItems : orderItemsData) {
            model.addRow(orderItems.toArray());
        }
        
    }
    
    private void clearTable(){
        DefaultTableModel tableOrderItemsModel = (DefaultTableModel) table_OrderItems.getModel();
        tableOrderItemsModel.setRowCount(0);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        customerDashboard = new CustomerDashboard(username);
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
                    JOptionPane.showMessageDialog(this,"Order is already cancelled.","Order Cancelled",JOptionPane.INFORMATION_MESSAGE);
                    break;
                case "Pick Up":
                    JOptionPane.showMessageDialog(this,"Order is delivering.","Order Delivered",JOptionPane.INFORMATION_MESSAGE);    
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "The food is prepared by the vendor, so the order cannot be cancelled.", "Order Cannot be Cancelled", JOptionPane.WARNING_MESSAGE);
                    break;
            }
        } else {
             JOptionPane.showMessageDialog(this, "Please select an order.", "No Order Selected", JOptionPane.INFORMATION_MESSAGE);
        }
        displayOrderHistory(username);
    }//GEN-LAST:event_btn_CancelOrderActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_CheckOrderStatus("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_CancelOrder;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_OrderHistory;
    private javax.swing.JLabel lbl_OrderItemsDetails;
    private javax.swing.JPanel panel;
    private javax.swing.JTable table_OrderHistory;
    private javax.swing.JTable table_OrderItems;
    // End of variables declaration//GEN-END:variables
}
