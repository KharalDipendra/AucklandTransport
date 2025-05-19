import Database.DBManager;
import GUI.*;

/* 
* @author Pammi
*/
public class Main {
    
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException {
        // TODO code application logic here
        new LaunchWindow().setVisible(true);
        DBManager manager = new DBManager();
        manager.getConnection();
     
       manager.createTable(); 
        
    }
}
   
