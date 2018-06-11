
package mmusthis;

/**
 *
 * @author geoffrey
 */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import java.sql.*;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import mmusthis.connection;
import net.proteanit.sql.DbUtils;

public class doctor extends javax.swing.JPanel {

    /**
     * Creates new form doctor
     */
    public String reg;
    public static String newReg;
    ResultSet rs = null;
    Statement smt = null;
    Connection conn = null;
    PreparedStatement pst = null;

    public doctor() {
        initComponents();

        connection.dbConnect();
        updateTable();
        patientsInWait();
        showDrugPrescriptions();

    }
    
    public void setREG(String myreg){
        newReg = myreg;
    }
    public String getREG(){
        return newReg;
    }

    private void updateTable() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");
            String sql1 = "select test_case,test_result from tbl_tests where DATE(date)=DATE(now()) and stu_id=?";
            pst = conn.prepareStatement(sql1);
            pst.setString(1, hiddenreg.getText());

            rs = pst.executeQuery();

            teststable.setModel(DbUtils.resultSetToTableModel(rs));
            if (rs.next()) {

                teststable.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }

    private void patientsInWait() {
        try {
            conn = connection.dbConnect();
            String sql1 = "select StudentId, time(Date) as time_in from tbl_waitingpatients where status=0 order by time_in asc";
            pst = conn.prepareStatement(sql1);

            rs = pst.executeQuery();

            patientsinwait.setModel(DbUtils.resultSetToTableModel(rs));
            if (rs.next()) {

                patientsinwait.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }

    private void updatepatientsInWait() {
        try {
            conn = connection.dbConnect();
            String sql1 = "update tbl_waitingpatients set status='1' where StudentId='" + hiddenreg.getText() + "'";
            pst = conn.prepareStatement(sql1);

            pst.execute();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }
    private void searchPatientInWait(){
         String sql1 = "select StudentId, time(Date) as time_in from tbl_waitingpatients where status=0 and StudentId like '%"+studid.getText()+"%' order by time_in asc";
        try{
            conn=connection.dbConnect();
            pst=conn.prepareStatement(sql1);
            
            rs = pst.executeQuery();

            patientsinwait.setModel(DbUtils.resultSetToTableModel(rs));
            if (rs.next()) {

                patientsinwait.setModel(DbUtils.resultSetToTableModel(rs));
            }
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
        
    }

    private void insertDiagnosis() {
        String query = "insert into tbl_diagnosis(stuid,docID,signs_symptoms,disease,remarks) values(?,?,?,?,?)";
        try {
            conn = connection.dbConnect();
            pst = conn.prepareStatement(query);

            pst.setString(1, hiddenreg.getText());
            pst.setString(2, doctorid.getText());
            pst.setString(3, signandsysmptoms.getText());
            pst.setString(4, txtdisease.getText());
            pst.setString(5, remarks.getText());

            pst.execute();
            JOptionPane.showMessageDialog(null, "done");

            signandsysmptoms.setText("");
            txtdisease.setText("");
            remarks.setText("");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }

    private void showDrugPrescriptions() {
        try {
            conn = connection.dbConnect();
            String sql1 = "select Drug_Name,Tablets,Frequency,Days from tbl_prescriptions where Student='" + hiddenreg.getText() + "' and statuss=0 and DATE(Date)=DATE(now()) ";
            pst = conn.prepareStatement(sql1);

            rs = pst.executeQuery();

            prescribeddrugs.setModel(DbUtils.resultSetToTableModel(rs));
            if (rs.next()) {

                prescribeddrugs.setModel(DbUtils.resultSetToTableModel(rs));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {

        }
    }
    private void getLabTests(){
        String query="select test_case,test_result from tbl_tests where statuss='0' and stu_id='"+hiddenreg.getText()+"' and DATE(date)=date(now())";
         try{
            conn=connection.dbConnect();
            pst=conn.prepareStatement(query);
            
            rs = pst.executeQuery();

            teststable.setModel(DbUtils.resultSetToTableModel(rs));
            if (rs.next()) {

                teststable.setModel(DbUtils.resultSetToTableModel(rs));
            }
            
        }catch(Exception e){
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e);
        }
    }
    private void getNames(){
        String query="select surname,Other_names from tbl_student where stu_id=?";
        try{
        conn=connection.dbConnect();
        pst=conn.prepareStatement(query);
        pst.setString(1, reg);
        rs=pst.executeQuery();
        
        while(rs.next()){
            
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        doctormainpanel = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        testcase = new javax.swing.JTextArea();
        jButton6 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        signandsysmptoms = new javax.swing.JTextArea();
        studid = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        teststable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        patientsinwait = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        hiddenreg = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        prescribeddrugs = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        drugname = new javax.swing.JTextField();
        tablets = new javax.swing.JTextField();
        frequency = new javax.swing.JTextField();
        jButton19 = new javax.swing.JButton();
        days = new javax.swing.JTextField();
        jScrollPane8 = new javax.swing.JScrollPane();
        remarks = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtdisease = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        doctorid = new javax.swing.JLabel();
        fullname = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        previous = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 255));
        setPreferredSize(new java.awt.Dimension(1050, 598));

        doctormainpanel.setBackground(new java.awt.Color(153, 204, 255));
        doctormainpanel.setPreferredSize(new java.awt.Dimension(1040, 518));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("lab tests"));

        jScrollPane4.setBorder(javax.swing.BorderFactory.createTitledBorder("test condition"));

        testcase.setColumns(20);
        testcase.setRows(5);
        jScrollPane4.setViewportView(testcase);

        jButton6.setText("add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton6)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        signandsysmptoms.setColumns(20);
        signandsysmptoms.setRows(5);
        signandsysmptoms.setWrapStyleWord(true);
        signandsysmptoms.setBorder(javax.swing.BorderFactory.createTitledBorder("signs and symptoms"));
        jScrollPane2.setViewportView(signandsysmptoms);

        studid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studidActionPerformed(evt);
            }
        });
        studid.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                studidKeyReleased(evt);
            }
        });

        teststable.setModel(new javax.swing.table.DefaultTableModel(
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
        teststable.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                teststableMouseMoved(evt);
            }
        });
        jScrollPane5.setViewportView(teststable);

