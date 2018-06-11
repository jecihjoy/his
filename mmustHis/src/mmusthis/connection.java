package mmusthis;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author geoffrey
 */
public class connection {
    Connection conn;
    
    public static Connection dbConnect(){
         String url="jdbc:mysql://localhost/health";
        try{
           
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url,"root","");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
}
