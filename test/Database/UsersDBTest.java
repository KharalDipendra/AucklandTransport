package Database;

import User.User;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * @author Dipendra
 */


public class UsersDBTest {

    private static UsersDB usersDB;
    private static DBManager dbManager;

    @BeforeClass
    public static void setUpClass() throws Exception {
        dbManager = new DBManager();
        usersDB = new UsersDB(dbManager);
        
        // Ensure the test user doesn't exist before adding it
        if (usersDB.getUserByEmail("test@example.com") != null) {
            usersDB.deleteUser("test@example.com");
        }
        
        User testUser = new User("testuser", "test@example.com", "password123");
        usersDB.addUser(testUser);
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
        if (usersDB != null) {
            usersDB.deleteUser("test@example.com");
        }
        if (dbManager != null) {
            dbManager.closeConnections();
        }
    }

    @Test
    public void testAuthenticate_Success() {
        System.out.println("Testing successful authentication");
        User result = usersDB.authenticate("testuser", "password123");
        assertNotNull("User should be authenticated successfully", result);
        assertEquals("Authenticated user's name should be 'testuser'", "testuser", result.getName());
    }

    @Test
    public void testAuthenticate_Failure_WrongPassword() {
        System.out.println("Testing failed authentication with wrong password");
        User result = usersDB.authenticate("testuser", "wrongpassword");
        assertNull("User should not be authenticated with wrong password", result);
    }

    @Test
    public void testAuthenticate_Failure_UserNotFound() {
        System.out.println("Testing failed authentication for non-existent user");
        User result = usersDB.authenticate("nonexistentuser", "password");
        assertNull("Non-existent user should not be authenticated", result);
    }
} 