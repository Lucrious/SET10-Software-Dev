package com.example;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseCommands {
    public static void main(String[] args) {
        System.out.println("Attempting to connect to MySQL...");

        try (Connection conn = SQLConnect.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Successfully connected to the MySQL database!");
            }
        } catch (SQLException e) {
            System.err.println("Connection failed! Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
