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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author geoffrey
 */
public class inventory extends javax.swing.JPanel {
    public static String drug_name, drug_amount, drug_desc, drug_category, drug_expire;
    public int total;
    Connection conn = null;
    ResultSet rs = null;
    Statement st;
    PreparedStatement pst = null;

    public inventory() {
        initComponents();
        selectDrugs();
       expirydate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

    }

    private void selectDrugs() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            String sql1 = "SELECT  drugs_name, drug_desc, drug_amount, category, expirydate FROM `tbl_drugs`";
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
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            String sql1 = "SELECT  drugs_name, drug_desc, drug_amount, category, expirydate FROM `tbl_drugs` where drugs_name like '%"+search.getText()+"%'";
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
        String name = dname.getText();
        String desc = ddescription.getText();
        String total = damount.getText().toString();
        String cat = category.getSelectedItem().toString();
        String expiry = expirydate.getSelectedItem().toString();
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            Statement st = conn.createStatement();
            String sql = "INSERT INTO `health`.`tbl_drugs` (`drugs_name`, `drug_desc`, `drug_amount`, `category`, `expirydate`) VALUES('" + name + "','" + desc + "','" + total + "','" + cat + "','" + expiry + "')";

            st.execute(sql);

            JOptionPane.showMessageDialog(null, "Drug Added", "Drug Box", JOptionPane.OK_OPTION);
            dname.setText(null);
            ddescription.setText(null);
            damount.setText(null);
            //category.setText(null);
           // expirydate.setSelectedIndex(1);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }
    
    private int getOldTotal(String druname){
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            st = conn.createStatement();
            String sql1 = "SELECT  drug_amount FROM `tbl_drugs` WHERE drugs_name = '"+druname+"' ";
            pst = conn.prepareStatement(sql1);
            rs = pst.executeQuery();
            while(rs.next()){
                total=rs.getInt("drug_amount");
                return total;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
            //return 0;
        } 
        return total;
    }

    private void updateDrugs() {
        String name = dname.getText();
        String desc = ddescription.getText();
        int total = Integer.parseInt(damount.getText().toString());
        String cat = category.getSelectedItem().toString();
        String expiry = expirydate.getSelectedItem().toString();
        
        int oldTotal = getOldTotal(drug_name);
        int neweTotal = oldTotal + total;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            Statement st = conn.createStatement();
            String sql = "UPDATE `health`.`tbl_drugs` SET `drugs_name` = '" + name + "', `drug_desc` = '" + desc + "', `drug_amount` = '" + neweTotal + "', `category` = '" + cat + "', `expirydate` = '" + expiry + "' WHERE `tbl_drugs`.`drugs_name` = '" + drug_name + "'";

            st.execute(sql);

            JOptionPane.showMessageDialog(null, "update successful", "Drug Box", JOptionPane.OK_OPTION);

            dname.setText(null);
            ddescription.setText(null);
            damount.setText(null);
            //category.setText(null);
            //expirydate.setText(null);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }

    private void deleteDrugs() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            Statement st = conn.createStatement();
            String sql = "DELETE FROM `health`.`tbl_drugs` WHERE `tbl_drugs`.`drugs_name` = '" + drug_name + "'";

            st.execute(sql);

            JOptionPane.showMessageDialog(null, "Drug Deleted", "Drug Box", JOptionPane.OK_OPTION);
            dname.setText(null);
            ddescription.setText(null);
            damount.setText(null);
            //category.setText(null);
           // expirydate.setText(null);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDatePickerUtil1 = new net.sourceforge.jdatepicker.util.JDatePickerUtil();
        jDatePickerUtil2 = new net.sourceforge.jdatepicker.util.JDatePickerUtil();
        utilDateModel1 = new net.sourceforge.jdatepicker.impl.UtilDateModel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        dname = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        newdrug = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        category = new javax.swing.JComboBox<>();
        delete = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        ddescription = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        DrugsTable = new javax.swing.JTable();
        search = new javax.swing.JTextField();
        damount = new javax.swing.JTextField();
        expirydate = new org.freixas.jcalendar.JCalendarCombo();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        exporttopdf = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(1050, 598));

        jLabel1.setFont(new java.awt.Font("Imprint MT Shadow", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 51, 255));
        jLabel1.setText("DRUGS INVENTORY ");

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel3.setText("DRUG NAME");

        jLabel4.setText("EXPIRY DATE");

        jLabel5.setText("Drug Description");

        jLabel6.setText("QUANTITY");

