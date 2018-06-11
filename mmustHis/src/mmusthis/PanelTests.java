package mmusthis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import java.time.LocalDate;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Jecihjoy
 */
public class PanelTests extends javax.swing.JPanel {
    public static String testcase2, id, stuid, fname, lname;
    Connection conn = null;
    ResultSet rs = null;
    Statement st;
    PreparedStatement pst   = null;
    public PanelTests() {
        initComponents();
        updateTable();
    }
    public void getNames(){
        try{
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
        String studetails = "SELECT    surname    , Other_names FROM    health.tbl_student WHERE stu_id = '"+stuid+"' ";  
             pst = conn.prepareStatement(studetails);
             rs = pst.executeQuery();
             while(rs.next()){
                 fname=rs.getString("surname");
                 lname=rs.getString("Other_names");
                 System.out.print(fname + lname);
                 jLabel4.setText(fname + " " + lname);
             }
        }catch(Exception e){
            
        }
    }
    
    
    private void updateTable(){
        try{
            conn=connection.dbConnect();
            String sql1 = "select testID, stu_id,test_case,test_result from tbl_tests where statuss='0'";
            pst = conn.prepareStatement(sql1);
 
            rs = pst.executeQuery();
           
            TestsTable.setModel(DbUtils.resultSetToTableModel(rs)); 
            if(rs.next()){
                
               TestsTable.setModel(DbUtils.resultSetToTableModel(rs));  
            }
            
 
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        finally{
            
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TestsTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        testcase = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        testresult = new javax.swing.JTextArea();
        saveTest = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 204, 255));
        setPreferredSize(new java.awt.Dimension(1050, 598));

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("LABORATORY TESTS");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        jTextField1.setBorder(javax.swing.BorderFactory.createTitledBorder("search by reg"));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        TestsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "#", "Reg Number", "Test Case", "Result"
            }
        ));
        TestsTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                TestsTableMouseMoved(evt);
            }
        });
        TestsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TestsTableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                TestsTableMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(TestsTable);

        jLabel2.setText("TEST CASE:");

        jLabel3.setText("TEST RESULT");

        testcase.setColumns(20);
        testcase.setRows(5);
        jScrollPane2.setViewportView(testcase);

        testresult.setColumns(20);
        testresult.setRows(5);
        jScrollPane3.setViewportView(testresult);

        saveTest.setText("SAVE");
        saveTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveTestActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jButton1.setText("cancel");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(saveTest, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))))
                .addGap(123, 123, 123))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel3)
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveTest)
                            .addComponent(jButton1)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveTestActionPerformed
        System.out.println(testresult.getText());
        try{
            
            conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/health","root","");
            st = conn.createStatement();
            String sql="UPDATE `health`.`tbl_tests` SET `statuss` = '1', `test_result` = '"+testresult.getText()+"' "
                    + "WHERE `tbl_tests`.`testID` ='"+id+"' AND `tbl_tests`.`stu_id` = '"+stuid+"';";

           st.execute(sql);
           
           updateTable();

        }catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "SERVER SAYS :"+ e.getMessage(),"SERVR ERROR",JOptionPane.OK_OPTION);
            }
        
    }//GEN-LAST:event_saveTestActionPerformed

    private void TestsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TestsTableMouseClicked
        int demo = TestsTable.getSelectedRow();
          testcase2 = TestsTable.getValueAt(demo, 2).toString();
          testcase.setText(testcase2);
          id = TestsTable.getValueAt(demo, 0).toString();
          stuid = TestsTable.getValueAt(demo, 1).toString();

          getNames();
             
    }//GEN-LAST:event_TestsTableMouseClicked

    private void TestsTableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TestsTableMouseEntered
        // TODO add your handling code here:
       // updateTable();
    }//GEN-LAST:event_TestsTableMouseEntered

    private void TestsTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TestsTableMouseMoved
        // TODO add your handling code here:
        // updateTable();
    }//GEN-LAST:event_TestsTableMouseMoved

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
         try{
            conn=connection.dbConnect();
            String sql1 = "select testID, stu_id,test_case,test_result from tbl_tests where statuss='0'  and stu_id like'%"+jTextField1.getText()+"%'";
            pst = conn.prepareStatement(sql1);
 
            rs = pst.executeQuery();
           
            TestsTable.setModel(DbUtils.resultSetToTableModel(rs)); 
            if(rs.next()){
                
               TestsTable.setModel(DbUtils.resultSetToTableModel(rs));  
            }
            
 
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
        finally{
            
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jLabel4.setText("");
        testcase.setText("");
        testresult.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TestsTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton saveTest;
    private javax.swing.JTextArea testcase;
    private javax.swing.JTextArea testresult;
    // End of variables declaration//GEN-END:variables
}
