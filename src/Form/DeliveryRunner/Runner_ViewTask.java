/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.DeliveryRunner;

import Class.RunnerTask;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class Runner_ViewTask extends javax.swing.JFrame {

    private List<RunnerTask> tasks;
    private String username;
    private RunnerTask runnerTask;

    public Runner_ViewTask(String username) {
        initComponents();
        this.username = username;
        DefaultTableModel tableCartModel = (DefaultTableModel) table_task.getModel();
        runnerTask = new RunnerTask();
        int vendorID = runnerTask.getRunnerID(username);
        String ID = String.valueOf(vendorID);
        
        displayTask(runnerTask.getRunnerTask(ID,true));

    }

    private void displayTask(List<List<String>> taskItems) {
        DefaultTableModel model = (DefaultTableModel) table_task.getModel();
        model.setRowCount(0);
        RunnerTask runnerTask = new RunnerTask(); // Create an instance of RunnerTask

        for (List<String> taskItem : taskItems) {
            // Assuming taskItem contains TaskID, RunnerID, OrderID, VendorID, TaskStatus
            String taskID = taskItem.get(0);
            String runnerID = taskItem.get(1);
            String orderID = taskItem.get(2);
            String vendorID = taskItem.get(3);
            String taskStatus = taskItem.get(4);

            // Fetch usernames based on UserIDs using the instance of RunnerTask
            String runnerUsername = runnerTask.getUsernameForUserID(runnerID);
            String vendorUsername = runnerTask.getUsernameForUserID(vendorID);

            // Add a new row with usernames
            model.addRow(new Object[]{taskID, runnerUsername, orderID, vendorUsername, taskStatus});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_accept = new javax.swing.JButton();
        btn_decline = new javax.swing.JButton();
        panel_task = new javax.swing.JScrollPane();
        table_task = new javax.swing.JTable();
        lbl_viewtask = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        table_task.getTableHeader().setReorderingAllowed(false);
        panel_task.setViewportView(table_task);
        if (table_task.getColumnModel().getColumnCount() > 0) {
            table_task.getColumnModel().getColumn(0).setResizable(false);
            table_task.getColumnModel().getColumn(1).setResizable(false);
            table_task.getColumnModel().getColumn(2).setResizable(false);
            table_task.getColumnModel().getColumn(3).setResizable(false);
            table_task.getColumnModel().getColumn(4).setResizable(false);
        }

        lbl_viewtask.setBackground(new java.awt.Color(255, 51, 153));
        lbl_viewtask.setFont(new java.awt.Font("Tw Cen MT Condensed Extra Bold", 1, 65)); // NOI18N
        lbl_viewtask.setForeground(new java.awt.Color(102, 102, 102));
        lbl_viewtask.setText("View Task");
        lbl_viewtask.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(panel_task, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(110, 110, 110)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btn_decline, javax.swing.GroupLayout.DEFAULT_SIZE, 578, Short.MAX_VALUE)
                            .addComponent(btn_accept, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(63, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_viewtask, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(238, 238, 238))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(lbl_viewtask, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel_task, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(btn_accept, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_decline, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(117, 117, 117))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_declineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_declineActionPerformed
        int selectedRow = table_task.getSelectedRow();
        if (selectedRow != -1) {
            String taskID = table_task.getValueAt(selectedRow, 0).toString();

            // Update the task status to "Declined"
            RunnerTask runnerTask = new RunnerTask();
            if (runnerTask.updateTaskStatus(taskID, "Declined")) {
                // Try to find another available runner and assign the task
                runnerTask.declineTask(taskID);

//            runnerTask.assignTaskToAvailableRunner(taskID);
                String runnerID = String.valueOf(runnerTask.getRunnerID(username));
                displayTask(runnerTask.getRunnerTask(runnerID,true));
            } else {
                // Handle error if the task couldn't be updated
            }
        }
    }//GEN-LAST:event_btn_declineActionPerformed

    private void btn_acceptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_acceptActionPerformed
        int selectedRow = table_task.getSelectedRow();
        if (selectedRow != -1) {
            String taskID = table_task.getValueAt(selectedRow, 0).toString();

            // Update the task status to "Accepted"
            RunnerTask runnerTask = new RunnerTask();
            if (runnerTask.updateTaskStatus(taskID, "Accepted")) {
                // Refresh the table to reflect the changes
                String runnerID = String.valueOf(runnerTask.getRunnerID(username));
                displayTask(runnerTask.getRunnerTask(runnerID,true));
            } else {
                // Handle error if the task couldn't be updated
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
    private javax.swing.JLabel lbl_viewtask;
    private javax.swing.JScrollPane panel_task;
    private javax.swing.JTable table_task;
    // End of variables declaration//GEN-END:variables
}
