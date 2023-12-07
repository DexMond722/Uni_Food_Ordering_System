/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form.Vendor;

import Class.VendorRevenue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class Vendor_RevenueDashboard extends javax.swing.JFrame {

    private VendorRevenue vendorRevenue;
    private final String username;

    public Vendor_RevenueDashboard(String username) {
        initComponents();
        this.username = username;
        this.vendorRevenue = new VendorRevenue(username);
        displayDebitTransactions(vendorRevenue.getDebitTransaction(username));
        displayCreditTransactions(vendorRevenue.getCreditTransaction(username));
        JTableHeader tableHeader1 = table_Debit.getTableHeader();
        Font headerFont1 = new Font("Georgia", Font.BOLD, 12);
        tableHeader1.setFont(headerFont1);
        JTableHeader tableHeader2 = table_Credit.getTableHeader();
        tableHeader2.setFont(headerFont1);
        setDebitAndCreditLabel();
    }

    private void displayDebitTransactions(List<List<String>> debitTransactions) {
        DefaultTableModel model = (DefaultTableModel) table_Debit.getModel();
        model.setRowCount(0); // Clear existing rows
        table_Debit.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        for (List<String> transactionLine : debitTransactions) {

            List<String> displayData = new ArrayList<>();
            displayData.add(transactionLine.get(0)); 
            displayData.add(transactionLine.get(2));  
            displayData.add(transactionLine.get(3)); 
            displayData.add(transactionLine.get(5));
            model.addRow(displayData.toArray());

        }
    }

    private void displayCreditTransactions(List<List<String>> creditTransactions) {
        DefaultTableModel model = (DefaultTableModel) table_Credit.getModel();
        model.setRowCount(0); // Clear existing rows
        table_Credit.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        for (List<String> transactionLine : creditTransactions) {

            List<String> displayData = new ArrayList<>();
            displayData.add(transactionLine.get(0)); 
            displayData.add(transactionLine.get(2));
            displayData.add(transactionLine.get(3));  
            displayData.add(transactionLine.get(5));
            model.addRow(displayData.toArray());

        }
    }

    private void setDebitAndCreditLabel() {
        double debitAmount = vendorRevenue.getDebitTransactionAmount(username);
        String debitCount = String.valueOf(debitAmount);

        double creditAmount = vendorRevenue.getCreditTransactionAmount(username);
        String creditCount = String.valueOf(creditAmount);

        double totalAmount = debitAmount - creditAmount;
        String totalCount = String.valueOf(totalAmount);

        lbl_TotalDebit.setText("Total Debit Amount: RM" + debitCount);
        lbl_TotalCredit.setText("Total Credit Amount: RM" + creditCount);
        lbl_TotalProfit.setText("Total Profit: RM" + totalCount);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_Debit = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        table_Credit = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lbl_TotalDebit = new javax.swing.JLabel();
        lbl_TotalCredit = new javax.swing.JLabel();
        lbl_TotalProfit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1200, 650));
        setMinimumSize(new java.awt.Dimension(1200, 650));
        setPreferredSize(new java.awt.Dimension(1200, 650));
        setSize(new java.awt.Dimension(1200, 650));
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setMaximumSize(new java.awt.Dimension(1200, 650));
        jPanel2.setMinimumSize(new java.awt.Dimension(1200, 650));
        jPanel2.setPreferredSize(new java.awt.Dimension(1200, 650));

        table_Debit.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_Debit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "TransactionID", "Amount", "DateTime", "Remark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Debit.setRowHeight(30);
        table_Debit.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(table_Debit);
        if (table_Debit.getColumnModel().getColumnCount() > 0) {
            table_Debit.getColumnModel().getColumn(0).setResizable(false);
            table_Debit.getColumnModel().getColumn(0).setPreferredWidth(30);
            table_Debit.getColumnModel().getColumn(1).setResizable(false);
            table_Debit.getColumnModel().getColumn(1).setPreferredWidth(10);
            table_Debit.getColumnModel().getColumn(2).setResizable(false);
            table_Debit.getColumnModel().getColumn(2).setPreferredWidth(90);
            table_Debit.getColumnModel().getColumn(3).setResizable(false);
            table_Debit.getColumnModel().getColumn(3).setPreferredWidth(160);
        }

        jLabel1.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel1.setText("Debit Transaction");

        table_Credit.setFont(new java.awt.Font("Georgia", 0, 16)); // NOI18N
        table_Credit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "TransactionID", "Amount", "DateTime", "Remark"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table_Credit.setRowHeight(30);
        table_Credit.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(table_Credit);
        if (table_Credit.getColumnModel().getColumnCount() > 0) {
            table_Credit.getColumnModel().getColumn(0).setResizable(false);
            table_Credit.getColumnModel().getColumn(0).setPreferredWidth(30);
            table_Credit.getColumnModel().getColumn(1).setResizable(false);
            table_Credit.getColumnModel().getColumn(1).setPreferredWidth(10);
            table_Credit.getColumnModel().getColumn(2).setResizable(false);
            table_Credit.getColumnModel().getColumn(2).setPreferredWidth(90);
            table_Credit.getColumnModel().getColumn(3).setResizable(false);
            table_Credit.getColumnModel().getColumn(3).setPreferredWidth(160);
        }

        jLabel2.setFont(new java.awt.Font("Georgia", 1, 18)); // NOI18N
        jLabel2.setText("Credit Transaction");

        jLabel3.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 204));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("View Revenue");
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        lbl_TotalDebit.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        lbl_TotalDebit.setForeground(new java.awt.Color(255, 51, 0));
        lbl_TotalDebit.setText("Total Debit Amount:");
        lbl_TotalDebit.setMaximumSize(new java.awt.Dimension(110, 16));
        lbl_TotalDebit.setMinimumSize(new java.awt.Dimension(110, 16));

        lbl_TotalCredit.setFont(new java.awt.Font("Georgia", 0, 18)); // NOI18N
        lbl_TotalCredit.setForeground(new java.awt.Color(255, 51, 51));
        lbl_TotalCredit.setText("Total Credit Amount:");

        lbl_TotalProfit.setFont(new java.awt.Font("Georgia", 0, 36)); // NOI18N
        lbl_TotalProfit.setForeground(new java.awt.Color(255, 51, 51));
        lbl_TotalProfit.setText("jLabel4");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(152, 152, 152)
                                .addComponent(lbl_TotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 571, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129)
                                .addComponent(lbl_TotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(442, 442, 442)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lbl_TotalProfit, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(373, 373, 373))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbl_TotalCredit, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_TotalDebit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(38, 38, 38)
                .addComponent(lbl_TotalProfit, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 1200, 650);

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
//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Vendor_RevenueDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Vendor_RevenueDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Vendor_RevenueDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Vendor_RevenueDashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Vendor_RevenueDashboard("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_TotalCredit;
    private javax.swing.JLabel lbl_TotalDebit;
    private javax.swing.JLabel lbl_TotalProfit;
    private javax.swing.JTable table_Credit;
    private javax.swing.JTable table_Debit;
    // End of variables declaration//GEN-END:variables
}
