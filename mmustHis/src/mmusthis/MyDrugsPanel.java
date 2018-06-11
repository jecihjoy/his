/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mmusthis;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static mmusthis.DispensePanel.Date;
import static mmusthis.OrderDrugs.dname;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jecihjoy
 */
public class MyDrugsPanel extends javax.swing.JPanel {
public static String dname, amount;
    private File file;
    public int total;
    
    Connection conn = null;
    ResultSet rs = null;
    Statement st;
    PreparedStatement pst = null;

 
    /**
     * Creates new form MyDrugsPanel
     */
    public MyDrugsPanel() {
        initComponents();
        selectDrugs();
        FewDrugs few =new FewDrugs();
    }
    private void selectDrugs() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            String sql1 = "SELECT DrugName, DrugAmount FROM `tbl_orders`";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();

            DrugsTable.setModel(DbUtils.resultSetToTableModel(rs));
            if (rs.next()) {

                DrugsTable.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }
    
     private void searchDrug() {
        try {
            conn =connection.dbConnect();
            String sql1 = "SELECT * FROM `tbl_orders` where DrugName like '%"+searchdrug.getText()+"%'";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();

            DrugsTable.setModel(DbUtils.resultSetToTableModel(rs));
            if (rs.next()) {

                DrugsTable.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }
    
    private void addDrugs() {
        String name = jTextField1.getText();
        String total = damount.getText().toString();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            Statement st = conn.createStatement();
            String sql = "INSERT INTO `health`.`tbl_orders` (`DrugName`, `DrugAmount`) VALUES('" + name + "','" + total + "')";

            st.execute(sql);

            JOptionPane.showMessageDialog(null, "Drug Added", "Drug Box", JOptionPane.INFORMATION_MESSAGE);
            //searchdrug.setText(null);
            damount.setText(null);
            damount.setText(null);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }
    
        private void deleteDrugs(){
        String name = jTextField1.getText();
        String total = damount.getText().toString();
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/health","root","");
            Statement st = conn.createStatement();
            String sql="DELETE FROM `health`.`tbl_orders` WHERE `tbl_orders`.`DrugName` = '" + name + "'";

            st.execute(sql);

            JOptionPane.showMessageDialog(null, "Deleted","Drug Box",JOptionPane.INFORMATION_MESSAGE);
            damount.setText(null);
            damount.setText(null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        } 

        finally{
            
        }
    }
        
    private int getOldTotal(String druname){

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            st = conn.createStatement();
            String sql1 = "SELECT  DrugAmount FROM `tbl_orders` WHERE DrugName = '"+druname+"' ";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            while(rs.next()){
                total=rs.getInt("DrugAmount");
                return total;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            //return 0;
        } 
        return total;
    }
    
    private void updateDrugs() {
        String name = jTextField1.getText();
        int total = Integer.parseInt(damount.getText().toString());
        
        int oldTotal = getOldTotal(name);
        int neweTotal = oldTotal + total;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            Statement st = conn.createStatement();
            String sql = "UPDATE `health`.`tbl_orders` SET `DrugAmount` = '"+neweTotal+"' WHERE `tbl_orders`.`DrugName` = '"+name+"'";

            st.execute(sql);

            JOptionPane.showMessageDialog(null, "update successful", "Drug Orders", JOptionPane.OK_OPTION);

            jTextField1.setText(null);
            damount.setText(null);

            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

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
        searchdrug = new javax.swing.JTextField();
        damount = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        add = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DrugsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(1050, 598));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Order Drugs"));
        jPanel1.setPreferredSize(new java.awt.Dimension(1050, 598));

        searchdrug.setBorder(javax.swing.BorderFactory.createTitledBorder("Search Drug"));
        searchdrug.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchdrugKeyReleased(evt);
            }
        });

        jLabel1.setText("Amount");

        add.setText("ADD");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        jButton1.setText("Update");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        DrugsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        DrugsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DrugsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(DrugsTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setText("Drug name");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jButton2.setText("generate pdf");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("send order");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton9.setText("HOME");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton5.setText("REPORT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(89, 89, 89))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(97, 97, 97)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(damount, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4)
                                .addGap(17, 17, 17))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(searchdrug, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addComponent(jButton2)
                        .addGap(52, 52, 52)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(437, 437, 437))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(717, 717, 717)
                        .addComponent(jButton9)
                        .addGap(80, 80, 80)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(jButton5))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(damount, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add)
                            .addComponent(jButton1)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchdrug, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(102, 102, 102))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1974, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void searchdrugKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchdrugKeyReleased
        // TODO add your handling code here:
        searchDrug();
    }//GEN-LAST:event_searchdrugKeyReleased

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        if("".equals(jTextField1.getText())){
            JOptionPane.showMessageDialog(null, "No drug provided!","error",JOptionPane.ERROR_MESSAGE);
        }
        else if("".equals(damount.getText()) || Integer.parseInt(damount.getText())<1){
            JOptionPane.showMessageDialog(null, "Invalid value for amount!","error",JOptionPane.ERROR_MESSAGE);
        }
        else{
        addDrugs();
        }
        selectDrugs();
    }//GEN-LAST:event_addActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         if("".equals(jTextField1.getText())){
            JOptionPane.showMessageDialog(null, "No drug provided!","error",JOptionPane.ERROR_MESSAGE);
        }
        else if("".equals(damount.getText()) || Integer.parseInt(damount.getText())<1){
            JOptionPane.showMessageDialog(null, "Invalid value for amount!","error",JOptionPane.ERROR_MESSAGE);
        }
        else{
        updateDrugs();
        }
        selectDrugs();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void DrugsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrugsTableMouseClicked
        // TODO add your handling code here:
        int row=DrugsTable.getSelectedRow();
        dname=DrugsTable.getValueAt(row, 0).toString();
        amount = DrugsTable.getValueAt(row, 1).toString();

        jTextField1.setText(dname);
        getOldTotal(dname);
        //damount.setText(amount);
    }//GEN-LAST:event_DrugsTableMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("drug_order.pdf"));

        int chooserresult = chooser.showSaveDialog(null);
        if (chooserresult == JFileChooser.APPROVE_OPTION) {
            String filepath = chooser.getSelectedFile().getPath();

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
                String sql1 = "select * from tbl_orders";
                pst = conn.prepareStatement(sql1);
                rs = pst.executeQuery();

                pst = conn.prepareStatement(sql1);
                // rst=pst.executeQuery();
                Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(filepath));

                PdfPTable pdftable = new PdfPTable(2);
                doc.open();

                float[] columnwidths = {10, 10};
                pdftable.setWidths(columnwidths);

                pdftable.setWidthPercentage(100);

                doc.add(new Paragraph("Order Summary", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                doc.add(new Paragraph(new Date().toString()));
                doc.add(new Paragraph("\n"));

                pdftable.addCell(new PdfPCell(new Paragraph("Drug Number", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                pdftable.addCell(new PdfPCell(new Paragraph("Drug Name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));

                while (rs.next()) {
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(1), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(2), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                }
                doc.add(pdftable);
                doc.add(new Paragraph("\n"));
                doc.close();

                JOptionPane.showMessageDialog(null, "pdf genarated successifully");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        new MailDialog();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        deleteDrugs();
        selectDrugs();// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new inventory());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new Drugs20());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DrugsTable;
    private javax.swing.JButton add;
    private javax.swing.JTextField damount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JTextField searchdrug;
    // End of variables declaration//GEN-END:variables
}
