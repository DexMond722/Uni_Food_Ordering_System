/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.Login;

import Class.UserManager;
import Class.User;
import Form.Admin.AdminDashboard;
import Form.Customer.CustomerDashboard;
import Form.DeliveryRunner.DeliveryDashboard;
import Form.Vendor.VendorDashboard;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author desmondcwf
 */
public class LoginForm extends javax.swing.JFrame {

    private UserManager userManager;
    

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        userManager = new UserManager();
        initComponents();
        btn_Login.setOpaque(true);
        btn_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtbox_Username.getText();
                String password = new String(txtbox_Password.getPassword());
                User user = userManager.getUserByUsernameAndPassword(username, password);

                if (user != null) {
                    openDashboard(user.getRole(), user.getUsername());
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void openDashboard(String role, String username) {
        if (role.equals("Administrator")) {
            AdminDashboard admin = new AdminDashboard(username);
            admin.setVisible(true);
            admin.pack();
            admin.setLocationRelativeTo(null);
            this.dispose();
        } else if (role.equals("Vendor")) {
            VendorDashboard vendor = new VendorDashboard(username);
            vendor.setVisible(true);
            vendor.pack();
            vendor.setLocationRelativeTo(null);
            this.dispose();
        } else if (role.equals("DeliveryRunner")) {
            DeliveryDashboard delivery = new DeliveryDashboard(username);
            delivery.setVisible(true);
            delivery.pack();
            delivery.setLocationRelativeTo(null);
            this.dispose();
        } else if (role.equals("Customer")) {
            CustomerDashboard customer = new CustomerDashboard(username);
            customer.setVisible(true);
            customer.pack();
            customer.setLocationRelativeTo(null);
            this.dispose();
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

        LoginForm = new javax.swing.JPanel();
        Left = new javax.swing.JPanel();
        lbl_title = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Right = new javax.swing.JPanel();
        lbl_Login = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        lbl_password = new javax.swing.JLabel();
        txtbox_Username = new javax.swing.JTextField();
        txtbox_Password = new javax.swing.JPasswordField();
        btn_Login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setResizable(false);
        setSize(new java.awt.Dimension(800, 500));

        LoginForm.setFocusable(false);
        LoginForm.setPreferredSize(new java.awt.Dimension(800, 500));
        LoginForm.setSize(new java.awt.Dimension(800, 500));
        LoginForm.setLayout(null);

        Left.setBackground(new java.awt.Color(0, 204, 255));
        Left.setPreferredSize(new java.awt.Dimension(400, 500));
        Left.setSize(new java.awt.Dimension(400, 500));

        lbl_title.setBackground(new java.awt.Color(255, 255, 255));
        lbl_title.setFont(new java.awt.Font("Georgia", 1, 24)); // NOI18N
        lbl_title.setForeground(new java.awt.Color(51, 51, 51));
        lbl_title.setText("APU Food Ordering System");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Login/logo (1).png"))); // NOI18N

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jLabel1))
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(lbl_title)))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeftLayout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbl_title, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
        );

        LoginForm.add(Left);
        Left.setBounds(0, 0, 400, 500);

        Right.setBackground(new java.awt.Color(51, 51, 51));
        Right.setPreferredSize(new java.awt.Dimension(400, 500));
        Right.setSize(new java.awt.Dimension(400, 500));

        lbl_Login.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        lbl_Login.setForeground(new java.awt.Color(255, 255, 255));
        lbl_Login.setText("LOGIN");

        lbl_username.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lbl_username.setForeground(new java.awt.Color(204, 255, 255));
        lbl_username.setText("Username");

        lbl_password.setFont(new java.awt.Font("Comic Sans MS", 1, 18)); // NOI18N
        lbl_password.setForeground(new java.awt.Color(204, 255, 255));
        lbl_password.setText("Password");

        txtbox_Username.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N

        txtbox_Password.setFont(new java.awt.Font("Georgia", 0, 13)); // NOI18N

        btn_Login.setBackground(new java.awt.Color(204, 255, 255));
        btn_Login.setFont(new java.awt.Font("Georgia", 1, 13)); // NOI18N
        btn_Login.setText("Login");
        btn_Login.setBorder(new javax.swing.border.MatteBorder(null));
        btn_Login.setFocusPainted(false);

        javax.swing.GroupLayout RightLayout = new javax.swing.GroupLayout(Right);
        Right.setLayout(RightLayout);
        RightLayout.setHorizontalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbl_username)
                            .addComponent(txtbox_Username)
                            .addComponent(lbl_password)
                            .addComponent(txtbox_Password, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)))
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(lbl_Login))
                    .addGroup(RightLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addComponent(btn_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        RightLayout.setVerticalGroup(
            RightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(lbl_Login)
                .addGap(51, 51, 51)
                .addComponent(lbl_username)
                .addGap(18, 18, 18)
                .addComponent(txtbox_Username, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lbl_password)
                .addGap(18, 18, 18)
                .addComponent(txtbox_Password, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addComponent(btn_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(71, Short.MAX_VALUE))
        );

        LoginForm.add(Right);
        Right.setBounds(400, 0, 400, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LoginForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(LoginForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JPanel LoginForm;
    private javax.swing.JPanel Right;
    private javax.swing.JButton btn_Login;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbl_Login;
    private javax.swing.JLabel lbl_password;
    private javax.swing.JLabel lbl_title;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JPasswordField txtbox_Password;
    private javax.swing.JTextField txtbox_Username;
    // End of variables declaration//GEN-END:variables
}
