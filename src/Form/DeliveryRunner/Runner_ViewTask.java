/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.DeliveryRunner;

import Class.RunnerTask;
import Class.UserCustomer;
import Class.VendorOrder;
import java.awt.Font;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class Runner_ViewTask extends javax.swing.JFrame {

    private List<RunnerTask> tasks;
    private String username;
    private RunnerTask runnerTask;
    private UserCustomer userCustomer;
    VendorOrder vo = new VendorOrder();

    public Runner_ViewTask(String username) {
        initComponents();
        this.username = username;
        this.userCustomer = new UserCustomer();
        DefaultTableModel tableCartModel = (DefaultTableModel) table_task.getModel();
        runnerTask = new RunnerTask();
        int vendorID = userCustomer.getUserID(username);
        String ID = String.valueOf(vendorID);
        JTableHeader tableHeader1 = table_task.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 14);
        tableHeader1.setFont(headerFont1);

        displayTask(runnerTask.getRunnerTask(ID, true));

    }

    private void displayTask(List<List<String>> taskItems) {
        DefaultTableModel model = (DefaultTableModel) table_task.getModel();
        model.setRowCount(0);
        RunnerTask runnerTask = new RunnerTask(); 

        for (List<String> taskItem : taskItems) {
            String taskID = taskItem.get(0);
            String runnerID = taskItem.get(1);
            String orderID = taskItem.get(2);
            String vendorID = taskItem.get(3);
            String taskStatus = taskItem.get(4);

            String runnerUsername = runnerTask.getUsernameForUserID(runnerID);
            String vendorUsername = runnerTask.getUsernameForUserID(vendorID);

            model.addRow(new Object[]{taskID, runnerUsername, orderID, vendorUsername, taskStatus});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbl_viewtask = new javax.swing.JLabel();
        panel_task = new javax.swing.JScrollPane();
        table_task = new javax.swing.JTable();
        btn_accept = new javax.swing.JButton();
        btn_decline = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        lbl_viewtask.setBackground(new java.awt.Color(255, 51, 153));
        lbl_viewtask.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        lbl_viewtask.setForeground(new java.awt.Color(102, 102, 102));
        lbl_viewtask.setText("View Task");
        lbl_viewtask.setToolTipText("");

        table_task.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_task.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "TaskID", "Runner", "OrderID", "Vendor", "TaskStatus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_task.setRowHeight(30);
        table_task.getTableHeader().setReorderingAllowed(false);
        panel_task.setViewportView(table_task);
        if (table_task.getColumnModel().getColumnCount() > 0) {
            table_task.getColumnModel().getColumn(0).setResizable(false);
            table_task.getColumnModel().getColumn(1).setResizable(false);
            table_task.getColumnModel().getColumn(2).setResizable(false);
            table_task.getColumnModel().getColumn(3).setResizable(false);
            table_task.getColumnModel().getColumn(4).setResizable(false);
        }

        btn_accept.setBackground(new java.awt.Color(0, 255, 204));
        btn_accept.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btn_accept.setText("Accept");
        btn_accept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_acceptActionPerformed(evt);
            }
        });

        btn_decline.setBackground(new java.awt.Color(255, 0, 51));
        btn_decline.setFont(new java.awt.Font("Microsoft YaHei", 1, 18)); // NOI18N
        btn_decline.setText("Decline");
        btn_decline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_declineActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(panel_task, javax.swing.GroupLayout.PREFERRED_SIZE, 732, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(lbl_viewtask)
                        .addGap(308, 308, 308))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btn_accept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_decline, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
                        .addGap(164, 164, 164))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_viewtask, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_task, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_accept, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btn_decline, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_declineActionPerformed
        int selectedRow = table_task.getSelectedRow();
        if (selectedRow != -1) {
            String taskID = table_task.getValueAt(selectedRow, 0).toString();

            RunnerTask runnerTask = new RunnerTask();
            if (runnerTask.updateTaskStatus(taskID, "Declined")) {
                runnerTask.declineTask(taskID);

                String runnerID = String.valueOf(userCustomer.getUserID(username));
                displayTask(runnerTask.getRunnerTask(runnerID, true));
                JOptionPane.showMessageDialog(this, "Task Declined", "Task Declined", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_declineActionPerformed

    private void btn_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_acceptActionPerformed
        int selectedRow = table_task.getSelectedRow();
        if (selectedRow != -1) {
            String taskID = table_task.getValueAt(selectedRow, 0).toString();

            RunnerTask runnerTask = new RunnerTask();
            if (runnerTask.updateTaskStatus(taskID, "Accepted")) {
                String runnerID = String.valueOf(userCustomer.getUserID(username));
                displayTask(runnerTask.getRunnerTask(runnerID, true));

                String orderID = runnerTask.getOrderIdForTaskId(taskID);
                int customerID = Integer.parseInt(vo.getCustomerUserID(orderID));
                int vendorID = Integer.parseInt(vo.getVendorID(orderID));
                
                runnerTask.generateDeliveryAssignedNotification(customerID, orderID);
                runnerTask.generateDeliveryAssignedNotification(vendorID, orderID);
                JOptionPane.showMessageDialog(this, "Task Accepted", "Task Accepted", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_btn_acceptActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Runner_ViewTask("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_accept;
    private javax.swing.JButton btn_decline;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbl_viewtask;
    private javax.swing.JScrollPane panel_task;
    private javax.swing.JTable table_task;
    // End of variables declaration//GEN-END:variables
}
