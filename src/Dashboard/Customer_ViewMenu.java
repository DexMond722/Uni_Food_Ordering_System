package Dashboard;

import Login.User;
import Login.UserCustomer;
import Login.CustomerOrder;
import Login.CustomerCredit;
import Dashboard.CustomerDashboard;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;


public class Customer_ViewMenu extends javax.swing.JFrame {
    
    private UserCustomer userCustomer;
    private CustomerOrder customerOrder;
    private CustomerCredit customerCredit;
    private double totalAmount;
    private String username;
    public Customer_ViewMenu(String username) {
        initComponents();
        this.username = username;
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Cart.getModel();
        userCustomer = new UserCustomer();
        customerCredit = new CustomerCredit();
        customerOrder = new CustomerOrder(customerCredit);
        populateVendorComboBox();
        clearTable();
        preventMenuEdited();
        tableCartModel.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Update and display the total when the table is changed
                updateAndDisplayTotal(calculateTotal(tableCartModel));
            }
        });
        
    }
    
    
    // populate all vendor in a comboBox
    private void populateVendorComboBox() {
        comboBox_Vendor.removeAllItems();
        List<String> vendorList = userCustomer.getVendorList();
        for (String vendor : vendorList) {
           comboBox_Vendor.addItem(vendor);
        }
        comboBox_Vendor.setSelectedIndex(-1);
    }
    
    // display Menu in the table_Menu
    private void displayMenu(List<List<String>> menuItems){
        DefaultTableModel model = (DefaultTableModel) table_Menu.getModel();
        model.setRowCount(0); 
        for (List<String> menuItem : menuItems) {
            model.addRow(menuItem.toArray());
        }
    }
    
    // clear Item in the table
    private void clearTable() {
        DefaultTableModel tableMenuModel = (DefaultTableModel) table_Menu.getModel();
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Cart.getModel();
        tableMenuModel.setRowCount(0);
        tableCartModel.setRowCount(0);
    }
    
    // make the text in the table cannot be edited 
    private void preventMenuEdited(){
        table_Menu.setDefaultEditor(Object.class,null);
    }
    
    // calculate the total of table cart
    private double calculateTotal(DefaultTableModel model) {
        double total = 0;
        for (int row = 0; row < model.getRowCount(); row++) {
            total += Double.parseDouble(model.getValueAt(row, 4).toString());
        }
        totalAmount = total;
        return total;
    }
    
     // get total amount of food in the cart
    private double getTotalAmountOfFoodOrdered(){
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Cart.getModel();
        return calculateTotal(tableCartModel);
    }
    

    // update and display the total
    private void updateAndDisplayTotal(double total) {
        // Assuming lbl_Total is the JLabel to display the total
        lbl_DisplayTotal.setText("Total: RM" + total);
    }

 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        btnGroup_SelectService = new javax.swing.ButtonGroup();
        panel = new javax.swing.JPanel();
        lbl_SelectVendor = new javax.swing.JLabel();
        comboBox_Vendor = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_Menu = new javax.swing.JTable();
        lbl_Menu = new javax.swing.JLabel();
        btn_AddtoCart = new javax.swing.JButton();
        lbl_Cart = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Cart = new javax.swing.JTable();
        btn_AddQuantity = new javax.swing.JButton();
        btn_MinusQuantity = new javax.swing.JButton();
        btn_DeleteItem = new javax.swing.JButton();
        lbl_DisplayTotal = new javax.swing.JLabel();
        radioBtn_DineIn = new javax.swing.JRadioButton();
        radioBtn_TakeAway = new javax.swing.JRadioButton();
        radioBtn_Delivery = new javax.swing.JRadioButton();
        lbl_SelectService = new javax.swing.JLabel();
        btn_PlaceOrder = new javax.swing.JButton();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(1100, 675));

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setMinimumSize(new java.awt.Dimension(1100, 675));
        panel.setPreferredSize(new java.awt.Dimension(1100, 675));

        lbl_SelectVendor.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_SelectVendor.setText("Select Vendor:");

        comboBox_Vendor.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        comboBox_Vendor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox_Vendor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBox_VendorItemStateChanged(evt);
            }
        });

        table_Menu.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        table_Menu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "FoodID", "FoodName", "Price"
            }
        ));
        table_Menu.setSelectionBackground(new java.awt.Color(222, 222, 222));
        jScrollPane3.setViewportView(table_Menu);

        lbl_Menu.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_Menu.setText("Menu:");

        btn_AddtoCart.setFont(new java.awt.Font("Georgia", 1, 13)); // NOI18N
        btn_AddtoCart.setText("Add to Cart");
        btn_AddtoCart.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_AddtoCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddtoCartActionPerformed(evt);
            }
        });

        lbl_Cart.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_Cart.setText("Cart:");

        table_Cart.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "FoodID", "FoodName", "Price", "Quantity", "Total Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(table_Cart);

        btn_AddQuantity.setText("+");
        btn_AddQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddQuantityActionPerformed(evt);
            }
        });

        btn_MinusQuantity.setText("-");
        btn_MinusQuantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_MinusQuantityActionPerformed(evt);
            }
        });

        btn_DeleteItem.setText("Delete");
        btn_DeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteItemActionPerformed(evt);
            }
        });

        lbl_DisplayTotal.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_DisplayTotal.setText("Total: ");

        radioBtn_DineIn.setBackground(new java.awt.Color(255, 255, 255));
        btnGroup_SelectService.add(radioBtn_DineIn);
        radioBtn_DineIn.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        radioBtn_DineIn.setText("Dine In");

        radioBtn_TakeAway.setBackground(new java.awt.Color(255, 255, 255));
        btnGroup_SelectService.add(radioBtn_TakeAway);
        radioBtn_TakeAway.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        radioBtn_TakeAway.setText("Take Away");

        radioBtn_Delivery.setBackground(new java.awt.Color(255, 255, 255));
        btnGroup_SelectService.add(radioBtn_Delivery);
        radioBtn_Delivery.setFont(new java.awt.Font("Georgia", 0, 12)); // NOI18N
        radioBtn_Delivery.setText("Delivery");

        lbl_SelectService.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_SelectService.setText("Select Service:");

        btn_PlaceOrder.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_PlaceOrder.setText("Place Order");
        btn_PlaceOrder.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_PlaceOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_PlaceOrderActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lbl_SelectVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(comboBox_Vendor, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(lbl_Menu)
                        .addGap(439, 439, 439)
                        .addComponent(lbl_Cart))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(271, 271, 271)
                        .addComponent(btn_AddtoCart, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addComponent(lbl_SelectService)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radioBtn_DineIn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtn_TakeAway)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(radioBtn_Delivery)
                                .addGap(172, 172, 172))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(btn_AddQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btn_MinusQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(btn_DeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btn_PlaceOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_DisplayTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_SelectVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(comboBox_Vendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_Menu)
                    .addComponent(lbl_Cart))
                .addGap(16, 16, 16)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_AddtoCart, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btn_AddQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btn_MinusQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btn_DeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbl_DisplayTotal)))
                .addGap(58, 58, 58)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_SelectService)
                    .addComponent(radioBtn_DineIn)
                    .addComponent(radioBtn_TakeAway)
                    .addComponent(radioBtn_Delivery))
                .addGap(18, 18, 18)
                .addComponent(btn_PlaceOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(62, Short.MAX_VALUE))
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
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // display the menu when the item state in combobox is change
    private void comboBox_VendorItemStateChanged(java.awt.event.ItemEvent evt) {                                                 
        if (evt.getStateChange() == evt.SELECTED) {
            String selectedVendor = (String) comboBox_Vendor.getSelectedItem();
            List<List<String>> menuItems = userCustomer.getMenuOfVendor(selectedVendor);
            displayMenu(menuItems);
        }
    }
    // delete the row in table cart
    private void btn_DeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteItemActionPerformed
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Cart.getModel();
        int[] selectedRows = table_Cart.getSelectedRows();
        if (selectedRows.length > 0) {
            // Remove rows in reverse order to avoid index issues
            for (int i = selectedRows.length - 1; i >= 0; i--) {
                tableCartModel.removeRow(selectedRows[i]);
            }
            // Update total price after removing items
            updateAndDisplayTotal(calculateTotal(tableCartModel));
        } else {
            JOptionPane.showMessageDialog(this, "Please select rows to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_DeleteItemActionPerformed

    private void btn_MinusQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_MinusQuantityActionPerformed
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Cart.getModel();
        int selectedRow = table_Cart.getSelectedRow();
        if (selectedRow != -1) {
            int quantity = Integer.parseInt(tableCartModel.getValueAt(selectedRow, 3).toString());
            double price = Double.parseDouble(tableCartModel.getValueAt(selectedRow, 2).toString());
            if (quantity > 1) {
                quantity--;
                double totalPrice = quantity * price;
                tableCartModel.setValueAt(quantity, selectedRow, 3);
                tableCartModel.setValueAt(totalPrice, selectedRow, 4);
                updateAndDisplayTotal(calculateTotal(tableCartModel));
            } else {
                tableCartModel.removeRow(selectedRow);
                updateAndDisplayTotal(calculateTotal(tableCartModel));
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_MinusQuantityActionPerformed

    private void btn_AddQuantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddQuantityActionPerformed
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Cart.getModel();
        int selectedRow = table_Cart.getSelectedRow();
        // Check if a row is selected
        if (selectedRow != -1) {
            int quantity = Integer.parseInt(tableCartModel.getValueAt(selectedRow, 3).toString());
            double price = Double.parseDouble(tableCartModel.getValueAt(selectedRow, 2).toString());
            quantity++;
            double totalPrice = quantity * price;
            tableCartModel.setValueAt(quantity, selectedRow, 3);
            tableCartModel.setValueAt(totalPrice, selectedRow, 4);
            updateAndDisplayTotal(calculateTotal(tableCartModel));
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btn_AddQuantityActionPerformed

    // add item to the table_Cart from table_Menu
    private void btn_AddtoCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddtoCartActionPerformed
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Cart.getModel();
        // get the selected row from table Menu
        int[] selectedRows = table_Menu.getSelectedRows();
        // display the item selected in table cart
        if (selectedRows.length > 0) {
            for (int selectedRow : selectedRows) {
                String foodID = table_Menu.getValueAt(selectedRow, 0).toString();
                String foodName = table_Menu.getValueAt(selectedRow, 1).toString();
                double price = Double.parseDouble(table_Menu.getValueAt(selectedRow, 2).toString());

                // Check if the food item is already in the cart
                int existingRowIndex = findExistingCartItem(foodID);
                if (existingRowIndex != -1) {
                    // Update quantity and total price
                    int quantity = Integer.parseInt(tableCartModel.getValueAt(existingRowIndex, 3).toString()) + 1;
                    double totalPrice = quantity * price;
                    tableCartModel.setValueAt(quantity, existingRowIndex, 3);
                    tableCartModel.setValueAt(totalPrice, existingRowIndex, 4);
                } else {
                    // Add a new row
                    Object[] rowData = {foodID, foodName, price, 1, price};
                    tableCartModel.addRow(rowData);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a food item to add to the cart.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
        table_Menu.clearSelection();
    }//GEN-LAST:event_btn_AddtoCartActionPerformed

//GEN-FIRST:event_comboBox_VendorItemStateChanged

//GEN-LAST:event_comboBox_VendorItemStateChanged

    private void btn_PlaceOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_PlaceOrderActionPerformed
//        System.out.println(getTableCartOrderItemsData());
        String serviceType = getServiceType();
        double totalAmount = getTotalAmountOfFoodOrdered();
        String selectedVendor = (String) comboBox_Vendor.getSelectedItem();
        // check the service type radio button is selected or not
        if (serviceType.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please select a service type.", "Service Type not selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        if (customerCredit.checkCredit(totalAmount,username)){
            customerOrder.placeOrder(totalAmount,userCustomer.getCustomerUserID(username),userCustomer.getVendorUserID(selectedVendor),serviceType,getTableCartOrderItemsData());
            JOptionPane.showMessageDialog(this, "Order Successfully", "Order Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Please top up credit, credit is not enough for the order", "Credit Insufficient", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btn_PlaceOrderActionPerformed

    // get service type by radio button
    private String getServiceType() {
        if (radioBtn_DineIn.isSelected()) {
            return "DineIn";
        } else if (radioBtn_TakeAway.isSelected()) {
            return "TakeAway";
        } else if (radioBtn_Delivery.isSelected()) {
            return "Delivery";
        } else {
            // Handle the case where no radio button is selected
            return "";
        }
    }
    
    // return the row index of table Cart if the food is already inside
    private int findExistingCartItem(String foodID) {
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Cart.getModel();
        for (int i = 0; i < tableCartModel.getRowCount(); i++) {
            if (tableCartModel.getValueAt(i, 0).toString().equals(foodID)) {
                return i; // Return the index of the existing item
            }
        }
        return -1; // Return -1 if the item is not found
    }
    
    // get the data in the table_Cart
    private List<List<String>> getTableCartOrderItemsData(){
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Cart.getModel();
        int rowCount = tableCartModel.getRowCount();
        List<List<String>> OrderItemsData = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            Object foodIDObj = tableCartModel.getValueAt(i, 0); // Assuming FoodID is in the first column
            Object quantityObj = tableCartModel.getValueAt(i, 3); // Assuming Quantity is in the fourth column
            String foodID = String.valueOf(foodIDObj);
            String quantity = String.valueOf(quantityObj);
            List<String> rowList = new ArrayList<>();
            rowList.add(foodID);
            rowList.add(quantity);
            OrderItemsData.add(rowList);
        }
        return OrderItemsData;
    }
    
    public static void main(String args[]) { 
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_ViewMenu("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btnGroup_SelectService;
    private javax.swing.JButton btn_AddQuantity;
    private javax.swing.JButton btn_AddtoCart;
    private javax.swing.JButton btn_DeleteItem;
    private javax.swing.JButton btn_MinusQuantity;
    private javax.swing.JButton btn_PlaceOrder;
    private javax.swing.JComboBox<String> comboBox_Vendor;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_Cart;
    private javax.swing.JLabel lbl_DisplayTotal;
    private javax.swing.JLabel lbl_Menu;
    private javax.swing.JLabel lbl_SelectService;
    private javax.swing.JLabel lbl_SelectVendor;
    private javax.swing.JPanel panel;
    private javax.swing.JRadioButton radioBtn_Delivery;
    private javax.swing.JRadioButton radioBtn_DineIn;
    private javax.swing.JRadioButton radioBtn_TakeAway;
    private javax.swing.JTable table_Cart;
    private javax.swing.JTable table_Menu;
    // End of variables declaration//GEN-END:variables
}
