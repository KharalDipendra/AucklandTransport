package Database;

import User.Booking;
import User.User;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * 
 * @author Dipendra
 */

public class BookingsDBTest {

    private static UsersDB usersDB;
    private static BookingsDB bookingsDB;
    private static DBManager dbManager;
    private static User testUser;

    @BeforeClass
    public static void setUpClass() throws SQLException {
        dbManager = new DBManager();
        usersDB = new UsersDB(dbManager);
        bookingsDB = new BookingsDB(dbManager);

        String userEmail = "bookingtest@example.com";
        // Ensure the user and their bookings are completely gone before starting
        if (usersDB.getUserByEmail(userEmail) != null) {
            List<Booking> oldBookings = bookingsDB.getBookingsForUser(userEmail);
            for (Booking booking : oldBookings) {
                bookingsDB.deleteBooking(booking.getBookingId());
            }
            usersDB.deleteUser(userEmail);
        }
        
        testUser = new User("bookinguser", userEmail, "password");
        usersDB.addUser(testUser);
        
        int bookingId = bookingsDB.addBooking(
                testUser.getName(),
                testUser.getEmail(),
                LocalDate.now(),
                LocalDate.now().plusDays(5),
                "Test Destination",
                25.50,
                "Bus"
        );
      
        assertTrue("Booking should have a valid ID after creation", bookingId > 0);
    }

    @AfterClass
    public static void tearDownClass() throws SQLException {
        if (dbManager == null) {
            return;
        }
       
        if (bookingsDB != null && testUser != null) {
            List<Booking> bookings = bookingsDB.getBookingsForUser(testUser.getEmail());
            for (Booking booking : bookings) {
                bookingsDB.deleteBooking(booking.getBookingId());
            }
        }
        if (usersDB != null && testUser != null) {
            usersDB.deleteUser(testUser.getEmail());
        }
        dbManager.closeConnections();
    }

    @Test
    public void testAddBooking() throws SQLException {
        List<Booking> bookings = bookingsDB.getBookingsForUser(testUser.getEmail());
        assertNotNull("Bookings list should not be null", bookings);
        assertFalse("Bookings list should not be empty", bookings.isEmpty());
    }
    
    @Test
    public void testGetBookingsForUser() throws SQLException {
        List<Booking> bookings = bookingsDB.getBookingsForUser(testUser.getEmail());
        assertNotNull("Bookings list should not be null", bookings);
        assertEquals("Should retrieve one booking for the test user", 1, bookings.size());
        
        Booking retrievedBooking = bookings.get(0);
        assertEquals("Destination should match", "Test Destination", retrievedBooking.getWhereTo());
    }
} 