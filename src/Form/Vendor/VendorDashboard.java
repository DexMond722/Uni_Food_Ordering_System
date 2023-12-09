/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.Vendor;

import Class.CustomerCredit;
import Class.CustomerOrder;
import Class.UserVendor;
import Form.Notification.Notifications;
import Form.Customer.CustomerDashboard;
import Form.Login.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.UIManager;

/**
 *
 * @author desmondcwf
 */
public class VendorDashboard extends javax.swing.JFrame {

    private CustomerCredit customerCredit;
    private CustomerOrder customerOrder;
    private UserVendor userVendor;
    private String username;
    private int userID;

    public VendorDashboard(String username, int userID) {
        userVendor = new UserVendor();
        initComponents();
        this.username = username;
        this.userID = userID;
        lbl_Welcome.setText("Welcome back, " + username);
        btn_EditMenu.setOpaque(true);
        btn_EditMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vendor_EditMenu editMenu = new Vendor_EditMenu(username);
                editMenu.setDefaultCloseOperation(VendorDashboard.DISPOSE_ON_CLOSE);
                editMenu.setVisible(true);
                editMenu.pack();
                editMenu.setLocationRelativeTo(null);
            }
        });
        btn_OrderStatus.setOpaque(true);
        btn_OrderStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vendor_OrderManagement orderManagement = new Vendor_OrderManagement(username);
                orderManagement.setDefaultCloseOperation(VendorDashboard.DISPOSE_ON_CLOSE);
                orderManagement.setVisible(true);
                orderManagement.pack();
                orderManagement.setLocationRelativeTo(null);
            }
        });
        btn_UpdateStatus.setOpaque(true);
        btn_UpdateStatus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vendor_UpdateStatus updateStatus = new Vendor_UpdateStatus(username);
                updateStatus.setDefaultCloseOperation(VendorDashboard.DISPOSE_ON_CLOSE);
                updateStatus.setVisible(true);
                updateStatus.pack();
                updateStatus.setLocationRelativeTo(null);
            }
        });
        btn_OrderHistory.setOpaque(true);
        btn_OrderHistory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vendor_OrderHistory orderHistory = new Vendor_OrderHistory(username);
                orderHistory.setDefaultCloseOperation(VendorDashboard.DISPOSE_ON_CLOSE);
                orderHistory.setVisible(true);
                orderHistory.pack();
                orderHistory.setLocationRelativeTo(null);
            }
        });
        btn_RevenueDashboard.setOpaque(true);
        btn_RevenueDashboard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Vendor_RevenueDashboard revenueDashboard = new Vendor_RevenueDashboard(username);
                revenueDashboard.setDefaultCloseOperation(VendorDashboard.DISPOSE_ON_CLOSE);
                revenueDashboard.setVisible(true);
                revenueDashboard.pack();
                revenueDashboard.setLocationRelativeTo(null);
            }
        });
        btn_Notification.setOpaque(true);
        btn_Notification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Notifications n = new Notifications(userID);
                n.setDefaultCloseOperation(CustomerDashboard.DISPOSE_ON_CLOSE);
                n.setVisible(true);
                n.pack();
                n.setLocationRelativeTo(null);

            }
        });
        btn_LogOut.setOpaque(true);
        btn_LogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();

                LoginForm login = new LoginForm();
                login.setVisible(true);
                login.pack();
                login.setLocationRelativeTo(null);

            }
        });
    }
    //display Credit Amount

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelTop = new javax.swing.JPanel();
        lbl_Icon = new javax.swing.JLabel();
        lbl_Welcome = new javax.swing.JLabel();
        btn_Notification = new javax.swing.JButton();
        PanelBottom = new javax.swing.JPanel();
        btn_EditMenu = new javax.swing.JButton();
        btn_OrderStatus = new javax.swing.JButton();
        btn_LogOut = new javax.swing.JButton();
        btn_UpdateStatus = new javax.swing.JButton();
        btn_OrderHistory = new javax.swing.JButton();
        btn_RevenueDashboard = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(530, 730));
        setMinimumSize(new java.awt.Dimension(530, 730));
        setPreferredSize(new java.awt.Dimension(500, 700));
        setResizable(false);
        setSize(new java.awt.Dimension(530, 730));

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 700));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 700));
        jPanel1.setPreferredSize(new java.awt.Dimension(500, 700));
        jPanel1.setLayout(null);

        PanelTop.setBackground(new java.awt.Color(63, 222, 183));
        PanelTop.setMaximumSize(new java.awt.Dimension(500, 120));

        lbl_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/profile_picture2.png"))); // NOI18N

        lbl_Welcome.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        lbl_Welcome.setText("jLabel1");

        btn_Notification.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/notification.png"))); // NOI18N
        btn_Notification.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_NotificationActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelTopLayout = new javax.swing.GroupLayout(PanelTop);
        PanelTop.setLayout(PanelTopLayout);
        PanelTopLayout.setHorizontalGroup(
            PanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Icon)
                .addGap(18, 18, 18)
                .addComponent(lbl_Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_Notification)
                .addContainerGap(71, Short.MAX_VALUE))
        );
        PanelTopLayout.setVerticalGroup(
            PanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTopLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbl_Icon)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelTopLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(PanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_Notification)
                    .addComponent(lbl_Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
        );

        jPanel1.add(PanelTop);
        PanelTop.setBounds(0, 0, 530, 120);

        PanelBottom.setBackground(new java.awt.Color(204, 255, 204));
        PanelBottom.setMaximumSize(new java.awt.Dimension(500, 580));
        PanelBottom.setPreferredSize(new java.awt.Dimension(500, 600));

        btn_EditMenu.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_EditMenu.setText("Edit Menu");
        btn_EditMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditMenuActionPerformed(evt);
            }
        });

        btn_OrderStatus.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_OrderStatus.setText("Manage Order");
        btn_OrderStatus.setMaximumSize(new java.awt.Dimension(105, 23));
        btn_OrderStatus.setMinimumSize(new java.awt.Dimension(105, 23));
        btn_OrderStatus.setPreferredSize(new java.awt.Dimension(177, 23));
        btn_OrderStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_OrderStatusActionPerformed(evt);
            }
        });

        btn_LogOut.setBackground(new java.awt.Color(235, 235, 235));
        btn_LogOut.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_LogOut.setForeground(new java.awt.Color(255, 0, 0));
        btn_LogOut.setText("Log Out");

        btn_UpdateStatus.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_UpdateStatus.setText("Update Order Status");
        btn_UpdateStatus.setMaximumSize(new java.awt.Dimension(179, 53));
        btn_UpdateStatus.setMinimumSize(new java.awt.Dimension(179, 53));

        btn_OrderHistory.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_OrderHistory.setText("View Order History");

        btn_RevenueDashboard.setFont(new java.awt.Font("Georgia", 1, 14)); // NOI18N
        btn_RevenueDashboard.setText("View Revenue");
        btn_RevenueDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_RevenueDashboardActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelBottomLayout = new javax.swing.GroupLayout(PanelBottom);
        PanelBottom.setLayout(PanelBottomLayout);
        PanelBottomLayout.setHorizontalGroup(
            PanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBottomLayout.createSequentialGroup()
                .addGap(164, 164, 164)
                .addGroup(PanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_LogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_RevenueDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_OrderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_UpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_OrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_EditMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        PanelBottomLayout.setVerticalGroup(
            PanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBottomLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btn_EditMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btn_OrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(btn_UpdateStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btn_OrderHistory, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(btn_RevenueDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btn_LogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jPanel1.add(PanelBottom);
        PanelBottom.setBounds(0, 120, 530, 610);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_EditMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_EditMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_EditMenuActionPerformed

    private void btn_OrderStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_OrderStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_OrderStatusActionPerformed

    private void btn_RevenueDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_RevenueDashboardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_RevenueDashboardActionPerformed

    private void btn_NotificationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_NotificationActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_NotificationActionPerformed

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendorDashboard("", -1).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBottom;
    private javax.swing.JPanel PanelTop;
    private javax.swing.JButton btn_EditMenu;
    private javax.swing.JButton btn_LogOut;
    private javax.swing.JButton btn_Notification;
    private javax.swing.JButton btn_OrderHistory;
    private javax.swing.JButton btn_OrderStatus;
    private javax.swing.JButton btn_RevenueDashboard;
    private javax.swing.JButton btn_UpdateStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_Icon;
    private javax.swing.JLabel lbl_Welcome;
    // End of variables declaration//GEN-END:variables
}
