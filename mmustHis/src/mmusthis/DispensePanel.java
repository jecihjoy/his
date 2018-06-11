package mmusthis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jecihjoy
 */
public class DispensePanel extends javax.swing.JPanel {
    public static String Student, Drug_name, Tablets, Frequency, Days, Date,fname, lname;
    Connection conn = null;
    ResultSet rs = null;
    Statement st;
    PreparedStatement pst = null;
    
    public DispensePanel() {
        initComponents();
        selectDrugs();
        trackDrugs();
    }
    
    public void getNames(){
        try{
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
        String studetails = "SELECT    surname    , Other_names FROM    health.tbl_student WHERE stu_id = '"+Student+"' ";  
             pst = conn.prepareStatement(studetails);
             rs = pst.executeQuery();
             while(rs.next()){
                 fname=rs.getString("surname");
                 lname=rs.getString("Other_names");
                 System.out.print(fname + lname);
                 fnamelname.setText(fname + " " + lname);
             }
        }catch(Exception e){
            
        }
    }
    
    private void selectDrugs() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            String sql1 = "SELECT  Student, Drug_name, Tablets, Frequency, Days, Date FROM `tbl_prescriptions` WHERE statuss =  '0'";
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
    
    private void searchPatient() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            String sql1 = "SELECT  Student, Drug_name, Tablets, Frequency, Days, Date FROM `tbl_prescriptions` WHERE statuss =  '0' and Student like '%"+jTextField1.getText()+"%'";
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
    
        private void deleteDrugs(){
        try{
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/health","root","");
            Statement st = conn.createStatement();
            String sql="UPDATE `health`.`tbl_prescriptions` SET `statuss` = '1' WHERE `tbl_prescriptions`.`Student` = '"+Student+"' AND `tbl_prescriptions`.`Drug_Name` = '"+Drug_name+"'";

            st.execute(sql);

            JOptionPane.showMessageDialog(null, "Issued","Drug Box",JOptionPane.INFORMATION_MESSAGE);
            drugname.setText(null);
            tabletstxt.setText(null);
            frequencytxt.setText(null);
            txt.setText(null);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        } 

        finally{
            
        }
    }
    private void updateAmount(){
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            String sql1 = "SELECT  Student, Drug_name, Tablets, Frequency, Days, Date FROM `tbl_prescriptions` WHERE statuss = '0'";
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
            
            count.setText(String.valueOf(correctcount));
            System.out.print("count value " +countMinDrugs); 
            System.out.print("label value " +count.getText());

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        fnamelname = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        DrugsTable = new javax.swing.JTable();
        drugname = new javax.swing.JLabel();
        tabletstxt = new javax.swing.JTextField();
        frequencytxt = new javax.swing.JTextField();
        txt = new javax.swing.JTextField();
        issue = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        count = new javax.swing.JLabel();
        fewDrugs = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 204, 255));
        setPreferredSize(new java.awt.Dimension(1050, 598));

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("ISSUE DRUGS");

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("search by regno"));
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        fnamelname.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

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

        drugname.setFont(new java.awt.Font("Imprint MT Shadow", 1, 14)); // NOI18N
        drugname.setText("Drug Name");

        tabletstxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Tablets"));

        frequencytxt.setBorder(javax.swing.BorderFactory.createTitledBorder("Frequency"));

        txt.setBorder(javax.swing.BorderFactory.createTitledBorder("Days"));

        issue.setText("ISSUE");
        issue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                issueActionPerformed(evt);
            }
        });

        jButton4.setText("ORDER DRUGS");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton1.setText("ALL DRUGS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("DISPENSE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton5.setText("visualizer");
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
                .addContainerGap()
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton4)
                    .addComponent(jButton3)
                    .addComponent(jButton5))
                .addContainerGap())
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 255));
        jLabel3.setText("Drugs below minimum. ");

        count.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        count.setForeground(new java.awt.Color(255, 0, 51));
        count.setText("0");

        fewDrugs.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        fewDrugs.setForeground(new java.awt.Color(255, 51, 102));
        fewDrugs.setText("Click to view");
        fewDrugs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fewDrugsMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tabletstxt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(drugname, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(frequencytxt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(issue)
                                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(83, 83, 83)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(fnamelname, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(count)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fewDrugs, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)))
                .addGap(73, 73, 73))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fnamelname, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(count)
                            .addComponent(fewDrugs))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(drugname, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(tabletstxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(frequencytxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(issue))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void DrugsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrugsTableMouseClicked
        // TODO add your handling code here:
        int demo = DrugsTable.getSelectedRow();
          Student = DrugsTable.getValueAt(demo, 0).toString();
          Drug_name = DrugsTable.getValueAt(demo, 1).toString();
          Tablets= DrugsTable.getValueAt(demo, 2).toString();
          Frequency= DrugsTable.getValueAt(demo, 3).toString();
          Days = DrugsTable.getValueAt(demo, 4).toString();
          Date = DrugsTable.getValueAt(demo, 5).toString();
          
          fnamelname.setText(Student);
          drugname.setText(Drug_name);
          tabletstxt.setText(Tablets);
          tabletstxt.setEditable(false);
          frequencytxt.setText(Frequency);
          frequencytxt.setEditable(false);
          txt.setText(Days);
          txt.setEditable(false);
          
          getNames();
          
    }//GEN-LAST:event_DrugsTableMouseClicked

    private void issueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_issueActionPerformed
        // TODO add your handling code here:
        int amount;
        int difference;
        
        
        int total = Integer.parseInt(tabletstxt.getText()) * Integer.parseInt(frequencytxt.getText()) * Integer.parseInt(txt.getText());
        System.out.print(total);
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            st = conn.createStatement();
            String sql1 = "SELECT  drug_amount FROM `tbl_drugs` WHERE drugs_name = '"+drugname.getText()+"' ";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            while(rs.next()){
                amount=rs.getInt("drug_amount");
                difference = amount - total;
                System.out.print(difference);
            
               String updateQuerry = "UPDATE `health`.`tbl_drugs` SET `drug_amount` = '"+difference+"' WHERE `tbl_drugs`.`drugs_name` = '"+drugname.getText()+"'";
               st.execute(updateQuerry);
               
            }
 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
        deleteDrugs();
        selectDrugs();
        trackDrugs();
    }//GEN-LAST:event_issueActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        searchPatient();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new DispensePanel());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new AllDrugs());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new OrderDrugs());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void fewDrugsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fewDrugsMouseClicked
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new FewDrugs());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_fewDrugsMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         Registration.panelmain.removeAll();
        Registration.panelmain.add(new drugvisualizer());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DrugsTable;
    public static javax.swing.JLabel count;
    private javax.swing.JLabel drugname;
    private javax.swing.JLabel fewDrugs;
    private javax.swing.JLabel fnamelname;
    private javax.swing.JTextField frequencytxt;
    private javax.swing.JButton issue;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField tabletstxt;
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables
}
