package org.example.infrastructure;

import org.example.repositories._Loggable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig implements _Loggable {
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public Connection getConnection() {
        try {
            logInfo("Successful database connection");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            logError("Could not connect to the database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void closeConnection() {
        try {
            logInfo("Database connection closed");
            this.getConnection().close();
        } catch (SQLException e) {
            logError("Error closing database connection: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