        patientsinwait.setModel(new javax.swing.table.DefaultTableModel(
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
        patientsinwait.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                patientsinwaitMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(patientsinwait);

        jLabel4.setText("search by regno");

        jButton1.setText("Enter student regno");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        hiddenreg.setEditable(false);
        hiddenreg.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        hiddenreg.setEnabled(false);
        hiddenreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hiddenregActionPerformed(evt);
            }
        });

        prescribeddrugs.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(prescribeddrugs);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("prescription"));

        jLabel3.setText("drug name");

        drugname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                drugnameActionPerformed(evt);
            }
        });

        tablets.setBorder(javax.swing.BorderFactory.createTitledBorder("tablets"));

        frequency.setBorder(javax.swing.BorderFactory.createTitledBorder("frequency/day"));

        jButton19.setText("add");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        days.setBorder(javax.swing.BorderFactory.createTitledBorder("no of days"));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(drugname, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(tablets, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(frequency, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(drugname, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(tablets))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(days, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(frequency, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton19))
        );

        remarks.setColumns(20);
        remarks.setRows(5);
        remarks.setBorder(javax.swing.BorderFactory.createTitledBorder("general remarks"));
        jScrollPane8.setViewportView(remarks);

        jLabel2.setText("DISEASE");

        jButton2.setText("SAVE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        doctorid.setText("doc001");

        fullname.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jButton3.setText("clear");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout doctormainpanelLayout = new javax.swing.GroupLayout(doctormainpanel);
        doctormainpanel.setLayout(doctormainpanelLayout);
        doctormainpanelLayout.setHorizontalGroup(
            doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctormainpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(doctormainpanelLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(studid, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13)
                        .addComponent(jButton1)
                        .addGap(35, 35, 35)
                        .addComponent(hiddenreg))
                    .addGroup(doctormainpanelLayout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtdisease, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, doctormainpanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane8))
                .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(doctormainpanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(doctormainpanelLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(fullname, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(87, 87, 87)
                        .addComponent(doctorid, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(doctormainpanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane5))))
                .addGap(26, 26, 26))
        );
        doctormainpanelLayout.setVerticalGroup(
            doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(doctormainpanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fullname, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(studid)
                        .addComponent(jButton1)
                        .addComponent(hiddenreg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(doctorid)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(doctormainpanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtdisease, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton2)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(doctormainpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        previous.setText("PreviousVisits");
        previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousActionPerformed(evt);
            }
        });

        jButton9.setText("BioData1");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("biodata2");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("biodata3");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton4.setText("HOME");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(doctormainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 1050, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(previous, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton10)
                .addGap(18, 18, 18)
                .addComponent(jButton11)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(doctormainpanel, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton10)
                            .addComponent(jButton11)
                            .addComponent(jButton4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(previous)
                        .addComponent(jButton9))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void drugnameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_drugnameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_drugnameActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if ("".equals(hiddenreg.getText())) {
            JOptionPane.showMessageDialog(null, "No patient selected", "Error", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(testcase.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid test case!", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                conn = connection.dbConnect();
                String insertQuery = "insert into tbl_tests(stu_id,test_case) values(?,?)";
                pst = conn.prepareStatement(insertQuery);

                pst.setString(1, hiddenreg.getText());
                pst.setString(2, testcase.getText());

                pst.execute();
                JOptionPane.showMessageDialog(null, "done");
                updateTable();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        //updateTable();
       // getLabTests();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void teststableMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_teststableMouseMoved
        // TODO add your handling code here:
        updateTable();
    }//GEN-LAST:event_teststableMouseMoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String regNoInList = JOptionPane.showInputDialog("Enter regno of the student");
        //studid.setText(regNoInList);
        String query = "select * from tbl_student where stu_id='" + regNoInList + "'";
        try {
            conn = connection.dbConnect();
            pst = conn.prepareStatement(query);
            rs = pst.executeQuery();
            if (rs.next()) {
                fullname.setText(rs.getString("surname"));
                hiddenreg.setText(rs.getString("stu_id"));
                updateTable();
                storeReg.rg=regNoInList;
            } else {
                JOptionPane.showMessageDialog(null, "Ops!\nNo student found!!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        if ("".equals(hiddenreg.getText())) {
            JOptionPane.showMessageDialog(null, "Ops! no patient selected!");
        } else if (drugname.getText() == "") {
            JOptionPane.showMessageDialog(null, "Error!\nNo drug selected!");
        } else {
            int amount = Integer.parseInt(tablets.getText()) * Integer.parseInt(tablets.getText()) * Integer.parseInt(tablets.getText());
            String query = "insert into tbl_prescriptions (Student,DoctorId, Drug_Name,Tablets,Frequency,Days) values (?,?,?,?,?,?)";
            try {
                conn = connection.dbConnect();
                pst = conn.prepareStatement(query);

                pst.setString(1, hiddenreg.getText());
                pst.setString(2, doctorid.getText());
                pst.setString(3, drugname.getText());
                pst.setString(4, tablets.getText());
                pst.setString(5, frequency.getText());
                pst.setString(6, days.getText());
                pst.execute();

                JOptionPane.showMessageDialog(null, "Done!");
                showDrugPrescriptions();
                updatepatientsInWait();
                
                drugname.setText("");
                tablets.setText("");
                frequency.setText("");
                days.setText("");

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
        
    }//GEN-LAST:event_jButton19ActionPerformed

    private void patientsinwaitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_patientsinwaitMouseClicked
        // TODO add your handling code here:
        int demo = patientsinwait.getSelectedRow();
        String rreg = patientsinwait.getValueAt(demo, 0).toString();
        //String id = patientsinwait.getValueAt(demo, 0).toString();
       // String stuid = patientsinwait.getValueAt(demo, 1).toString();
        hiddenreg.setText(rreg);
        
         String query="select surname,Other_names from tbl_student where stu_id=?";
        try{
        conn=connection.dbConnect();
        pst=conn.prepareStatement(query);
        pst.setString(1, rreg);
        rs=pst.executeQuery();
        
        while(rs.next()){
            fullname.setText(rs.getString(1)+" "+rs.getString(2));
        }
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_patientsinwaitMouseClicked

    private void previousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousActionPerformed
        // TODO add your handling code here:
        //doctormainpanel
        doctormainpanel.removeAll();
        doctormainpanel.setLayout(new BorderLayout());
        previousRecords pr = new previousRecords();
        doctormainpanel.add(pr, BorderLayout.CENTER);
        doctormainpanel.revalidate();
        doctormainpanel.repaint();
        
        setName(hiddenreg.getText());
       // System.out.println(pr.identifier);

    }//GEN-LAST:event_previousActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if ("".equals(hiddenreg.getText())) {
            JOptionPane.showMessageDialog(null, "No patient selected", "error", JOptionPane.ERROR_MESSAGE);
        } else if ("".equals(signandsysmptoms.getText())) {
            JOptionPane.showMessageDialog(null, "no signs or symptoms provided", "error", JOptionPane.ERROR_MESSAGE);
        } else {
            insertDiagnosis();
            updatepatientsInWait();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        doctormainpanel.removeAll();
        doctormainpanel.setLayout(new BorderLayout());
        biodata pr = new biodata();
        doctormainpanel.add(pr, BorderLayout.CENTER);
        doctormainpanel.revalidate();
        doctormainpanel.repaint();
        setREG(hiddenreg.getText());
        
        biodata.myreg.setText(new doctor().getREG());
       
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        doctormainpanel.removeAll();
        doctormainpanel.setLayout(new BorderLayout());
        biodata2 pr = new biodata2();
        doctormainpanel.add(pr, BorderLayout.CENTER);
        doctormainpanel.revalidate();
        doctormainpanel.repaint();
        setREG(hiddenreg.getText());
    }//GEN-LAST:event_jButton10ActionPerformed

    private void studidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studidActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_studidActionPerformed

    private void studidKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_studidKeyReleased
        // TODO add your handling code here:
         searchPatientInWait();
    }//GEN-LAST:event_studidKeyReleased

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        doctormainpanel.removeAll();
        doctormainpanel.setLayout(new BorderLayout());
        biodata3 pr = new biodata3();
        doctormainpanel.add(pr, BorderLayout.CENTER);
        doctormainpanel.revalidate();
        doctormainpanel.repaint();
        setREG(hiddenreg.getText());
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        doctormainpanel.removeAll();
        doctormainpanel.setLayout(new BorderLayout());
        doctormainpanel.add(new doctor(), BorderLayout.CENTER);
        doctormainpanel.revalidate();
        doctormainpanel.repaint();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void hiddenregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hiddenregActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hiddenregActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        hiddenreg.setText("");
        remarks.setText("");
        signandsysmptoms.setText("");
        txtdisease.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField days;
    private javax.swing.JLabel doctorid;
    private javax.swing.JPanel doctormainpanel;
    private javax.swing.JTextField drugname;
    private javax.swing.JTextField frequency;
    private javax.swing.JLabel fullname;
    private javax.swing.JTextField hiddenreg;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JTable patientsinwait;
    private javax.swing.JTable prescribeddrugs;
    private javax.swing.JButton previous;
    private javax.swing.JTextArea remarks;
    private javax.swing.JTextArea signandsysmptoms;
    private javax.swing.JTextField studid;
    private javax.swing.JTextField tablets;
    private javax.swing.JTextArea testcase;
    private javax.swing.JTable teststable;
    private javax.swing.JTextField txtdisease;
    // End of variables declaration//GEN-END:variables
}
