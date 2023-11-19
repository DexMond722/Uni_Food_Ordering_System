package Dashboard;

import Login.User;
import Login.UserCustomer;
import Dashboard.CustomerDashboard;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.*;

public class Customer_ViewMenu extends javax.swing.JFrame {

    private UserCustomer userCustomer; 
    public Customer_ViewMenu() {
        userCustomer = new UserCustomer();
        initComponents();
        populateVendorComboBox();
        clearTable();
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
        DefaultTableModel model = (DefaultTableModel) table_Menu.getModel();
        model.setRowCount(0);
    }
    
    

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        panel = new javax.swing.JPanel();
        lbl_SelectVendor = new javax.swing.JLabel();
        comboBox_Vendor = new javax.swing.JComboBox<>();
        jScrollPane3 = new javax.swing.JScrollPane();
        table_Menu = new javax.swing.JTable();
        lbl_Menu = new javax.swing.JLabel();

        jScrollPane1.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        panel.setBackground(new java.awt.Color(255, 255, 255));

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
        jScrollPane3.setViewportView(table_Menu);

        lbl_Menu.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        lbl_Menu.setText("Menu:");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbl_SelectVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(lbl_Menu)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(comboBox_Vendor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(501, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl_SelectVendor, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBox_Vendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbl_Menu)
                .addGap(16, 16, 16)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(199, Short.MAX_VALUE))
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
    // add item state changed event of comboBox to display the correspond menu
    private void comboBox_VendorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBox_VendorItemStateChanged
        if (evt.getStateChange() == evt.SELECTED) {
            String selectedVendor = (String) comboBox_Vendor.getSelectedItem();
            List<List<String>> menuItems = userCustomer.getMenuOfVendor(selectedVendor);
            displayMenu(menuItems);
        }
    }//GEN-LAST:event_comboBox_VendorItemStateChanged

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Customer_ViewMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboBox_Vendor;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbl_Menu;
    private javax.swing.JLabel lbl_SelectVendor;
    private javax.swing.JPanel panel;
    private javax.swing.JTable table_Menu;
    // End of variables declaration//GEN-END:variables
}
