package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class SQLConnect {
    // Configure dotenv to look inside the Java/demo folder for .env.local
    private static final Dotenv dotenv = Dotenv.configure()
        .filename(".env.local")
        .load();

    private static final String URL = String.format(
        "jdbc:mysql://%s:%s/%s?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC",
        dotenv.get("DB_HOST", "127.0.0.1"),
        dotenv.get("DB_PORT", "3306"),
        dotenv.get("DB_DATABASE")
    );
    private static final String USER = dotenv.get("DB_USERNAME");
    private static final String PASSWORD = dotenv.get("DB_PASSWORD");

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found in classpath.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}