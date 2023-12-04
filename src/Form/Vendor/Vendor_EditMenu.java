package Form.Vendor;

import Class.MenuItem;
import Class.UserVendor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private int selectedRow = -1;
    private int selectedColumn = -1;
    private static final int FOODNAME_COLUMN_INDEX = 1; // Replace with your actual column index for FoodName
    private static final int PRICE_COLUMN_INDEX = 2;

    public Vendor_EditMenu(String username) {
        initComponents();
        this.username = username;
        userVendor = new UserVendor();
        displayMenu(userVendor.getVendorMenu(username));
        preventMenuEdited();

        table_Menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table_Menu.getColumnModel().getColumnIndexAtX(e.getX());
                int row = e.getY() / table_Menu.getRowHeight();
                selectedRow = row;
                selectedColumn = column;
            }
        });
    }

    private void displayMenu(List<List<String>> menuItems) {
        DefaultTableModel model = (DefaultTableModel) table_Menu.getModel();
        model.setRowCount(0);
        for (List<String> menuItem : menuItems) {
            model.addRow(menuItem.toArray());
        }
        table_Menu.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table_Menu.setRowHeight(18);
    }

    private void clearTable() {
        DefaultTableModel tableMenuModel = (DefaultTableModel) table_Menu.getModel();
        tableMenuModel.setRowCount(0);
    }

    // make the text in the table cannot be edited 
    private void preventMenuEdited() {
        table_Menu.setDefaultEditor(Object.class, null);
    }

    private void addMenuItem() {
        String foodName = JOptionPane.showInputDialog(this, "Enter Food Name:");
        if (foodName == null) {
            JOptionPane.showMessageDialog(this, "Function Cancelled.");
            return;  // Exit the method
        }

        if (!foodName.isEmpty()) {  // User entered a non-empty food name
            String inputPrice = JOptionPane.showInputDialog(this, "Enter Price:");
            if (inputPrice != null) {  // User pressed OK
                try {
                    double price = Double.parseDouble(inputPrice);
                    String selectedVendor = username;
                    UserVendor.addMenuItem(selectedVendor, foodName, price);
                    refreshMenuTable(selectedVendor);

                    JOptionPane.showMessageDialog(this, "Menu item added successfully!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
                }

            } else {
                JOptionPane.showMessageDialog(this, "Function Cancelled.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please Enter food name.");
        }
    }

    private String getCurrentFoodNameFromTable(int selectedRow) {
        return table_Menu.getValueAt(selectedRow, 1).toString(); // Assuming FoodName is in the second column
    }

    private void editMenuItem() {
        int selectedRow = table_Menu.getSelectedRow();
        if (selectedRow != -1) {
            String foodID = table_Menu.getValueAt(selectedRow, 0).toString(); // Assuming FoodID is in the first column

            String editedFoodName = JOptionPane.showInputDialog(this, "Enter Edited FoodName:\n(If no, leave empty)");

            if (editedFoodName == null) {
                JOptionPane.showMessageDialog(this, "Editing canceled.");
                return; // Exit the method
            }

            String newPrice = JOptionPane.showInputDialog(this, "Enter edited price:");

            if (newPrice == null) {
                JOptionPane.showMessageDialog(this, "Editing canceled.");
                return; // Exit the method
            }

            try {
                double editedPrice;
                if (!newPrice.isEmpty()) {
                    editedPrice = Double.parseDouble(newPrice);
                } else {
                    // Handle empty input of price
                    JOptionPane.showMessageDialog(this, "Price input cannot be empty.");
                    return; // Exit the method
                }

                if (!editedFoodName.isEmpty()) {
                    userVendor.editMenuItem(username, foodID, editedFoodName, editedPrice);
                    refreshMenuTable(username);
                } else {
                    // Retrieve current food name from the table data
                    String currentFoodName = userVendor.getCurrentFoodNameFromTable(selectedRow, username);
                    userVendor.editMenuItem(username, foodID, currentFoodName, editedPrice);
                    refreshMenuTable(username);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }

    public double getCurrentPriceFromTable(int selectedRow) {
        return Double.parseDouble(table_Menu.getValueAt(selectedRow, PRICE_COLUMN_INDEX).toString());
    }

    private void editFoodName(int selectedRow) {
        String foodID = table_Menu.getValueAt(selectedRow, 0).toString(); // Assuming FoodID is in the first column

        String editedFoodName = JOptionPane.showInputDialog(this, "Enter New FoodName:");

        if (editedFoodName == null) {
            JOptionPane.showMessageDialog(this, "Editing canceled.");
            return; // Exit the method
        }

        try {
            if (!editedFoodName.isEmpty()) {
                userVendor.editMenuItem(username, foodID, editedFoodName, getCurrentPriceFromTable(selectedRow));
                refreshMenuTable(username);
            } else {
                String currentFoodName = userVendor.getCurrentFoodNameFromTable(selectedRow, username);
                userVendor.editMenuItem(username, foodID, currentFoodName, getCurrentPriceFromTable(selectedRow));
                refreshMenuTable(username);
                JOptionPane.showMessageDialog(this, "Invalid Input. Please enter a Food Name.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Input. Please enter a Food Name.");
        }
    }

    private void editPrice(int selectedRow) {
        String foodID = table_Menu.getValueAt(selectedRow, 0).toString(); // Assuming FoodID is in the first column

        String newPrice = JOptionPane.showInputDialog(this, "Enter edited price:");

        if (newPrice == null) {
            JOptionPane.showMessageDialog(this, "Editing canceled.");
            return; // Exit the method
        }

        try {
            double editedPrice;
            if (!newPrice.isEmpty()) {
                editedPrice = Double.parseDouble(newPrice);
            } else {
                // Handle empty input of price
                JOptionPane.showMessageDialog(this, "Price input cannot be empty.");
                return; // Exit the method
            }

            userVendor.editMenuItem(username, foodID, getCurrentFoodNameFromTable(selectedRow), editedPrice);
            refreshMenuTable(username);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
        }
    }

    private void deleteMenuItem(int selectedRow) {
        String vendorName = username; // Assuming you have this available

        if (selectedRow != -1) {
            userVendor.deleteMenuItem(vendorName, selectedRow);
            refreshMenuTable(vendorName); // Refresh the table after deletion
            JOptionPane.showMessageDialog(this, "Successfully Deleted.");
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
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
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Menu = new javax.swing.JTable();
        btn_Add = new javax.swing.JButton();
        btn_Edit = new javax.swing.JButton();
        btn_Remove = new javax.swing.JButton();

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
            table_Menu.getColumnModel().getColumn(2).setResizable(false);
        }

        btn_Add.setText("Add");
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_Edit.setText("Edit");
        btn_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditActionPerformed(evt);
            }
        });

        btn_Remove.setText("Remove");
        btn_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(181, 181, 181)
                        .addComponent(btn_Add)
                        .addGap(74, 74, 74)
                        .addComponent(btn_Edit)
                        .addGap(73, 73, 73)
                        .addComponent(btn_Remove))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Edit)
                    .addComponent(btn_Remove)
                    .addComponent(btn_Add))
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

    private void btn_AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_AddActionPerformed
        addMenuItem();
    }//GEN-LAST:event_btn_AddActionPerformed

    private void btn_EditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditActionPerformed
        if (selectedRow != -1 && selectedColumn != -1) {
            switch (selectedColumn) {
                case FOODNAME_COLUMN_INDEX:
                    editFoodName(selectedRow);
                    break;
                case PRICE_COLUMN_INDEX:
                    editPrice(selectedRow);
                    break;
                default:
                    JOptionPane.showMessageDialog(this, "Please select either FoodName or Price to edit.");
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a cell to edit.");
        }
        refreshMenuTable(username);
    }//GEN-LAST:event_btn_EditActionPerformed

    private void btn_RemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RemoveActionPerformed
        deleteMenuItem(selectedRow);
    }//GEN-LAST:event_btn_RemoveActionPerformed

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
    private javax.swing.JButton btn_Edit;
    private javax.swing.JButton btn_Remove;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_Menu;
    // End of variables declaration//GEN-END:variables
}
