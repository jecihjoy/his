
package mmusthis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author geoffrey
 */
public class previousRecords extends javax.swing.JPanel {
    public static String identifier;

    Connection conn = null;
    ResultSet rs = null;
    Statement st;
    PreparedStatement pst = null;
    public previousRecords() {
        initComponents();
        regno.setText(storeReg.rg);
        getPreviousRecords();
        populateTableLabTests();
        populateTablePrescription();
        getDates();
        
        
        System.out.println(regno.getText());
    }
    private void getPreviousRecords(){
     //   String query="select * from tbl_diagnosis,tbl_prescriptions,tbl_tests where tbl_diagnosis.stuid=tbl_prescriptions.Student and tbl_diagnosis.stuid=tbl_tests.stu_id and tbl_diagnosis.stuid=?"
             //   + " and  DATE(tbl_diagnosis.date)=DATE(tbl_prescriptions.Date) and DATE(tbl_diagnosis.date)=DATE(tbl_tests.date)";
       String query=" select * from tbl_diagnosis where stuid=? and DATE(date)=DATE(now())";
        try{
            conn=connection.dbConnect();
            pst=conn.prepareStatement(query);
            pst.setString(1, regno.getText());
            rs=pst.executeQuery();
            if(rs.next()){
                jTextArea2.setText(rs.getString("signs_symptoms"));
                jTextArea1.setText(rs.getString("remarks"));
                jTextField1.setText(rs.getString("disease"));
                //jLabel1.setText("<html>"+rs.getString("Student")+" "+rs.getString("test_case")+" "+rs.getString("test_result")+" "+rs.getString("date")+"</html>");
            }
            else{
                 JOptionPane.showMessageDialog(null, "No information available","info",JOptionPane.INFORMATION_MESSAGE);
            }
            
        }catch(Exception ee){
            ee.printStackTrace();
            JOptionPane.showMessageDialog(null, ee.getMessage());
        }
        try{
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    private void populateTableLabTests(){
       String query="select test_case,test_result from tbl_tests where stu_id=? and DATE(date)=DATE(now())";
// String query="select test_case,test_result from tbl_tests, tbl_diagnosis, tbl_prescriptions where tbl_tests.stu_id=? AND  DATE(tbl_tests.date)=DATE(tbl_diagnosis.date) and DATE(tbl_prescriptions.Date)=DATE(tbl_diagnosis.date) ";
         try {
            conn = connection.dbConnect();
            pst = conn.prepareStatement(query);
            pst.setString(1, regno.getText());
            rs = pst.executeQuery();

            jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            if (rs.next()) {

                jTable2.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (Exception ex) {
            //JOptionPane.showMessageDialog(null, ex);
            ex.printStackTrace();
        } finally {

        }
    }
    private void populateTablePrescription(){
          //String query="select Drug_Name,Days from tbl_prescriptions, tbl_diagnosis,tbl_tests where tbl_prescriptions.Student=? AND  DATE(tbl_prescriptions.Date)=DATE(tbl_diagnosis.date) and DATE(tbl_prescriptions.Date)=DATE(tbl_tests.date) and DATE(tbl_diagnosis.date)=DATE(tbl_tests.date) ";
         String query="select Drug_Name,Days from tbl_prescriptions where Student=? and DATE(Date)=DATE(now()) ";
          try {
            conn = connection.dbConnect();
            pst = conn.prepareStatement(query);
            pst.setString(1, regno.getText());
            rs = pst.executeQuery();

            jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            if (rs.next()) {

                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }
    private void getDates(){
        String  query="select DATE(date) as dates from tbl_diagnosis where stuid='"+regno.getText()+"'";
        try{
            conn=connection.dbConnect();
            pst=conn.prepareStatement(query);
            rs=pst.executeQuery();
            while(rs.next()){
                dates.removeAll();
                dates.addItem(rs.getString("dates"));
            }
        }catch(Exception ee){
            ee.printStackTrace();
        }
    }
   
                                                                                                                                                                                                                                                                                               
   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        regno = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        statusmessage = new javax.swing.JLabel();
        dates = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 204, 255));
        setPreferredSize(new java.awt.Dimension(1005, 518));

        regno.setText("reg here");

        jLabel2.setText("JACINTA GICHUHI");

        jTextArea2.setEditable(false);
        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setBorder(javax.swing.BorderFactory.createTitledBorder("Signs and Symptoms"));
        jScrollPane2.setViewportView(jTextArea2);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Drug Name", "Days"
            }
        ));
        jScrollPane3.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Number", "Test Cases", "Test Result"
            }
        ));
        jScrollPane1.setViewportView(jTable2);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setBorder(javax.swing.BorderFactory.createTitledBorder("Remarks"));
        jScrollPane4.setViewportView(jTextArea1);

        jTextField1.setEditable(false);
        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("Disease"));

        jButton1.setText("PREVIOUS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("NEXT");

        statusmessage.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        statusmessage.setForeground(new java.awt.Color(255, 0, 0));

        jLabel3.setText("choose date");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(111, 111, 111)
                                .addComponent(jButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(38, 38, 38)
                                .addComponent(statusmessage, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                                    .addComponent(jTextField1)
                                    .addComponent(jScrollPane2))))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(regno, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(71, 71, 71)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(96, 96, 96)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(dates, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regno, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dates, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statusmessage, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> dates;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    public static javax.swing.JLabel regno;
    private javax.swing.JLabel statusmessage;
    // End of variables declaration//GEN-END:variables
}
