import Database.BookingsDB;
import Database.DBManager;
import Database.Tables;
import Database.UsersDB;
import GUI.*;
import User.User;
import java.awt.Color;
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
        
        //Flatleaf plugin to change the defauly ugly SWING UI to something a bit more modern 
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
       
        
        //Create a new Database manager
        DBManager manager = new DBManager();
        UsersDB database = new UsersDB(manager);
        
        
        
        //INITIAL WINDOW comment it out after first use unless you want to reset the database and start again
        Tables.createWindow();
        //Ensure theres always an admin entry by default
      
        
        new LaunchWindow().setVisible(true);
       
    }

}
