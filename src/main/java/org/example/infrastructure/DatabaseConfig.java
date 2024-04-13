package org.example.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSWORD = "";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection connection) throws SQLException{
        connection.close();
    }
}
