/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.Admin;

import Class.User;
import Class.UserManager;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author desmondcwf
 */
public class Admin_ManageUser extends javax.swing.JFrame {

    private UserManager userManager;
    private DefaultTableModel tableModel;
    
    public Admin_ManageUser() {
        initComponents();
        userManager = new UserManager();
        String[] columnNames = {"Username", "Password", "Role"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table_ManageUser.setModel(tableModel);
        //Change header font for table
        JTableHeader tableHeader1 = table_ManageUser.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 14);
        tableHeader1.setFont(headerFont1);
        loadUsersIntoTable();

        table_ManageUser.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table_ManageUser.getSelectedRow();
                    if (selectedRow >= 0) {
                        // Get the data from the selected row
                        String username = (String) tableModel.getValueAt(selectedRow, 0);
                        String password = (String) tableModel.getValueAt(selectedRow, 1);
                        String role = (String) tableModel.getValueAt(selectedRow, 2);

                        // Display the data in textbox
                        txtbox_Username.setText(username);
                        txtbox_Password.setText(password);
                        cbox_Role.setSelectedItem(role);
                    }
                }
            }
        });

        btn_Delete.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_ManageUser.getSelectedRow();
                if (selectedRow >= 0) {
                    String usernameToDelete = (String) tableModel.getValueAt(selectedRow, 0);
                    tableModel.removeRow(selectedRow);
                    if (userManager.deleteUser(usernameToDelete)) {
                        JOptionPane.showMessageDialog(null, "User deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                        txtbox_Username.setText("");
                        txtbox_Password.setText("");
                        cbox_Role.setSelectedItem(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error deleting user", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to delete", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        btn_Update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table_ManageUser.getSelectedRow();
                if (selectedRow >= 0) {
                    String oldUsername = (String) tableModel.getValueAt(selectedRow, 0);
                    String newUsername = txtbox_Username.getText();
                    String newPassword = txtbox_Password.getText();
                    String newRole = (String) cbox_Role.getSelectedItem();

                    userManager.updateUser(oldUsername, newUsername, newPassword, newRole);

                    tableModel.setValueAt(newUsername, selectedRow, 0);
                    tableModel.setValueAt(newPassword, selectedRow, 1);
                    tableModel.setValueAt(newRole, selectedRow, 2);

                    JOptionPane.showMessageDialog(null, "Data updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a row to update", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    //Load users into the table
    private void loadUsersIntoTable() {
        tableModel.setRowCount(0);
        List<User> usersList = userManager.getAllUsers();
        for (User user : usersList) {
            tableModel.addRow(new Object[]{user.getUsername(), user.getPassword(), user.getRole()});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_ManageUser = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_ManageUser = new javax.swing.JTable();
        lbl_Username = new javax.swing.JLabel();
        lbl_Password = new javax.swing.JLabel();
        lbl_Role = new javax.swing.JLabel();
        txtbox_Username = new javax.swing.JTextField();
        txtbox_Password = new javax.swing.JTextField();
        cbox_Role = new javax.swing.JComboBox<>();
        btn_Delete = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setResizable(false);
        setSize(new java.awt.Dimension(700, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 600));
        jPanel1.setSize(new java.awt.Dimension(700, 600));

        lbl_ManageUser.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        lbl_ManageUser.setForeground(new java.awt.Color(153, 153, 153));
        lbl_ManageUser.setText("Manage User");

        table_ManageUser.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_ManageUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_ManageUser.setRowHeight(30);
        table_ManageUser.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_ManageUser);
        if (table_ManageUser.getColumnModel().getColumnCount() > 0) {
            table_ManageUser.getColumnModel().getColumn(0).setResizable(false);
            table_ManageUser.getColumnModel().getColumn(1).setResizable(false);
            table_ManageUser.getColumnModel().getColumn(2).setResizable(false);
            table_ManageUser.getColumnModel().getColumn(3).setResizable(false);
        }

        lbl_Username.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        lbl_Username.setText("Username");

        lbl_Password.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        lbl_Password.setText("Password");

        lbl_Role.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        lbl_Role.setText("Role");

        cbox_Role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator", "Customer", "DeliveryRunner", "Vendor" }));
        cbox_Role.setSelectedItem(null);

        btn_Delete.setBackground(new java.awt.Color(255, 51, 0));
        btn_Delete.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_Delete.setText("Delete");
        btn_Delete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_Update.setBackground(new java.awt.Color(0, 204, 204));
        btn_Update.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_Update.setText("Update");
        btn_Update.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btn_Update.setPreferredSize(new java.awt.Dimension(77, 23));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(236, 236, 236)
                        .addComponent(lbl_ManageUser, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 598, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbl_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtbox_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtbox_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbl_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(113, 113, 113)
                                .addComponent(cbox_Role, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(68, 68, 68)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(360, 360, 360))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_ManageUser, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(lbl_Username)
                        .addGap(24, 24, 24)
                        .addComponent(lbl_Password)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(lbl_Role))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(cbox_Role, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtbox_Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Update, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtbox_Password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_ManageUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_Update;
    private javax.swing.JComboBox<String> cbox_Role;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_ManageUser;
    private javax.swing.JLabel lbl_Password;
    private javax.swing.JLabel lbl_Role;
    private javax.swing.JLabel lbl_Username;
    private javax.swing.JTable table_ManageUser;
    private javax.swing.JTextField txtbox_Password;
    private javax.swing.JTextField txtbox_Username;
    // End of variables declaration//GEN-END:variables
}
