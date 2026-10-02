package com.example;

import com.example.Entities.Address;
import com.example.Entities.Contact;
import com.example.Entities.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

public class Getting {

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

    public ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Kurs";

        try (Connection conn = SQLConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Course course = new Course();
                course.setKursID(rs.getString("kursID"));
                course.setTittel(rs.getString("tittel"));
                course.setDato(rs.getString("dato"));
                course.setMinPris(rs.getString("minPris"));
                course.setMaksPris(rs.getString("maksPris"));
                course.setBeskrivelse(rs.getString("beskrivelse"));
                
                byte[] imageBytes = rs.getBytes("bilde");
                course.setBilde(convertBlobToBase64(imageBytes));
                
                course.setArrangorID(rs.getString("Arrangor_arrangorID"));
                course.setKategoriID(rs.getString("Kategori_kategoriID"));

                courses.add(course);
            }
            return courses;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Address> getOHAdress() {
        ArrayList<Address> addresses = new ArrayList<>();
        String sql = "SELECT a.Adresse_gate, a.Adresse_gatenr, a.Adresse_By_postnr, b.navn AS byNavn " +
                     "FROM Arrangor a " +
                     "JOIN Adresse ad ON a.Adresse_gate = ad.gate AND a.Adresse_gatenr = ad.gatenr AND a.Adresse_By_postnr = ad.By_postnr " +
                     "JOIN Byen b ON ad.By_postnr = b.postnr " +
                     "WHERE a.tittel LIKE ?";

        try (Connection conn = SQLConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%Fredrikstad Husflidslag%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Address address = new Address();
                    address.setGate(rs.getString("Adresse_gate"));
                    address.setGatenr(rs.getString("Adresse_gatenr"));
                    address.setPostnr(rs.getString("Adresse_By_postnr"));
                    address.setByNavn(rs.getString("byNavn"));
                    
                    addresses.add(address);
                }
            }
            return addresses;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<Contact> getOHContacts() {
        ArrayList<Contact> contacts = new ArrayList<>();
        String sql = "SELECT navn, etternavn, telefon, epost, posisjon " +
                     "FROM KontaktPerson " +
                     "WHERE kontaktID <= 6 AND Arrangor_arrangorID = ?";

        try (Connection conn = SQLConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, 1);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Contact contact = new Contact();
                    contact.setNavn(rs.getString("navn"));
                    contact.setEtternavn(rs.getString("etternavn"));
                    contact.setTelefon(rs.getString("telefon"));
                    contact.setEpost(rs.getString("epost"));
                    contact.setPosisjon(rs.getString("posisjon"));

                    contacts.add(contact);
                }
            }
            return contacts;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return null;
        }
    }
}