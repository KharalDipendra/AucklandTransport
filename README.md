# Auckland Transport Desktop App

This is a Java Swing desktop application for managing a fictional Auckland Transport system. It provides functionalities for both regular users and administrators. The application uses an embedded Apache Derby database for data persistence and features a modern UI theme using FlatLaf.

## Features

-   **User Authentication**: Secure login for users and admins.
-   **User Registration**: New users can create an account.
-   **Booking System**: Users can book transport services.
-   **Card Management**: Users can generate a virtual card and top up their balance.
-   **Account Management**: Users can update their email and password.
-   **Admin Panel**: Admins can view all users and bookings and have the ability to delete them or grant admin privileges.
-   **Embedded Database**: Uses Apache Derby to store user and booking information.
-   **Modern UI**: Uses the [FlatLaf](https://www.formdev.com/flatlaf/) library for a clean and modern look and feel.

## How to Run

1.  **Clone the repository:**
    ```bash
    git clone <repository-url>
    ```
2.  **Open in your IDE:**
    Open the project in your favorite Java IDE (e.g., NetBeans, IntelliJ, Eclipse).
3.  **Run the application:**
    Locate the `Main.java` file and run it. This will start the application and show the login window.

## Database Setup

The application uses an embedded Apache Derby database. The database files will be created in a directory named `TransportDB` in the root of the project folder when the application is first run.

### Initial Database Reset

The `Main.java` file contains a line to reset the database:

```java
//INITIAL WINDOW comment it out
Tables.createWindow();
```

-   **To reset the database**: Uncomment the `Tables.createWindow();` line in `Main.java` and run the application. A confirmation window will appear asking if you want to drop and recreate the tables.
-   **For normal operation**: Keep the `Tables.createWindow();` line commented out.

## Project Structure

-   `src/`
    -   `Database/`: Contains classes for database connection (`DBManager`), table creation (`Tables`), and data access objects for Users (`UsersDB`) and Bookings (`BookingsDB`).
    -   `GUI/`: Contains all the Java Swing UI forms for the application (`LaunchWindow`, `UserWindow`, `AdminWindow`, etc.).
    -   `User/`: Contains the model classes for `User`, `Booking`, and `Session`.
    -   `card/`: Contains logic for managing user cards.
    -   `Images/`: Contains images used in the UI.
    -   `Main.java`: The main entry point of the application.
-   `test/`: Contains JUnit tests for the database classes.
-   `lib/`: Contains all the required libraries and dependencies.

## Dependencies

The project relies on the following external libraries located in the `lib` directory:

-   `flatlaf-3.4.1.jar`: For the modern UI look and feel.
-   `derby.jar` & `derbyclient.jar`: For the Apache Derby database.
-   `LGoodDatePicker-11.2.1.jar`: For the date picker component in the UI.
-   `junit-4.13.2.jar` & `hamcrest-core-1.3.jar`: For unit testing. 