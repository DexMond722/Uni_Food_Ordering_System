/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.Vendor;

import Class.UserVendor;
import Form.Customer.CustomerDashboard;
import Form.Customer.Customer_ViewMenu;
import Form.Login.LoginForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author desmondcwf
 */
public class VendorDashboard extends javax.swing.JFrame {

    private UserVendor userVendor;
    private String username;
    
    public VendorDashboard(String username) {
        userVendor = new UserVendor();
        initComponents();
         this.username = username;
        lbl_Welcome.setText("Welcome back, "+username);
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
                Vendor_AcceptOrder acceptOrder = new Vendor_AcceptOrder(username);
                acceptOrder.setDefaultCloseOperation(VendorDashboard.DISPOSE_ON_CLOSE);
                acceptOrder.setVisible(true);
                acceptOrder.pack();
                acceptOrder.setLocationRelativeTo(null);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelTop = new javax.swing.JPanel();
        lbl_Icon = new javax.swing.JLabel();
        lbl_Welcome = new javax.swing.JLabel();
        PanelBottom = new javax.swing.JPanel();
        btn_EditMenu = new javax.swing.JButton();
        btn_OrderStatus = new javax.swing.JButton();
        btn_LogOut = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 650));
        setResizable(false);
        setSize(new java.awt.Dimension(500, 650));

        jPanel1.setMaximumSize(new java.awt.Dimension(500, 650));
        jPanel1.setMinimumSize(new java.awt.Dimension(500, 650));
        jPanel1.setLayout(null);

        PanelTop.setBackground(new java.awt.Color(63, 222, 183));
        PanelTop.setMaximumSize(new java.awt.Dimension(500, 120));

        lbl_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/profile_picture2.png"))); // NOI18N

        lbl_Welcome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbl_Welcome.setText("jLabel1");

        javax.swing.GroupLayout PanelTopLayout = new javax.swing.GroupLayout(PanelTop);
        PanelTop.setLayout(PanelTopLayout);
        PanelTopLayout.setHorizontalGroup(
            PanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_Icon)
                .addGap(18, 18, 18)
                .addComponent(lbl_Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        PanelTopLayout.setVerticalGroup(
            PanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelTopLayout.createSequentialGroup()
                .addGroup(PanelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelTopLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lbl_Icon))
                    .addGroup(PanelTopLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(lbl_Welcome, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel1.add(PanelTop);
        PanelTop.setBounds(0, 0, 500, 120);

        PanelBottom.setBackground(new java.awt.Color(204, 255, 204));
        PanelBottom.setMaximumSize(new java.awt.Dimension(500, 530));

        btn_EditMenu.setText("Edit Menu");
        btn_EditMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_EditMenuActionPerformed(evt);
            }
        });

        btn_OrderStatus.setText("Accept/Cancel Order");
        btn_OrderStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_OrderStatusActionPerformed(evt);
            }
        });

        btn_LogOut.setBackground(new java.awt.Color(235, 235, 235));
        btn_LogOut.setForeground(new java.awt.Color(255, 0, 0));
        btn_LogOut.setText("Log Out");

        javax.swing.GroupLayout PanelBottomLayout = new javax.swing.GroupLayout(PanelBottom);
        PanelBottom.setLayout(PanelBottomLayout);
        PanelBottomLayout.setHorizontalGroup(
            PanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBottomLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_EditMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelBottomLayout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addGroup(PanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_OrderStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                    .addComponent(btn_LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(172, Short.MAX_VALUE))
        );
        PanelBottomLayout.setVerticalGroup(
            PanelBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBottomLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btn_EditMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(btn_OrderStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                .addComponent(btn_LogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(134, 134, 134))
        );

        jPanel1.add(PanelBottom);
        PanelBottom.setBounds(-10, 120, 500, 530);

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


    public static void main(String args[]) {



        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VendorDashboard("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanelBottom;
    private javax.swing.JPanel PanelTop;
    private javax.swing.JButton btn_EditMenu;
    private javax.swing.JButton btn_LogOut;
    private javax.swing.JButton btn_OrderStatus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_Icon;
    private javax.swing.JLabel lbl_Welcome;
    // End of variables declaration//GEN-END:variables
}
