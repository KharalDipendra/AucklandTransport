
import Database.DBManager;
import Database.Tables;
import GUI.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.UIManager;

/* 
* @author Pammi
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
            UIManager.put("TabbedPane.selectedBackground", Color.decode("#039BE5"));
            UIManager.put("TabbedPane.background", UIManager.getColor("Panel.background"));
            UIManager.put("TabbedPane.selectedForeground", Color.BLACK);

           
            UIManager.put("TextField.background", Color.WHITE);
            UIManager.put("TextField.foreground", Color.BLACK);
            UIManager.put("TextField.borderColor", Color.decode("#B0BEC5"));

          
            UIManager.put("Button.default.background", UIManager.getColor("Button.background"));
            UIManager.put("Button.default.hoverBackground", UIManager.getColor("Button.hoverBackground"));
            UIManager.put("Button.default.pressedBackground", UIManager.getColor("Button.pressedBackground"));
            UIManager.put("Button.default.focusedBackground", UIManager.getColor("Button.background"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
       
        
        
        DBManager manager = new DBManager();
        new LaunchWindow().setVisible(true);
    }

}
