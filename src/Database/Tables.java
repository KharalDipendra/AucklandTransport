/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;

import java.sql.SQLException;

/**
 * Tables class contains SQL statements for creating database tables
 * and methods to manage table creation in the database.
 * This class defines the schema for users, bookings, and booking types.
 */
public class Tables {
    /** SQL statement to create the users table */
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

    /** SQL statement to create the booking type table */
    private static final String CREATE_BOOKING_TYPE = """
        CREATE TABLE booking_type (
            id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
            type VARCHAR(10) NOT NULL UNIQUE CHECK (type IN ('bus', 'train'))
        )
    """;

    /** SQL statement to create the bookings table */
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
 
    /**
     * Creates the users table in the database
     * Drops the existing table if it exists before creating a new one
     * @param manager DBManager instance for database operations
     * @throws SQLException if there is an error creating the table
     */
    public static void createUsersTable(DBManager manager) throws SQLException {
        try {
            manager.updateDB("DROP TABLE USERS");
        } catch (SQLException e) {
            // Table doesn't exist, continue with creation
        }
        manager.updateDB(CREATE_USERS_TABLE);
    }
    
    /**
     * Creates the bookings table in the database
     * Drops the existing table if it exists before creating a new one
     * @param manager DBManager instance for database operations
     * @throws SQLException if there is an error creating the table
     */
    public static void createBookingsTable(DBManager manager) throws SQLException {
        try {
            manager.updateDB("DROP TABLE BOOKINGS");
        } catch (SQLException e) {
            // Table doesn't exist, continue with creation
        }
        manager.updateDB(CREATE_BOOKINGS_TABLE);
    } 

    /**
     * Creates the booking type table in the database
     * Drops the existing table if it exists before creating a new one
     * @param manager DBManager instance for database operations
     * @throws SQLException if there is an error creating the table
     */
    public static void createBookingTypeTable(DBManager manager) throws SQLException {
        try {
            manager.updateDB("DROP TABLE booking_type");
        } catch (SQLException e) {
            // Table doesn't exist, continue with creation
        }
        manager.updateDB(CREATE_BOOKING_TYPE);
    } 
}

