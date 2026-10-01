package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public class Getting {

    // Handles Image-logic
    private String convertBlobToBase64(byte[] imageBytes) {
        if (imageBytes == null) {
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(imageBytes);
        return "data:image/jpeg;base64," + base64;
    }

    public ArrayList<String> getAllCategories() {
        ArrayList<String> categories = new ArrayList<>();
        String sql = "SELECT navn FROM Kategori";

        // Sikrer at koblingen åpner, og så stenger automatisk
        try (Connection conn = SQLConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                categories.add(rs.getString("navn"));
            }

            return categories;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<String> getAllCourses() {
        ArrayList<String> courses = new ArrayList<>();
        String sql = "SELECT * FROM Kurs";

        try (Connection conn = SQLConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String kursID = rs.getString("kursID");
                String tittel = rs.getString("tittel");
                String dato = rs.getString("dato");
                String minPris = rs.getString( "minPris");
                String maksPris = rs.getString("maksPris");
                String beskrivelse = rs.getString("beskrivelse");

                byte[] imageBytes = rs.getBytes("bilde");
                String imageData = convertBlobToBase64(imageBytes);
                
                String arrangorID = rs.getString("Arrangor_arrangorID");
                String kategoriID = rs.getString("Kategori_kategoriID");

                courses.add(kursID);
                courses.add(tittel);
                courses.add(dato);
                courses.add(minPris);
                courses.add(maksPris);
                courses.add(beskrivelse);
                courses.add(imageData);
                courses.add(arrangorID);
                courses.add(kategoriID);
            }
            
            return courses;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<String> getOHAdress() {
        ArrayList<String> OHAddress = new ArrayList<>();
        String sql = "SELECT a.Adresse_gate, a.Adresse_gatenr, a.Adresse_By_postnr, b.navn AS byNavn " +
                     "FROM Arrangor a " +
                     "JOIN Adresse ad ON a.Adresse_gate = ad.gate AND a.Adresse_gatenr = ad.gatenr AND a.Adresse_By_postnr = ad.By_postnr " +
                     "JOIN Byen b ON ad.By_postnr = b.postnr " +
                     "WHERE a.tittel LIKE ?";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Forebygger kræsj eller syntaksfeil
            pstmt.setString(1, "%Fredrikstad Husflidslag%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {

                    OHAddress.add(rs.getString("Adresse_gate"));
                    OHAddress.add(rs.getString("Adresse_gatenr"));
                    OHAddress.add(rs.getString("Adresse_By_postnr"));
                    OHAddress.add(rs.getString("byNavn"));
                }
            }

            return OHAddress;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<String> getOHContacts() {
        ArrayList<String> OHContacts = new ArrayList<>();
        String sql = "SELECT navn, etternavn, telefon, epost, posisjon " +
                     "FROM KontaktPerson " +
                     "WHERE kontaktID <= 6 AND Arrangor_arrangorID = ?";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Forebygger kræsj eller syntaksfeil
            pstmt.setInt(1, 1);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    OHContacts.add(rs.getString("navn"));
                    OHContacts.add(rs.getString("etternavn"));
                    OHContacts.add(rs.getString("telefon"));
                    OHContacts.add(rs.getString("epost"));
                    OHContacts.add(rs.getString("posisjon"));
                }
            }

            return OHContacts;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}
