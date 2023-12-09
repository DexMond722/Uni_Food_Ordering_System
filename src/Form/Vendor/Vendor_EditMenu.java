package Form.Vendor;

import Class.UserVendor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Vendor_EditMenu extends javax.swing.JFrame {

    private UserVendor userVendor;
    private String username;
    private int selectedRow = -1;
    private int selectedColumn = -1;
    private boolean newItemAdded;
    private static final int FOODNAME_COLUMN_INDEX = 1;
    private static final int PRICE_COLUMN_INDEX = 2;

    public Vendor_EditMenu(String username) {
        initComponents();
        this.username = username;
        userVendor = new UserVendor();
        displayMenu(userVendor.getVendorMenu(username));
        JTableHeader tableHeader1 = table_Menu.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 14);
        tableHeader1.setFont(headerFont1);
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
    }


    // make the text in the table cannot be edited 
    private void preventMenuEdited() {
        table_Menu.setDefaultEditor(Object.class, null);
    }

    private void addMenuItem() {
        String foodName = JOptionPane.showInputDialog(this, "Enter Food Name:");
        if (foodName == null) {
            JOptionPane.showMessageDialog(this, "Function Cancelled.");
            return;
        }

        if (!foodName.isEmpty()) {
            String inputPrice = JOptionPane.showInputDialog(this, "Enter Price:");
            if (inputPrice != null) {
                try {
                    double price = Double.parseDouble(inputPrice);
                    String selectedVendor = username;
                    userVendor.addMenuItem(selectedVendor, foodName, price);
                    refreshMenuTable(selectedVendor);

                    JOptionPane.showMessageDialog(this, "Menu item added successfully!");
                    newItemAdded = true;
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
        return table_Menu.getValueAt(selectedRow, 1).toString();
    }

    public double getCurrentPriceFromTable(int selectedRow) {
        return Double.parseDouble(table_Menu.getValueAt(selectedRow, PRICE_COLUMN_INDEX).toString());
    }

    private void editFoodName(int selectedRow) {
        String foodID = table_Menu.getValueAt(selectedRow, 0).toString();
        String editedFoodName = JOptionPane.showInputDialog(this, "Enter New FoodName:");

        if (!newItemAdded) {
            return;
        }

        if (editedFoodName == null) {
            JOptionPane.showMessageDialog(this, "Editing Cancelled.");
            return;
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
        String foodID = table_Menu.getValueAt(selectedRow, 0).toString();

        String newPrice = JOptionPane.showInputDialog(this, "Enter edited price:");

        if (newPrice == null) {
            JOptionPane.showMessageDialog(this, "Editing cancelled.");
            return;
        }

        try {
            double editedPrice;
            if (!newPrice.isEmpty()) {
                editedPrice = Double.parseDouble(newPrice);
            } else {
                JOptionPane.showMessageDialog(this, "Price input cannot be empty.");
                return;
            }

            userVendor.editMenuItem(username, foodID, getCurrentFoodNameFromTable(selectedRow), editedPrice);
            refreshMenuTable(username);

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
        }
    }

    private void deleteMenuItem(int selectedRow) {
        String vendorName = username;

        if (selectedRow != -1) {
            userVendor.deleteMenuItem(vendorName, selectedRow);
            refreshMenuTable(vendorName);
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
        jLabel1 = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(750, 530));
        setMinimumSize(new java.awt.Dimension(750, 530));
        setResizable(false);
        setSize(new java.awt.Dimension(750, 530));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMaximumSize(new java.awt.Dimension(750, 500));
        jPanel1.setMinimumSize(new java.awt.Dimension(750, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(750, 500));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMinimumSize(new java.awt.Dimension(760, 500));
        jPanel2.setPreferredSize(new java.awt.Dimension(760, 500));

        table_Menu.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
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
        table_Menu.setRowHeight(30);
        table_Menu.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_Menu);
        if (table_Menu.getColumnModel().getColumnCount() > 0) {
            table_Menu.getColumnModel().getColumn(0).setResizable(false);
            table_Menu.getColumnModel().getColumn(1).setResizable(false);
            table_Menu.getColumnModel().getColumn(2).setResizable(false);
        }

        btn_Add.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_Add.setText("Add");
        btn_Add.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_Add.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_AddActionPerformed(evt);
            }
        });

        btn_Edit.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_Edit.setText("Edit");
        btn_Edit.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_Edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditActionPerformed(evt);
            }
        });

        btn_Remove.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        btn_Remove.setText("Remove");
        btn_Remove.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btn_Remove.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RemoveActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Edit Menu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(btn_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_Add, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Edit, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_Remove, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 770, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
        if (selectedRow != -1) {
            deleteMenuItem(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }//GEN-LAST:event_btn_RemoveActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable table_Menu;
    // End of variables declaration//GEN-END:variables
}
