package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Getting {

    public void getAllMembers() {
        String sql = "SELECT kategoriID, navn FROM Kategori"; // Replace with your actual table and column names

        // try-with-resources ensures connection and statement close automatically
        try (Connection conn = SQLConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("Fetching members from database:");
            while (rs.next()) {
                int id = rs.getInt("kategoriID");
                String name = rs.getString("navn");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
        }
    }
}
