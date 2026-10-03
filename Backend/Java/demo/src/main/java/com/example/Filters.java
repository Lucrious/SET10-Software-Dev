package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import com.example.Entities.Course;
import com.example.Entities.Post;

public class Filters {

        private String convertBlobToBase64(byte[] imageBytes) {
            if (imageBytes == null) {
                return null;
            }
            String base64 = Base64.getEncoder().encodeToString(imageBytes);
            return "data:image/jpeg;base64," + base64;
        }

        public ArrayList<Course> searchCourses(String searchTerm) {
        ArrayList<Course> courses = new ArrayList<>();
        // Use % wildcards for partial matching
        String sql = "SELECT * FROM Kurs WHERE tittel LIKE ? OR beskrivelse LIKE ? ORDER BY dato DESC";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);

            try (ResultSet rs = pstmt.executeQuery()) {
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
            }
            
            return courses;

        } catch (SQLException e) {
            System.err.println("Search query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Post> searchPosts(String searchTerm) {
        ArrayList<Post> posts = new ArrayList<>();
        // Use % wildcards for partial matching
        String sql = "SELECT * FROM Innlegg WHERE tittel LIKE ? OR tekst LIKE ? ORDER BY publiseringsdato DESC";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + searchTerm + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Post post = new Post();
                    post.setNyhetID(rs.getString("nyhetID"));
                    post.setTittel(rs.getString("tittel"));
                    post.setTekst(rs.getString("tekst"));
                    post.setPubliseringsdato(rs.getString("publiseringsdato"));
                    post.setArrangorID(rs.getString("Arrangor_arrangorID"));

                    posts.add(post);
                }
            }

            return posts;

        } catch (SQLException e) {
            System.err.println("Search query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
}
