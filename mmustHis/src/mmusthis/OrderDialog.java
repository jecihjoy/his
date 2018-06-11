
package mmusthis;

import java.awt.BorderLayout;
import javax.swing.JDialog;

/**
 *
 * @author Jecihjoy
 */

public class OrderDialog extends JDialog {
    
    public OrderDialog(){
        setSize(1050,598);
        setTitle("Order Drug");
        setLocationRelativeTo(new Registration());
        setLayout(new BorderLayout());
        add(new OrderDrugs(), BorderLayout.CENTER);
        setVisible(true);
    }
    
}