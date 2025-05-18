/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Database;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    private static final String CREATE_BOOKINGS_TABLE = """
        CREATE TABLE BOOKINGS (
            id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
            name VARCHAR(100) NOT NULL,
            cardNumber VARCHAR(20) NOT NULL,
            type VARCHAR(50) NOT NULL,
            dateBooked DATE NOT NULL,
            departure TIMESTAMP NOT NULL,
            FOREIGN KEY (cardNumber) REFERENCES USERS(cardNumber)
        )
    """;

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
   
    }

