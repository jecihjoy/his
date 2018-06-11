package mmusthis;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Jecihjoy
 */
public class Login extends javax.swing.JPanel {

    Connection myCon = null;
    ResultSet rs = null;
    Statement st;
    PreparedStatement pst = null;

    public Login() {
        initComponents();
    }

    public void userLogin() {
        String userdb, passdb;
        userdb = username.getText();
        passdb = password.getText();
        if (userdb.isEmpty() || passdb.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All login details are required", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                //1. create a connection
                myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/health", "root", "");

                //2. create a statement
                Statement mySt = myCon.createStatement();
                String selectInfo = "select pf, password, roleid  from tbl_staff";
                pst = myCon.prepareStatement(selectInfo);

                rs = pst.executeQuery();

                while (rs.next()) {

                    String dbuser = rs.getString("pf");
                    String dbpass = rs.getString("password");
                    int role = rs.getInt("roleid");

                    if (dbuser.equals(userdb) && dbpass.equals(passdb) && role == 1) {
                        JOptionPane.showMessageDialog(null, "Loggin Successful", "Doctor", JOptionPane.INFORMATION_MESSAGE);

                        Registration.panelmain.removeAll();
                        Registration.panelmain.add(new doctor());
                        Registration.panelmain.revalidate();
                        Registration.panelmain.repaint();

                        password.setText(null);
                        username.setText(null);
                    } else if (dbuser.equals(userdb) && dbpass.equals(passdb) && role == 2) {
                        JOptionPane.showMessageDialog(null, "Loggin Successful", "Pharmacist", JOptionPane.INFORMATION_MESSAGE);

                        Registration.panelmain.removeAll();
                        Registration.panelmain.add(new DispensePanel());
                        Registration.panelmain.revalidate();
                        Registration.panelmain.repaint();

                        password.setText(null);
                        username.setText(null);
                    } else if (dbuser.equals(userdb) && dbpass.equals(passdb) && role == 3) {
                        JOptionPane.showMessageDialog(null, "Loggin Successful", "Lab Assistant", JOptionPane.INFORMATION_MESSAGE);

                        Registration.panelmain.removeAll();
                        Registration.panelmain.add(new PanelTests());
                        Registration.panelmain.revalidate();
                        Registration.panelmain.repaint();

                        password.setText(null);
                        username.setText(null);
                    } else if (dbuser.equals(userdb) && dbpass.equals(passdb) && role == 4) {
                        JOptionPane.showMessageDialog(null, "Loggin Successful", "INQUIRIES", JOptionPane.INFORMATION_MESSAGE);

                        Registration.panelmain.removeAll();
                        Registration.panelmain.add(new receptionistHome());
                        Registration.panelmain.revalidate();
                        Registration.panelmain.repaint();

                        password.setText(null);
                        username.setText(null);
                    } else if (dbuser.equals(userdb) && dbpass.equals(passdb) && role == 5) {
                        JOptionPane.showMessageDialog(null, "Login Successful", "DRUGS", JOptionPane.INFORMATION_MESSAGE);

                        Registration.panelmain.removeAll();
                        Registration.panelmain.add(new inventory());
                        Registration.panelmain.revalidate();
                        Registration.panelmain.repaint();

                        password.setText(null);
                        username.setText(null);
                    } else if (!dbuser.equals(userdb) && (!dbpass.equals(passdb)) && role != 4) {

                        JOptionPane.showMessageDialog(null, "Loggin failed", "LOGIN", JOptionPane.INFORMATION_MESSAGE);
                    }
                    
                }
            } catch (Exception exc) {
                JOptionPane.showMessageDialog(null, "Database Error  " + exc.getMessage(),"error",JOptionPane.ERROR_MESSAGE);
                //JOptionPane.showMessageDialog(null, "INCORECT SIGN IN DETAILS","REJECTED LOGIN",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        username = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(204, 204, 255));
        setPreferredSize(new java.awt.Dimension(1075, 738));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mmusthis/IMG_20180120_013850.jpg"))); // NOI18N
        jLabel2.setName(""); // NOI18N

        jButton1.setText("LOGIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("CANCEL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("LOGIN"));

        username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Imprint MT Shadow", 2, 18)); // NOI18N
        jLabel3.setText("UserName");

        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                passwordKeyPressed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Imprint MT Shadow", 2, 18)); // NOI18N
        jLabel4.setText("Passward");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(77, 77, 77)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(username)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(username, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(360, 360, 360)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(85, 85, 85)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(265, 265, 265)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(368, 368, 368)
                        .addComponent(jLabel2)))
                .addContainerGap(318, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(84, 84, 84)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap(41, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        userLogin();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_passwordKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            userLogin();
        }
    }//GEN-LAST:event_passwordKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        password.setText("");
        username.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField username;
    // End of variables declaration//GEN-END:variables
}
