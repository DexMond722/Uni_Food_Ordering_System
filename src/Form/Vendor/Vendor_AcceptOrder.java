/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.Vendor;

import Class.UserVendor;
import Class.VendorOrder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Vendor_AcceptOrder extends javax.swing.JFrame {

    private VendorOrder vendorOrder;
    private String username; 
    private static final String runnerTaskFilePath = "src/Database/runnerTask.txt";
    private static final String userFilePath = "src/Database/users.txt";
    private static final String orderFilePath = "src/Database/order.txt";

    
    
    public Vendor_AcceptOrder(String username) {
        initComponents();
        this.username = username;
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Order.getModel();
        vendorOrder = new VendorOrder(username);
        displayOrder(vendorOrder.getVendorOrder(username));
        preventMenuEdited(); 
        
        // Add ListSelectionListener to table_Order
        table_Order.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table_Order.getSelectedRow();
                    if (selectedRow != -1) {
                        // Get the OrderItemID from the selected row
                        String orderItemID = (String) table_Order.getValueAt(selectedRow, 2);
                        try {
                            displayFoodDetails(orderItemID);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                            // Handle the exception as needed, e.g., show an error message
                        }
                    }
                }
            }
        });
    }
    
    
    private void displayOrder(List<List<String>> orderItems) {
        DefaultTableModel model = (DefaultTableModel) table_Order.getModel();
        model.setRowCount(0);

        for (List<String> orderItem : orderItems) {
            String orderStatus = orderItem.get(4); // Assuming OrderStatus is at index 4
            if ("Pending".equals(orderStatus)) {
                List<String> displayData = new ArrayList<>();
                displayData.add(orderItem.get(0));  // OrderID
                displayData.add(orderItem.get(1));  // OrderPlacementTime
                displayData.add(orderItem.get(2));  // OrderItemID
                displayData.add(orderItem.get(3));  // OrderAmount
                displayData.add(orderItem.get(4));  // OrderStatus
                displayData.add(orderItem.get(5));  // CustomerUserID
                displayData.add(orderItem.get(7));  // ServiceType
                model.addRow(displayData.toArray());
            }
        }
    } 
    
    private void displayFoodDetails(String orderItemID) throws IOException {
        DefaultTableModel model = (DefaultTableModel) table_FoodDetails.getModel();
        model.setRowCount(0);

    // Load order items data from orderItems.txt for the selected OrderItemID
        try {
            String menuFolderPath = "src/Database/Menu/";
            String menuFilePath = menuFolderPath + username + "Menu.txt";

            BufferedReader menuReader = new BufferedReader(new FileReader(menuFilePath));
            Map<String, String> foodNameMap = new HashMap<>();

        // Read the menu file and populate the foodNameMap
            String menuLine;
            while ((menuLine = menuReader.readLine()) != null) {
                String[] menuData = menuLine.split(",");
                String foodID = menuData[0].trim();
                String foodName = menuData[1].trim(); // Assuming FoodName is at index 1
                foodNameMap.put(foodID, foodName);
            }
        // Read the order items file and display details
            BufferedReader orderReader = new BufferedReader(new FileReader("src/Database/orderItems.txt"));
            orderReader.readLine(); // Skip header
            String orderLine;
            while ((orderLine = orderReader.readLine()) != null) {
                List<String> orderItemDetails = Arrays.asList(orderLine.split(","));
                String currentOrderItemID = orderItemDetails.get(0);

                if (currentOrderItemID.equals(orderItemID)) {
                    String foodID = orderItemDetails.get(1);
                    String foodName = foodNameMap.get(foodID);

                    // Replace FoodID with FoodName in the order item details
                    orderItemDetails.set(1, foodName);
                    model.addRow(orderItemDetails.toArray());
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void assignRunnerForDelivery(String orderID) {
        
        try {
            // Check the service type of the selected order
            String serviceType = getServiceTypeForOrder(orderID);
            
            if ("Delivery".equals(serviceType)) {
                // Get the VendorID from the logged-in user
                int vendorID = vendorOrder.getVendorUserIdByUsername(username);
                
                // Load users to find a suitable DeliveryRunner
                List<String> deliveryRunnerIDs = getUsersWithRole("DeliveryRunner");
                if (!deliveryRunnerIDs.isEmpty()) {
                    // Get a random DeliveryRunnerID
                    Random random = new Random();
                    String randomDeliveryRunnerID = deliveryRunnerIDs.get(random.nextInt(deliveryRunnerIDs.size()));

                    // Generate a new TaskID
                    int newTaskID = vendorOrder.generateNewTaskID(runnerTaskFilePath);

                    // Write into runnerTask.txt
                    BufferedWriter runnerTaskWriter = new BufferedWriter(new FileWriter(runnerTaskFilePath, true));
                    String newRunnerTask = newTaskID + "," + randomDeliveryRunnerID + "," + orderID + "," + vendorID + ",Pending";
                    runnerTaskWriter.write(newRunnerTask);
                    runnerTaskWriter.newLine();
                    runnerTaskWriter.close();

                    // Update order status to "Accepted"
                    updateOrderStatus(orderID, "Accepted");

                    // Refresh the order table
                    refreshOrderTable();
                } else {
                    // Handle case when no DeliveryRunner is available
                    System.out.println("No available DeliveryRunner");
                }

                // Update UI or inform the user
            } else {
                // Inform the user that this order doesn't require a DeliveryRunner
                //System.out.println("This order doesn't require a DeliveryRunner");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle exceptions as needed
        }
    }
    
     private List<String> getUsersWithRole(String role) {
        List<String> userIDs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length >= 4 && role.equals(userData[3].trim())) {
                    userIDs.add(userData[0]); // Assuming UserID is at index 0
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userIDs;
    }
     
    private String getServiceTypeForOrder(String orderID) throws IOException {
        // Read the order file to find the ServiceType for the given OrderID
        try (BufferedReader orderReader = new BufferedReader(new FileReader(orderFilePath))) {
            String orderLine;
            while ((orderLine = orderReader.readLine()) != null) {
                String[] orderData = orderLine.split(",");
                if (orderData.length >= 1 && orderData[0].equals(orderID)) {
                    return orderData[7]; // Assuming ServiceType is at index 7
                }
            }
        }
        return ""; // Return empty string if ServiceType is not found for the given OrderID
    }
    
    private void updateOrderStatus(String orderID, String newStatus) {
        try {
            // Read the order file and update the status
            List<String> updatedOrders = new ArrayList<>();
            BufferedReader orderReader = new BufferedReader(new FileReader(orderFilePath));
            String orderLine;
            while ((orderLine = orderReader.readLine()) != null) {
                String[] orderData = orderLine.split(",");
                if (orderData[0].equals(orderID)) {
                    orderData[4] = newStatus; // Assuming OrderStatus is at index 4
                }
                updatedOrders.add(String.join(",", orderData));
            }
           orderReader.close();

         // Rewrite the updated orders to the file
            BufferedWriter orderWriter = new BufferedWriter(new FileWriter(orderFilePath));
            for (String updatedOrder : updatedOrders) {
                orderWriter.write(updatedOrder);
                orderWriter.newLine();
            }
            orderWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            // Handle exceptions as needed
        }
    }
    
    private void refreshOrderTable() {
        DefaultTableModel model = (DefaultTableModel) table_Order.getModel();
        model.setRowCount(0); // Clear the existing rows

        // Get the updated order items and display them in the table
        List<List<String>> orderItems = vendorOrder.getVendorOrder(username);
        displayOrder(orderItems);
    }
    
    private void preventMenuEdited(){
        table_Order.setDefaultEditor(Object.class,null);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(750, 626));
        setMinimumSize(new java.awt.Dimension(750, 626));
        setPreferredSize(new java.awt.Dimension(750, 626));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(750, 626));
        jPanel1.setMinimumSize(new java.awt.Dimension(750, 626));
        jPanel1.setLayout(null);

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
        table_Order.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_Order);
        if (table_Order.getColumnModel().getColumnCount() > 0) {
            table_Order.getColumnModel().getColumn(0).setResizable(false);
            table_Order.getColumnModel().getColumn(1).setResizable(false);
            table_Order.getColumnModel().getColumn(1).setPreferredWidth(100);
            table_Order.getColumnModel().getColumn(2).setResizable(false);
            table_Order.getColumnModel().getColumn(3).setResizable(false);
            table_Order.getColumnModel().getColumn(4).setResizable(false);
            table_Order.getColumnModel().getColumn(5).setResizable(false);
            table_Order.getColumnModel().getColumn(6).setResizable(false);
        }

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(40, 40, 670, 240);

        btn_Accept.setText("jButton1");
        btn_Accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AcceptActionPerformed(evt);
            }
        });
        jPanel1.add(btn_Accept);
        btn_Accept.setBounds(440, 420, 100, 40);

        btn_Cancel.setText("jButton2");
        jPanel1.add(btn_Cancel);
        btn_Cancel.setBounds(590, 420, 100, 40);

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
        table_FoodDetails.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_FoodDetails);
        if (table_FoodDetails.getColumnModel().getColumnCount() > 0) {
            table_FoodDetails.getColumnModel().getColumn(0).setResizable(false);
            table_FoodDetails.getColumnModel().getColumn(1).setResizable(false);
            table_FoodDetails.getColumnModel().getColumn(2).setResizable(false);
        }

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(40, 330, 370, 210);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel1.setText("Ordered Items Details");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(40, 300, 190, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("Order Details");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(40, 10, 190, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 750, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_AcceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AcceptActionPerformed
        int selectedRow = table_Order.getSelectedRow();
            if (selectedRow != -1) {
                String orderID = (String) table_Order.getValueAt(selectedRow, 0); // Assuming OrderID is in the first column
                assignRunnerForDelivery(orderID);
                updateOrderStatus(orderID, "Accepted");
                refreshOrderTable();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a specific row on the Order Table");
            }
    }//GEN-LAST:event_btn_AcceptActionPerformed


    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendor_AcceptOrder("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Accept;
    private javax.swing.JButton btn_Cancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_FoodDetails;
    private javax.swing.JTable table_Order;
    // End of variables declaration//GEN-END:variables
}
