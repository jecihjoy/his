
package mmusthis;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import static java.lang.Thread.sleep;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author geoffrey
 */
public class Registration extends javax.swing.JFrame {

    /**
     * Creates new form Registration
     */
     ResultSet rs = null;
    Statement smt = null;
    Connection conn = null;
    PreparedStatement pst = null;
    public Registration() {
        initComponents();
        currentDate();
        setTitle(jLabel1.getText());
        panelmain.removeAll();
        //Home home=new Home();
        
        panelmain.add(new Login());
       // panelmain.setVisible(true);
       panelmain.revalidate();
        panelmain.repaint();
    }
    private void generateReport1(){
         String regno=JOptionPane.showInputDialog("Enter student regno");
        JFileChooser chooser=new JFileChooser();
        chooser.setSelectedFile(new File(regno+"_report.pdf"));

        int chooserresult = chooser.showSaveDialog(null);
        if (chooserresult == JFileChooser.APPROVE_OPTION) {
            String filepath = chooser.getSelectedFile().getPath();

            try {
                conn = connection.dbConnect();
                String sql1 = "select Drug_Name,Tablets,Frequency,Days,DoctorId from tbl_prescriptions where Student='"+regno+"' and DATE(Date)=DATE(now())  ";
                pst = conn.prepareStatement(sql1);
                rs = pst.executeQuery();

                pst = conn.prepareStatement(sql1);
                // rst=pst.executeQuery();
                Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(filepath));

                PdfPTable pdftable = new PdfPTable(4);
                doc.open();

                float[] columnwidths = {6, 6, 6,6};
                pdftable.setWidths(columnwidths);

                pdftable.setWidthPercentage(100);

                doc.add(new Paragraph("Medical Report\t\t\t", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                doc.add(new Paragraph(new Date().toString()));
                doc.add(new Paragraph("\n"));
                pdftable.addCell(new PdfPCell(new Paragraph("Drug name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                pdftable.addCell(new PdfPCell(new Paragraph("tablets", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                pdftable.addCell(new PdfPCell(new Paragraph("frequencies", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                pdftable.addCell(new PdfPCell(new Paragraph("No of Days", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));

                

               
                while (rs.next()) {
                              
                    
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(1), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(2), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(3), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(4), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    
                    doc.add(new Paragraph(rs.getString(1)));
                    doc.add(new Paragraph(rs.getString(1)));
                    doc.add(new Paragraph(rs.getString(2)));
                    doc.add(new Paragraph(rs.getString(3)));
                    doc.add(new Paragraph(rs.getString(4)));
                }
                
                doc.add(pdftable);
                doc.add(new Paragraph("\n"));
                doc.close();

                JOptionPane.showMessageDialog(null, "pdf genarated successifully");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                e.printStackTrace();
            }
        }
    }
    public void currentDate()
    {
        
        Thread clock=new Thread(){
         public void run(){
             for(;;){
                 
         Calendar cal =new GregorianCalendar();
        
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        
        date.setText(day+"/"+(month+1)+"/"+year);
        
        int second = cal.get(Calendar.SECOND);
        int minute = cal.get(Calendar.MINUTE);
        int hour = cal.get(Calendar.HOUR);
        
        time.setText(hour+":"+minute+":"+second);
        
                 try {
                     sleep(1000);
                 } catch (InterruptedException ex) {
                    // Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                 }
             }
         }
        
        };
        clock.start();
    }
    private void generateReport() {
        String regno = JOptionPane.showInputDialog("Enter student regno");
        String regno1 = regno.replace("/", "_");
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File( regno1 + "_report.pdf"));

        int chooserresult = chooser.showSaveDialog(null);
        if (chooserresult == JFileChooser.APPROVE_OPTION) {
            String filepath = chooser.getSelectedFile().getPath();

            try {
                conn = connection.dbConnect();

                String sql1 = "select Drug_Name,Tablets,Frequency,Days,DoctorId from tbl_prescriptions where Student='" + regno + "'  ";
                String sql2 = "select stu_id, surname,Other_names from tbl_student where stu_id='" + regno + "'";
                String sql3 = "select * from tbl_diagnosis where stuid='" + regno + "'";
                String sql4 = "select * from tbl_tests where stu_id='" + regno + "'";
                String sql5 = "select * from tbl_immunization where stuid='" + regno + "'";
                
                String query6="select * from tbl_familyillness where stu_id='"+regno+"' ";
                String query7="select hosAdmissionStatus, reason from tbl_hosadmission where stu_id='"+regno+"' ";
                String query8="select illness, status from tbl_illness where stu_id='"+regno+"' ";
                
                pst = conn.prepareStatement(sql1);
                rs = pst.executeQuery();
                pst = conn.prepareStatement(sql1);

                PreparedStatement pst2 = conn.prepareStatement(sql2);
                ResultSet rs2;
                rs2 = pst2.executeQuery();

                PreparedStatement pst3 = conn.prepareStatement(sql3);
                ResultSet rs3;
                rs3 = pst3.executeQuery();

                PreparedStatement pst4 = conn.prepareStatement(sql4);
                ResultSet rs4;
                rs4 = pst4.executeQuery();
                
                PreparedStatement pst5 = conn.prepareStatement(sql5);
                ResultSet rs5;
                rs5 = pst5.executeQuery();

                PreparedStatement pst6 = conn.prepareStatement(query6);
                ResultSet rs6;
                rs6 = pst6.executeQuery();
                
                PreparedStatement pst7 = conn.prepareStatement(query7);
                ResultSet rs7;
                rs7 = pst7.executeQuery();
                
                PreparedStatement pst8 = conn.prepareStatement(query8);
                ResultSet rs8;
                rs8 = pst8.executeQuery();
                
                

                // rst=pst.executeQuery();
                Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(filepath));

                PdfPTable pdftable = new PdfPTable(4);
                PdfPTable pdftable1 = new PdfPTable(3);
                PdfPTable pdftable2 = new PdfPTable(2);
                PdfPTable pdftable3 = new PdfPTable(3);
                
                PdfPTable pdftable6 = new PdfPTable(2);
                PdfPTable pdftable7 = new PdfPTable(3);
                PdfPTable pdftable8 = new PdfPTable(2);
                doc.open();

                float[] columnwidths = {6, 6, 6, 6};
                pdftable.setWidths(columnwidths);

                float[] columnwidths2 = {6, 6, 6};
                pdftable1.setWidths(columnwidths2);

                float[] columnwidths3 = {6, 6};
                pdftable2.setWidths(columnwidths3);

                float[] columnwidths4 = {6, 6, 6};
                pdftable3.setWidths(columnwidths4);
                
                float[] columnwidths6 = {6, 6};
                pdftable6.setWidths(columnwidths6);
                
                float[] columnwidths7 = {6, 6, 6};
                pdftable7.setWidths(columnwidths7);
                
                float[] columnwidths8 = {6, 6};
                pdftable8.setWidths(columnwidths8);

                pdftable.setWidthPercentage(100);
                pdftable1.setWidthPercentage(100);
                pdftable2.setWidthPercentage(100);
                pdftable3.setWidthPercentage(100);
                
                pdftable6.setWidthPercentage(100);
                pdftable7.setWidthPercentage(100);
                pdftable8.setWidthPercentage(100);
                
                

                doc.add(new Paragraph("Medical Report for:", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                while (rs2.next()) {
                    doc.add(new Paragraph(rs2.getString("stu_id").toUpperCase() + ":- " + rs2.getString("surname").toUpperCase() + " " + rs2.getString("Other_names").toUpperCase(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 8, Font.PLAIN)));
                }
                doc.add(new Paragraph(new Date().toString()));
                doc.add(new Paragraph("Prescription Record"));
                doc.add(new Paragraph("\n"));
                pdftable.addCell(new PdfPCell(new Paragraph("Drug name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                pdftable.addCell(new PdfPCell(new Paragraph("tablets", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                pdftable.addCell(new PdfPCell(new Paragraph("frequencies", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                pdftable.addCell(new PdfPCell(new Paragraph("No of Days", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));

                if (rs.next()) {

                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(1), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(2), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(3), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(4), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));

                } else {
                    doc.add(new Paragraph("no records found!", FontFactory.getFont(FontFactory.COURIER, 9, BaseColor.RED)));
                }
                doc.add(pdftable);

                doc.add(new Paragraph("Diagnosis Record"));
                doc.add(new Paragraph("\n"));
                pdftable1.addCell("Signs and symptoms");
                pdftable1.addCell("disease");
                pdftable1.addCell("Remarks");
                while (rs3.next()) {
                    pdftable1.addCell(new PdfPCell(new Paragraph(rs3.getString("signs_symptoms"))));
                    pdftable1.addCell(new PdfPCell(new Paragraph(rs3.getString("disease"))));
                    pdftable1.addCell(new PdfPCell(new Paragraph(rs3.getString("remarks"))));
                }
                doc.add(pdftable1);

                doc.add(new Paragraph("Laboratory tests and results"));
                doc.add(new Paragraph("\n"));
                pdftable2.addCell(new PdfPCell(new Paragraph("test_case")));
                pdftable2.addCell(new PdfPCell(new Paragraph("test_result")));
                while (rs4.next()) {
                    pdftable2.addCell(rs4.getString("test_case"));
                    pdftable2.addCell(rs4.getString("test_result"));
                }
                doc.add(pdftable2);
                doc.add(new Paragraph("Immunization Record"));
                doc.add(new Paragraph("\n"));
                pdftable3.addCell(new PdfPCell(new Paragraph("Condition")));
                pdftable3.addCell(new PdfPCell(new Paragraph("Status")));
                pdftable3.addCell(new PdfPCell(new Paragraph("Date")));
                while (rs5.next()) {
                    pdftable3.addCell(rs5.getString("condition"));
                    pdftable3.addCell(rs5.getString("status"));
                    pdftable3.addCell(rs5.getString("date"));
                }
                doc.add(pdftable3);
                
                 doc.add(new Paragraph("Earlier Illness status"));
                doc.add(new Paragraph("\n"));
                pdftable8.addCell(new PdfPCell(new Paragraph("Illness")));
                pdftable8.addCell(new PdfPCell(new Paragraph("Status")));
               // pdftable8.addCell(new PdfPCell(new Paragraph("Date")));
                while (rs8.next()) {
                    pdftable8.addCell(rs8.getString("illness"));
                    pdftable8.addCell(rs8.getString("status"));
                    //pdftable8.addCell(rs8.getString("date"));
                }
                doc.add(pdftable8);
                
                 doc.add(new Paragraph("Previous Hospital admission status"));
                doc.add(new Paragraph("\n"));
                pdftable7.addCell(new PdfPCell(new Paragraph("Ever been admitted ?")));
                pdftable7.addCell(new PdfPCell(new Paragraph("Reason")));
                
                while (rs7.next()) {
                    pdftable7.addCell(rs7.getString("hosAdmissionStatus"));
                    pdftable7.addCell(rs7.getString("reason"));
                    
                }
                doc.add(pdftable7);
                
                 doc.add(new Paragraph("Family Related illnesses"));
                doc.add(new Paragraph("\n"));
                pdftable6.addCell(new PdfPCell(new Paragraph("Any member of the family suffered from ?")));
                pdftable6.addCell(new PdfPCell(new Paragraph("Status")));
                
                while (rs6.next()) {
                    pdftable6.addCell(rs6.getString("illness"));
                    pdftable6.addCell(rs6.getString("status"));
                   
                }
                doc.add(pdftable6);

                doc.close();

                JOptionPane.showMessageDialog(null, "pdf genarated successifully");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
                e.printStackTrace();
            }
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

        panelmain = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        help = new javax.swing.JButton();
        settings = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        exit = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelmain.setBackground(new java.awt.Color(204, 204, 255));
        panelmain.setPreferredSize(new java.awt.Dimension(1050, 598));

        jPanel1.setBackground(new java.awt.Color(51, 51, 255));

        help.setText("HELP");

        settings.setText("SETTINGS");

        logout.setText("LOG OUT");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        exit.setText("EXIT");
        exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitActionPerformed(evt);
            }
        });

        jButton1.setText("VISUALIZER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("patients report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addComponent(settings)
                .addGap(36, 36, 36)
                .addComponent(help)
                .addGap(32, 32, 32)
                .addComponent(jButton1)
                .addGap(42, 42, 42)
                .addComponent(jButton2)
                .addGap(18, 18, 18)
                .addComponent(exit)
                .addGap(163, 163, 163))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(settings)
                            .addComponent(help)
                            .addComponent(exit)
                            .addComponent(jButton1)
                            .addComponent(jButton2))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 204));

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 255));
        jLabel1.setText("MMUST HEALTH INFORMATION SYSTEM");

        date.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        date.setForeground(new java.awt.Color(102, 0, 102));
        date.setText("jLabel2");

        time.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        time.setForeground(new java.awt.Color(102, 0, 102));
        time.setText("jLabel3");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(184, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(time, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(date, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelmain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelmain, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new Login());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_logoutActionPerformed

    private void exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitActionPerformed
        // TODO add your handling code here:
        int exit=JOptionPane.showConfirmDialog(null,"Are you sure that you want to cancel ?","ConfirmDialog",JOptionPane.YES_NO_OPTION);
                if(exit==JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                
                    
                }
    }//GEN-LAST:event_exitActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
         Registration.panelmain.removeAll();
        Registration.panelmain.add(new visualizer());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        generateReport();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registration.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registration().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel date;
    private javax.swing.JButton exit;
    private javax.swing.JButton help;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JButton logout;
    public static javax.swing.JPanel panelmain;
    private javax.swing.JButton settings;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
