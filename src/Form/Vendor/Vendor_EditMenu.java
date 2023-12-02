
package Form.Vendor;

import Class.MenuItem;
import Class.UserVendor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class Vendor_EditMenu extends javax.swing.JFrame {

    private UserVendor userVendor;
    private String username; 
    
    
    public Vendor_EditMenu(String username) {
        initComponents();
        this.username = username;
        DefaultTableModel tableCartModel = (DefaultTableModel) table_Menu.getModel();
        userVendor = new UserVendor();
        populateVendorComboBox();
        clearTable();
        preventMenuEdited(); 

    }
    
     private void populateVendorComboBox() {
        comboBox_Vendor.removeAllItems();
        List<String> vendorList = userVendor.getVendorList();
        for (String vendor : vendorList) {
           comboBox_Vendor.addItem(vendor);
        }
        comboBox_Vendor.setSelectedIndex(-1);
    }
    
    private void displayMenu(List<List<String>> menuItems){
        DefaultTableModel model = (DefaultTableModel) table_Menu.getModel();
        model.setRowCount(0); 
        for (List<String> menuItem : menuItems) {
            model.addRow(menuItem.toArray());
        }
    }

     private void clearTable() {
        DefaultTableModel tableMenuModel = (DefaultTableModel) table_Menu.getModel();
        tableMenuModel.setRowCount(0);
        
    }
    
    // make the text in the table cannot be edited 
    private void preventMenuEdited(){
        table_Menu.setDefaultEditor(Object.class,null);
    }
    
    private void addMenuItem() {
        if (comboBox_Vendor.getSelectedIndex() == -1) {
        JOptionPane.showMessageDialog(this, "Please select a vendor before adding a menu item.");
        return;
    }
        
        String foodName = JOptionPane.showInputDialog(this, "Enter Food Name:");
        if (foodName != null) {  // User pressed OK
            String priceStr = JOptionPane.showInputDialog(this, "Enter Price:");
            if (priceStr != null) {  // User pressed OK
                try {
                    double price = Double.parseDouble(priceStr);

                    // Get the selected vendor
                    String selectedVendor = (String) comboBox_Vendor.getSelectedItem();
//                    System.out.println(selectedVendor);
                    UserVendor.addMenuItem(selectedVendor, foodName, price);
                    refreshMenuTable(selectedVendor);

                    JOptionPane.showMessageDialog(this, "Menu item added successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
                }
            }
        }
    }

    private void refreshMenuTable(String selectedVendor) {
        List<List<String>> menuItems = userVendor.getVendorMenu(selectedVendor);
        displayMenu(menuItems);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        comboBox_Vendor = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Menu = new javax.swing.JTable();
        btn_Add = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(750, 626));
        setResizable(false);
        setSize(new java.awt.Dimension(1200, 700));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(750, 626));
        jPanel1.setMinimumSize(new java.awt.Dimension(750, 626));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(153, 153, 153));

        comboBox_Vendor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboBox_Vendor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBox_VendorItemStateChanged(evt);
            }
        });

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
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Menu.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_Menu);
        if (table_Menu.getColumnModel().getColumnCount() > 0) {
            table_Menu.getColumnModel().getColumn(0).setResizable(false);
            table_Menu.getColumnModel().getColumn(1).setResizable(false);
        }

        btn_Add.setText("Add");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        jButton2.setText("Edit");

        jButton3.setText("Remove");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jButton2)
                .addGap(81, 81, 81)
                .addComponent(jButton3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btn_Add)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 215, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBox_Vendor, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(comboBox_Vendor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                        .addComponent(btn_Add)
                        .addGap(144, 144, 144)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(35, 35, 35))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 750, 440);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBox_VendorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBox_VendorItemStateChanged
        String selectedVendor = (String) comboBox_Vendor.getSelectedItem();
        List<List<String>> menuItems = userVendor.getVendorMenu(selectedVendor);
        displayMenu(menuItems);
    }//GEN-LAST:event_comboBox_VendorItemStateChanged

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        addMenuItem();
    }//GEN-LAST:event_btn_AddActionPerformed
    
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendor_EditMenu("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Add;
    private javax.swing.JComboBox<String> comboBox_Vendor;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_Menu;
    // End of variables declaration//GEN-END:variables
}
