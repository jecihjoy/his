
package mmusthis;

import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.awt.event.ActionEvent;
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
import static mmusthis.DispensePanel.count;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jecihjoy
 */
public class OrderDrugs extends javax.swing.JPanel {
    public static String dname, amount;
    private File file;
    public int total;
    
    Connection conn = null;
    ResultSet rs = null;
    Statement st;
    PreparedStatement pst = null;

    public OrderDrugs() {
        initComponents();
        selectDrugs();
        trackDrugs();
        FewDrugs few =new FewDrugs();
        jTextField1.setText(few.getName());
    }
    public void trackDrugs(){
        int countMinDrugs = 0;
        int correctcount = 0;
        int painkillers = 500;
        int antiviral = 300;
        int anticeptics = 200;
        int antifungal = 100;
        int amount;
        String name;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            String sql1 = "SELECT  drugs_name, drug_desc, drug_amount, category, expirydate FROM `tbl_drugs`\n" +
            "WHERE category = 'antifungal' AND drug_amount = '"+antifungal+"'  OR category = 'antifungal' AND drug_amount < '"+antifungal+"' \n" +
            "OR  category = 'anticeptics' AND drug_amount = '"+anticeptics+"'  OR category = 'anticeptics' AND drug_amount < '"+anticeptics+"'\n" +
            "OR  category = 'pain killers' AND drug_amount = '"+painkillers+"'  OR category = 'pain killers' AND drug_amount < '"+painkillers+"'\n" +
            "OR  category = 'anti viral' AND drug_amount = '"+antiviral+"'  OR category = 'anti viral' AND drug_amount < '"+antiviral+"'";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            if (rs.next()) {
               String nameOfDrug = rs.getString("drugs_name");
            }
            rs.last();
            correctcount=rs.getRow();
            
            innercount.setText(String.valueOf(correctcount));
            System.out.print("count value " +countMinDrugs); 
            System.out.print("label value " +count.getText());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }


    private void selectDrugs() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            String sql1 = "SELECT DrugName,DrugAmount FROM `tbl_orders`";
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
        jPanel3 = new javax.swing.JPanel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        innercount = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 153, 255));
        setPreferredSize(new java.awt.Dimension(1050, 598));

        jPanel1.setBackground(new java.awt.Color(204, 255, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Order Drugs"));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 762, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jButton4.setText("Delete");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("ORDER DRUGS");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("ALL DRUGS");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("DISPENSE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("VISUALIZER");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton7)
                .addGap(8, 8, 8)
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton8)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton5)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addContainerGap())
        );

        innercount.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        innercount.setForeground(new java.awt.Color(255, 51, 51));
        innercount.setText("0");

        jLabel4.setForeground(new java.awt.Color(51, 51, 255));
        jLabel4.setText("Drugs below minimum.");

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 51, 204));
        jLabel5.setText("View");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 774, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(searchdrug, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(130, 130, 130)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton4))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                    .addComponent(damount))
                                .addGap(49, 49, 49)
                                .addComponent(innercount)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(59, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(innercount, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(damount, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(add)
                    .addComponent(jButton1)
                    .addComponent(jButton4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(jButton2)
                        .addGap(42, 42, 42)
                        .addComponent(jButton3))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(searchdrug, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        // TODO add your handling code here:
        addDrugs();
        selectDrugs();
               
    }//GEN-LAST:event_addActionPerformed

    private void searchdrugKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchdrugKeyReleased
        // TODO add your handling code here:
        searchDrug();
    }//GEN-LAST:event_searchdrugKeyReleased

    private void DrugsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrugsTableMouseClicked
        // TODO add your handling code here:
        int row=DrugsTable.getSelectedRow();
         dname=DrugsTable.getValueAt(row, 0).toString();
         amount = DrugsTable.getValueAt(row, 1).toString();
        
        jTextField1.setText(dname);
        getOldTotal(dname);
        //damount.setText(amount);
    }//GEN-LAST:event_DrugsTableMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
deleteDrugs();
selectDrugs();// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new DispensePanel());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new AllDrugs());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new OrderDrugs());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton5ActionPerformed

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

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new FewDrugs());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        updateDrugs();
        selectDrugs();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
         Registration.panelmain.removeAll();
        Registration.panelmain.add(new drugvisualizer());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DrugsTable;
    private javax.swing.JButton add;
    private javax.swing.JTextField damount;
    public static javax.swing.JLabel innercount;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTextField jTextField1;
    private javax.swing.JTextField searchdrug;
    // End of variables declaration//GEN-END:variables
}
