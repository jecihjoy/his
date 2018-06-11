package mmusthis;

import java.awt.BorderLayout;
import javax.swing.JDialog;

/**
 *
 * @author Jecihjoy
 */
public class MailDialog  extends JDialog{
    
    public  MailDialog (){
        setSize(413,460);
        setTitle("Send Email");
        setLocationRelativeTo(new Registration());
        setLayout(new BorderLayout());
        add(new SendMail(), BorderLayout.CENTER);
        setVisible(true);
    }
    
}
