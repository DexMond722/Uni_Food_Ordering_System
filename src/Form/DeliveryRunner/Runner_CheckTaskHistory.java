/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.DeliveryRunner;

import Class.RunnerTask;
import Class.UserCustomer;
import java.awt.Font;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class Runner_CheckTaskHistory extends javax.swing.JFrame {

    private List<RunnerTask> tasks;
    private String username;
    private RunnerTask runnerTask;
    private UserCustomer userCustomer;

    public Runner_CheckTaskHistory(String username) {
        initComponents();
        this.username = username;
        this.userCustomer = new UserCustomer();
        DefaultTableModel tableCartModel = (DefaultTableModel) table_taskhistory.getModel();
        runnerTask = new RunnerTask();
        int vendorID = userCustomer.getUserID(username);
        String ID = String.valueOf(vendorID);
        JTableHeader tableHeader1 = table_taskhistory.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 14);
        tableHeader1.setFont(headerFont1);
        displayTaskHistory(runnerTask.getRunnerTask(ID, false));
    }

    private void displayTaskHistory(List<List<String>> taskItems) {
        DefaultTableModel model = (DefaultTableModel) table_taskhistory.getModel();
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_taskhistory = new javax.swing.JTable();
        lbl_taskhistory = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setFocusable(false);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table_taskhistory.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_taskhistory.setModel(new javax.swing.table.DefaultTableModel(
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
        table_taskhistory.setRowHeight(30);
        table_taskhistory.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_taskhistory);
        if (table_taskhistory.getColumnModel().getColumnCount() > 0) {
            table_taskhistory.getColumnModel().getColumn(0).setResizable(false);
            table_taskhistory.getColumnModel().getColumn(1).setResizable(false);
            table_taskhistory.getColumnModel().getColumn(2).setResizable(false);
            table_taskhistory.getColumnModel().getColumn(3).setResizable(false);
            table_taskhistory.getColumnModel().getColumn(4).setResizable(false);
        }

        lbl_taskhistory.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        lbl_taskhistory.setForeground(new java.awt.Color(153, 153, 0));
        lbl_taskhistory.setText("Task History");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbl_taskhistory, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(252, 252, 252))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl_taskhistory, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Runner_CheckTaskHistory("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_taskhistory;
    private javax.swing.JTable table_taskhistory;
    // End of variables declaration//GEN-END:variables
}
