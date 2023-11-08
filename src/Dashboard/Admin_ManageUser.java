/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Dashboard;

import Login.User;
import Login.UserManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author desmondcwf
 */
public class Admin_ManageUser extends javax.swing.JFrame {

    private UserManager userManager;
    private DefaultTableModel tableModel;

    /**
     * Creates new form Admin_ManageUser
     */
    public Admin_ManageUser() {
        initComponents();
        userManager = new UserManager();
        String[] columnNames = {"Username", "Password", "Role"};
        tableModel = new DefaultTableModel(columnNames, 0);
        jTable1.setModel(tableModel);
        loadUsersIntoTable();

        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = jTable1.getSelectedRow();
                    if (selectedRow >= 0) {
                        // Get the data from the selected row
                        String username = (String) tableModel.getValueAt(selectedRow, 0);
                        String password = (String) tableModel.getValueAt(selectedRow, 1);
                        String role = (String) tableModel.getValueAt(selectedRow, 2);

                        // Display the data in text fields
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
                int selectedRow = jTable1.getSelectedRow();
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
                int selectedRow = jTable1.getSelectedRow();
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
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtbox_Username = new javax.swing.JTextField();
        txtbox_Password = new javax.swing.JTextField();
        cbox_Role = new javax.swing.JComboBox<>();
        btn_Delete = new javax.swing.JButton();
        btn_Update = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(700, 600));
        setSize(new java.awt.Dimension(700, 600));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(700, 600));
        jPanel1.setSize(new java.awt.Dimension(700, 600));
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(153, 153, 153));
        jLabel1.setText("Manage User");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(238, 6, 253, 85);

        jTable1.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(54, 97, 598, 295);

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setText("Username");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(87, 422, 110, 21);

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel3.setText("Password");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(87, 467, 100, 21);

        jLabel4.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel4.setText("Role");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(87, 520, 60, 21);
        jPanel1.add(txtbox_Username);
        txtbox_Username.setBounds(260, 419, 179, 23);
        jPanel1.add(txtbox_Password);
        txtbox_Password.setBounds(260, 464, 179, 23);

        cbox_Role.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administrator", "Customer", "DeliveryRunner", "Vendor" }));
        cbox_Role.setSelectedItem(null);
        jPanel1.add(cbox_Role);
        cbox_Role.setBounds(260, 517, 179, 23);

        btn_Delete.setBackground(new java.awt.Color(255, 51, 0));
        btn_Delete.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_Delete.setText("Delete");
        btn_Delete.setBorder(new javax.swing.border.MatteBorder(null));
        jPanel1.add(btn_Delete);
        btn_Delete.setBounds(510, 500, 90, 50);

        btn_Update.setBackground(new java.awt.Color(204, 255, 255));
        btn_Update.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_Update.setText("Update");
        btn_Update.setBorder(new javax.swing.border.MatteBorder(null));
        btn_Update.setPreferredSize(new java.awt.Dimension(77, 23));
        jPanel1.add(btn_Update);
        btn_Update.setBounds(510, 420, 90, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 58, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 95, Short.MAX_VALUE))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtbox_Password;
    private javax.swing.JTextField txtbox_Username;
    // End of variables declaration//GEN-END:variables
}
