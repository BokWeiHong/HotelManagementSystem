package HotelManagementSystem;

import java.sql.*;

// Class to handle database connection
public class conn {
    // Connection and Statement objects
    Connection c;
    Statement s;

    // Constructor to establish the database connection
    public conn() {
        try {
            // Load the MySQL JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            // Replace "hotelmanagementsystem", "root", and "admin123" with your actual database, username, and password
            c = DriverManager.getConnection("jdbc:mysql:///hotelmanagementsystem", "root", "admin123");

            // Create a Statement object for executing SQL queries
            s = c.createStatement();
        } catch (Exception e) {
            // Handle any exceptions that occur during the connection setup
            System.out.println(e);
        }
    }
}

