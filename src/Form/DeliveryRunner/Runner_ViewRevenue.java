/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.DeliveryRunner;

import Class.RunnerRevenue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class Runner_ViewRevenue extends javax.swing.JFrame {

    private RunnerRevenue runnerRevenue;
    private String username;

    /**
     * Creates new form Runner_ViewRevenue
     */
    public Runner_ViewRevenue(String username) {
        initComponents();
        this.username = username;
        runnerRevenue = new RunnerRevenue(username);
        displayDebitTransactions(runnerRevenue.getDebitTransaction(username));
        JTableHeader tableHeader1 = table_revenue.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 14);
        tableHeader1.setFont(headerFont1);

        cbox_revenue.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                String selectedDateInterval = (String) cbox_revenue.getSelectedItem();
                updateTableBasedOnDate(selectedDateInterval);
            }
        }
        );
    }

    private void displayDebitTransactions(List<List<String>> debitTransactions) {
        DefaultTableModel model = (DefaultTableModel) table_revenue.getModel();
        model.setRowCount(0); // Clear existing rows
        table_revenue.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        table_revenue.setRowHeight(18);
        for (List<String> transactionLine : debitTransactions) {

            List<String> displayData = new ArrayList<>();
            displayData.add(transactionLine.get(0));  // OrderID
            displayData.add(transactionLine.get(2));  // OrderPlacementTime
            displayData.add(transactionLine.get(3));  // OrderItemID
            displayData.add(transactionLine.get(4));  // OrderAmount
            displayData.add(transactionLine.get(5));
            model.addRow(displayData.toArray());
        }
    }

    public void filterTransactionByDateInterval(List<List<String>> orderItems, String selectedDateInterval) {
        LocalDateTime current = LocalDateTime.now();
        LocalDateTime orderTime;
        List<List<String>> filteredOrders = new ArrayList<>();

        for (List<String> orderItem : orderItems) {
            orderTime = LocalDateTime.parse(orderItem.get(3), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            switch (selectedDateInterval) {
                case "All":
                    // No date filtering needed, add all orders
                    filteredOrders.add(orderItem);
                    System.out.println("Error parsing date: " + orderItem.get(2) + " at index " + orderItems.indexOf(orderItem));
                    break;
                case "Daily":
                    // Compare orderTime with today's date
                    if (orderTime.toLocalDate().equals(current.toLocalDate())) {
                        filteredOrders.add(orderItem);
                    }
                    break;
                case "Monthly":
                    // Compare orderTime with this month's date
                    if (orderTime.getMonth().equals(current.getMonth()) && orderTime.getYear() == current.getYear()) {
                        filteredOrders.add(orderItem);
                    }
                    break;
                case "Quarterly":
                    // Compare orderTime with current year and quarter
                    if (orderTime.getYear() == current.getYear()) {
                        int orderQuarter = (orderTime.getMonthValue() - 1) / 3 + 1; // Calculate the quarter
                        int currentQuarter = (current.getMonthValue() - 1) / 3 + 1;

                        if (orderQuarter == currentQuarter) {
                            filteredOrders.add(orderItem);
                        }
                    }
                    break;
                case "Yearly":
                    // Compare orderTime year with current year
                    if (orderTime.getYear() == current.getYear()) {
                        filteredOrders.add(orderItem);
                    }
                    break;
            }
        }

        displayDebitTransactions(filteredOrders);
    }

    private void updateTableBasedOnDate(String selectedDateInterval) {
        // Read the order file and filter orders based on the selected status and date interval
        List<List<String>> filteredOrders = runnerRevenue.getDebitTransaction(username);
        filterTransactionByDateInterval(filteredOrders, selectedDateInterval);

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
        table_revenue = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        cbox_revenue = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setFocusable(false);
        setPreferredSize(new java.awt.Dimension(809, 595));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        table_revenue.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_revenue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "TransactionID", "TransactionAmount", "DateTime", "TransactionType", "Remark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_revenue.setRowHeight(30);
        table_revenue.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_revenue);
        if (table_revenue.getColumnModel().getColumnCount() > 0) {
            table_revenue.getColumnModel().getColumn(0).setResizable(false);
            table_revenue.getColumnModel().getColumn(1).setResizable(false);
            table_revenue.getColumnModel().getColumn(2).setResizable(false);
            table_revenue.getColumnModel().getColumn(3).setResizable(false);
            table_revenue.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel1.setBackground(new java.awt.Color(0, 204, 204));
        jLabel1.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 153));
        jLabel1.setText("View Revenue");

        cbox_revenue.setFont(new java.awt.Font("Georgia", 0, 14)); // NOI18N
        cbox_revenue.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Daily", "Monthly", "Quarterly", "Yearly" }));
        cbox_revenue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbox_revenueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(272, 272, 272))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbox_revenue, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cbox_revenue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(100, 100, 100))
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

    private void cbox_revenueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbox_revenueActionPerformed

    }//GEN-LAST:event_cbox_revenueActionPerformed

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
                new Runner_ViewRevenue("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbox_revenue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table_revenue;
    // End of variables declaration//GEN-END:variables
}
