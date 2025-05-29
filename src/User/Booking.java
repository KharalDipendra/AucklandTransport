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
    
    public double getPrice() {
        return this.PRICE;
    }
    
    public double setPrice(double new_price) {
        return this.PRICE = new_price;
    }
    
    public String getWhereTo() {
        return this.where;
    }
    
    public String setWhereTo(String new_where) {
        //NORTH,EAST,SOUTH,WEST
        return this.where = new_where;
    }
    // --- bookingId ---
    public int getBookingId() {
        return bookingId;
    }
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    // --- name ---
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // --- email ---
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // --- dateBooked ---
    public LocalDate getDateBooked() {
        return dateBooked;
    }
    public void setDateBooked(LocalDate dateBooked) {
        this.dateBooked = dateBooked;
    }

    // --- departureDate ---
    public LocalDate getDepartureDate() {
        return departureDate;
    }
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    // --- serviceType ---
    public String getServiceType() {
        return serviceType;
    }
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
