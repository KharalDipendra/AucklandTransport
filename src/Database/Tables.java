package Database;

import GUI.ConfirmDB;

/**
 * Utility class for creating and initializing application tables.
 * Uses DBManager.executeUpdate(...) which wraps SQLExceptions in RuntimeExceptions.
 */
public final class Tables {
    private Tables() { /* static only */ }
    
    private static final String CREATE_USERS_TABLE =
        "CREATE TABLE USERS (" +
        "name VARCHAR(100) PRIMARY KEY, " +
        "email VARCHAR(150) UNIQUE NOT NULL, " +
        "password VARCHAR(100) NOT NULL, " +
        "memberType VARCHAR(10) DEFAULT 'Member', " +
        "cardNumber VARCHAR(20), " +
        "topUp DECIMAL(10,2), " +
        "memberSince DATE NOT NULL DEFAULT CURRENT_DATE, " +
        "discountType VARCHAR(20) DEFAULT 'Standard'" +
        ")";
    
    private static final String CREATE_BOOKINGS_TABLE =
        "CREATE TABLE BOOKINGS (" +
          "bookingId     INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
          "username      VARCHAR(100) NOT NULL,               " + 
          "email         VARCHAR(150) NOT NULL,               " +
          "dateBooked    DATE NOT NULL,                       " +
          "departureDate DATE NOT NULL,                       " +
          "destination   VARCHAR(100) NOT NULL,               " + 
          "price DECIMAL(10,2), " +
          "serviceType   VARCHAR(50)  NOT NULL,               " + 
          "FOREIGN KEY (email) REFERENCES USERS(email)        " +
        ")";
    
    // drops a table if it exists
    private static void dropIfExists(DBManager manager, String tableName) {
        try {
            manager.executeUpdate("DROP TABLE " + tableName);
            System.out.println("Dropped existing table: " + tableName);
        } catch (RuntimeException e) {
            // ignore if table doesn't exist
        }
    }
    
    // makes the database tables
    public static void makeTable(DBManager manager) {
        dropIfExists(manager, "BOOKINGS");
        dropIfExists(manager, "USERS");
        
        // Create tables in dependency order
        manager.executeUpdate(CREATE_USERS_TABLE);
        manager.executeUpdate(CREATE_BOOKINGS_TABLE);
        
        System.out.println("All tables initialized");
    }
    
    // shows a confirmation window before making tables
    public static void createWindow() {
      new ConfirmDB().setVisible(true);
    }
}