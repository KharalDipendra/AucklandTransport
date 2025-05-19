/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

/**
 *
 * @author Pammi
 */
public class Tables {
    private static final String CREATE_USERS_TABLE = """
        CREATE TABLE USERS (
            id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
            username VARCHAR(50) NOT NULL UNIQUE,
            email VARCHAR(100) NOT NULL UNIQUE,
            password VARCHAR(255) NOT NULL,
            cardNumber VARCHAR(20) NOT NULL UNIQUE,
            topUp DECIMAL(10,2) DEFAULT 0.00
        )
    """;

       
    private static final String CREATE_BOOKING_TYPE = """
        CREATE TABLE booking_type (
            id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
            type VARCHAR(10) NOT NULL UNIQUE CHECK (type IN ('bus', 'train'))
        )
    """;

    private static final String CREATE_BOOKINGS_TABLE = """
        CREATE TABLE BOOKINGS (
            id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
            userid INT NOT NULL,
            booking_type_id INT NOT NULL,
            dateBooked DATE NOT NULL,
            departure VARCHAR(100) NOT NULL,
    
            FOREIGN KEY (userid) REFERENCES USERS(id) ON DELETE CASCADE,
            FOREIGN KEY (booking_type_id) REFERENCES booking_type(id)
        )
    """;
 
        
    
/*
    public static void dropTables(DBManager manager) {
        manager.updateDB("DROP TABLE BOOKINGS"); 
        manager.updateDB("DROP TABLE USERS");

    } */
        
    
    public static void createUsersTable(DBManager manager) {
        try {
            manager.updateDB("DROP TABLE USERS");
        } catch (Exception e) {
           //Table doesn't exist
        }
        manager.updateDB(CREATE_USERS_TABLE);
    }
    
    public static void createBookingsTable(DBManager manager) {
        try {
            manager.updateDB("DROP TABLE BOOKINGS");
        } catch (Exception e) {
            // Table doesn't exist
        }
       manager.updateDB(CREATE_BOOKINGS_TABLE);
    } 

    public static void createBookingTypeTable(DBManager manager) {
        try {
            manager.updateDB("DROP TABLE BOOKINGS");
        } catch (Exception e) {
            // Table doesn't exist
        }
       manager.updateDB(CREATE_BOOKING_TYPE);
    } 
   
    }