        newdrug.setText("ADD DRUG");
        newdrug.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newdrugActionPerformed(evt);
            }
        });

        update.setText("UPDATE");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jLabel8.setText("CATEGORY");

        category.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "anticeptics", "antifungal", "anti viral", "pain killers" }));

        delete.setText("DELETE");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        ddescription.setColumns(20);
        ddescription.setRows(5);
        jScrollPane2.setViewportView(ddescription);

        DrugsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "DRUG NAME", "QUANTITY", "DATE UPDATED"
            }
        ));
        DrugsTable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                DrugsTableMouseMoved(evt);
            }
        });
        DrugsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DrugsTableMouseClicked(evt);
            }
        });
        DrugsTable.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                DrugsTablePropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(DrugsTable);

        search.setBorder(javax.swing.BorderFactory.createTitledBorder("search drug"));
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchKeyReleased(evt);
            }
        });

        damount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                damountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(255, 255, 255)
                        .addComponent(newdrug)
                        .addGap(51, 51, 51)
                        .addComponent(update)
                        .addGap(48, 48, 48)
                        .addComponent(delete)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 946, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(dname, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(damount, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(57, 57, 57)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(expirydate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(update)
                            .addComponent(delete)
                            .addComponent(newdrug))
                        .addGap(62, 62, 62))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(expirydate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(damount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(category, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                        .addComponent(search, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel4.setBackground(new java.awt.Color(204, 204, 255));

        jLabel7.setText("SEARCH");

        jButton4.setText("search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("export pdf");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jButton4)
                .addGap(40, 40, 40)
                .addComponent(jButton5)
                .addContainerGap(459, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        exporttopdf.setText("EXPORT TO PDF");
        exporttopdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exporttopdfActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(102, 153, 255));
        jButton1.setText("REPORTS");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(102, 102, 255));
        jButton2.setText("ORDERS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton2)
                .addGap(128, 128, 128))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(exporttopdf, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(59, 59, 59)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exporttopdf)
                .addGap(128, 128, 128)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1070, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void newdrugActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newdrugActionPerformed
        // TODO add your handling code here:
        addDrugs();
        selectDrugs();
    }//GEN-LAST:event_newdrugActionPerformed

    private void DrugsTablePropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_DrugsTablePropertyChange

    }//GEN-LAST:event_DrugsTablePropertyChange

    private void DrugsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrugsTableMouseClicked
        int demo = DrugsTable.getSelectedRow();
          drug_name = DrugsTable.getValueAt(demo, 0).toString();
          drug_desc = DrugsTable.getValueAt(demo, 1).toString();
          drug_amount = DrugsTable.getValueAt(demo, 2).toString();
          drug_category = DrugsTable.getValueAt(demo, 3).toString();
          drug_expire = DrugsTable.getValueAt(demo, 4).toString();
          
          dname.setText(drug_name);
          damount.setText(drug_amount);
          ddescription.setText(drug_desc);
          category.setSelectedItem(drug_category);
          expirydate.setSelectedItem(drug_expire);
          
        int oldTotal = getOldTotal(drug_name);
    }//GEN-LAST:event_DrugsTableMouseClicked

    private void DrugsTableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DrugsTableMouseMoved

    }//GEN-LAST:event_DrugsTableMouseMoved

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        // TODO add your handling code here:
        updateDrugs();
        selectDrugs();
    }//GEN-LAST:event_updateActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        deleteDrugs();
        selectDrugs();
    }//GEN-LAST:event_deleteActionPerformed

    private void exporttopdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exporttopdfActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File("inventory_record.pdf"));

        int chooserresult = chooser.showSaveDialog(null);
        if (chooserresult == JFileChooser.APPROVE_OPTION) {
            String filepath = chooser.getSelectedFile().getPath();

            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
                String sql1 = "select * from tbl_drugs";
                pst = conn.prepareStatement(sql1);
                rs = pst.executeQuery();

                pst = conn.prepareStatement(sql1);
                // rst=pst.executeQuery();
                Document doc = new Document();
                PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream(filepath));

                PdfPTable pdftable = new PdfPTable(3);
                doc.open();

                float[] columnwidths = {10, 10, 10};
                pdftable.setWidths(columnwidths);

                pdftable.setWidthPercentage(100);

                doc.add(new Paragraph("Inventory Summary", FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD)));
                doc.add(new Paragraph(new Date().toString()));
                doc.add(new Paragraph("\n"));

                pdftable.addCell(new PdfPCell(new Paragraph("Drug name", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                pdftable.addCell(new PdfPCell(new Paragraph("description", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));
                pdftable.addCell(new PdfPCell(new Paragraph("Amount", FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.BOLD))));

                while (rs.next()) {
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(1), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(2), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                    pdftable.addCell(new PdfPCell(new Paragraph(rs.getString(3), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9, Font.PLAIN))));
                }
                doc.add(pdftable);
                doc.add(new Paragraph("\n"));
                doc.close();

                JOptionPane.showMessageDialog(null, "pdf genarated successifully");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_exporttopdfActionPerformed

    private void damountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_damountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_damountActionPerformed

    private void searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyReleased
        // TODO add your handling code here:
        searchDrug();
    }//GEN-LAST:event_searchKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new Drugs20());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        Registration.panelmain.removeAll();
        Registration.panelmain.add(new MyDrugsPanel());
        Registration.panelmain.revalidate();
        Registration.panelmain.repaint();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable DrugsTable;
    private javax.swing.JComboBox<String> category;
    private javax.swing.JTextField damount;
    private javax.swing.JTextArea ddescription;
    private javax.swing.JButton delete;
    private javax.swing.JTextField dname;
    private org.freixas.jcalendar.JCalendarCombo expirydate;
    private javax.swing.JButton exporttopdf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private net.sourceforge.jdatepicker.util.JDatePickerUtil jDatePickerUtil1;
    private net.sourceforge.jdatepicker.util.JDatePickerUtil jDatePickerUtil2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JButton newdrug;
    private javax.swing.JTextField search;
    private javax.swing.JButton update;
    private net.sourceforge.jdatepicker.impl.UtilDateModel utilDateModel1;
    // End of variables declaration//GEN-END:variables
}
