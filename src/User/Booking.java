package User;

import java.time.LocalDate;

/**
 * Represents a transport booking for a user.
 * Mirrors the BOOKINGS table schema:
 * - bookingId INT (identity PK)
 * - name VARCHAR(100)
 * - email VARCHAR(150)
 * - dateBooked DATE
 * - departureDate DATE
 * - serviceType VARCHAR(10)
 */
public class Booking {
    private int bookingId;
    private String name;
    private String email;
    private LocalDate dateBooked;
    private LocalDate departureDate;
    private String serviceType;
    private String where;
    private double PRICE;
    

    /**
     * No-arg constructor for frameworks and DAO use.
     */
    public Booking() {
    }

    /**
     * Full constructor for manual creation.
     */
    public Booking(int bookingId,
                   String name,
                   String email,
                   LocalDate dateBooked,
                   LocalDate departureDate,
                   String serviceType) {
        this.bookingId = bookingId;
        this.name = name;
        this.email = email;
        this.dateBooked = dateBooked;
        this.departureDate = departureDate;
        this.serviceType = serviceType;
        this.where = "NORTH";
        this.PRICE = 0.0;
    }
    
    // gets the price of the booking
    public double getPrice() {
        return this.PRICE;
    }
    
    // sets the price of the booking
    public double setPrice(double new_price) {
        return this.PRICE = new_price;
    }
    
    // gets the destination of the booking
    public String getWhereTo() {
        return this.where;
    }
    
    // sets the destination of the booking
    public String setWhereTo(String new_where) {
        //NORTH,EAST,SOUTH,WEST
        return this.where = new_where;
    }
    // gets the booking id
    public int getBookingId() {
        return bookingId;
    }
    // sets the booking id
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    // gets the name of the user who made the booking
    public String getName() {
        return name;
    }
    // sets the name of the user who made the booking
    public void setName(String name) {
        this.name = name;
    }

    // gets the email of the user who made the booking
    public String getEmail() {
        return email;
    }
    // sets the email of the user who made the booking
    public void setEmail(String email) {
        this.email = email;
    }

    // gets the date the booking was made
    public LocalDate getDateBooked() {
        return dateBooked;
    }
    // sets the date the booking was made
    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }

    // gets the departure date of the booking
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    // sets the departure date of the booking
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    // gets the service type of the booking
    public String getServiceType() {
        return serviceType;
    }
    // sets the service type of the booking
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public String toString() {
        return String.format(
            "Booking[id=%d, user=%s, type=%s, departure=%s]", 
            bookingId, name, serviceType, departureDate
        );
    }
}
