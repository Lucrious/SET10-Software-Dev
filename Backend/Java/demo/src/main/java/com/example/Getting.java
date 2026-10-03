package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;

import com.example.Entities.Address;
import com.example.Entities.AdminLog;
import com.example.Entities.Arranger;
import com.example.Entities.Buy;
import com.example.Entities.Category;
import com.example.Entities.City;
import com.example.Entities.Contact;
import com.example.Entities.Course;
import com.example.Entities.FAQ;
import com.example.Entities.Participation;
import com.example.Entities.Post;
import com.example.Entities.Ticket;
import com.example.Entities.TicketType;
import com.example.Entities.User;
import com.example.Entities.WaitingList;

// NB! Denne klassen er ment til å hente generaliserte metoder som vises overalt på nettsiden.
// Mer spesifikke spørringer finnes i "Filters.java"

public class Getting {

    private String convertBlobToBase64(byte[] imageBytes) {
        if (imageBytes == null) {
            return null;
        }
        String base64 = Base64.getEncoder().encodeToString(imageBytes);
        return "data:image/jpeg;base64," + base64;
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM Kategori";

        try (Connection conn = SQLConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setKategoriID(rs.getString("kategoriID"));
                category.setNavn(rs.getString("navn"));

                categories.add(category);
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

    public ArrayList<Arranger> getAllArrangers() {
        ArrayList<Arranger> arrangers = new ArrayList<>();
        String sql = "SELECT * FROM Arrangor";

        try (Connection conn = SQLConnect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Arranger arranger = new Arranger();
                
                arranger.setArrangorID(rs.getString("arrangorID"));
                arranger.setTittel(rs.getString("tittel"));
                arranger.setGate(rs.getString("Adresse_gate"));
                arranger.setGatenr(rs.getString("Adresse_gatenr"));
                arranger.setPostnr(rs.getString("Adresse_By_postnr"));

                arrangers.add(arranger);
            }
            return arrangers;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public ArrayList<TicketType> getAllTicketTypes() {
        ArrayList<TicketType> ticketTypes = new ArrayList<>();
        String sql = "SELECT billettypeID, tittel, antall, pris, Kurs_kursID FROM Billettype";
        // Litt usikker på hvorfor "SELECT * FROM Bilettype" ikke funker, men dette funket ovenfor det jeg nevnte..

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                TicketType ticketType = new TicketType();
                ticketType.setBillettypeID(rs.getString("billettypeID"));
                ticketType.setTittel(rs.getString("tittel"));
                ticketType.setAntall(rs.getString("antall"));
                ticketType.setPris(rs.getString("pris"));
                ticketType.setKursID(rs.getString("Kurs_kursID"));

                ticketTypes.add(ticketType);
            }
            return ticketTypes;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<WaitingList> getAllWaitingLists() {
        ArrayList<WaitingList> waitingLists = new ArrayList<>();
        String sql = "SELECT * from Venteliste";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                WaitingList waitingList = new WaitingList();
                waitingList.setVentelisteID(rs.getString("ventelisteID"));
                waitingList.setPameldingsdato(rs.getString("pameldingsdato"));
                waitingList.setStatus(rs.getString("status"));
                waitingList.setKursID(rs.getString("Kurs_kursID"));
                waitingList.setBrukerID(rs.getString("Bruker_brukerID"));
                waitingList.setGjestNavn(rs.getString("gjestNavn"));
                waitingList.setGjestEpost(rs.getString("gjestEpost"));
                waitingList.setGjestTelefon(rs.getString("gjestTelefon"));

                waitingLists.add(waitingList);
            }
            return waitingLists;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Participation> getAllParticipations() {
        ArrayList<Participation> participations = new ArrayList<>();
        String sql = "SELECT * from Deltakelse";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Participation participation = new Participation();
                participation.setDeltakelseID(rs.getString("deltakelseID"));
                participation.setOppmotestatus(rs.getString("oppmotestatus"));
                participation.setDato(rs.getString("dato"));
                participation.setKursID(rs.getString("Kurs_kursID"));
                participation.setBrukerID(rs.getString("Bruker_brukerID"));
                participation.setGjestNavn(rs.getString("gjestNavn"));
                participation.setGjestEpost(rs.getString("gjestEpost"));
                participation.setGjestTelefon(rs.getString("gjestTelefon"));
                
                participations.add(participation);
            }
            return participations;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Post> getAllPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        String sql = "SELECT * from Innlegg";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Post post = new Post();
                post.setNyhetID(rs.getString("nyhetID"));
                post.setTittel(rs.getString("tittel"));
                post.setTekst(rs.getString("tekst"));
                post.setPubliseringsdato(rs.getString("publiseringsdato"));
                post.setArrangorID(rs.getString("Arrangor_arrangorID"));

                posts.add(post);
            }
            return posts;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<FAQ> getAllFAQs() {
        ArrayList<FAQ> faqs = new ArrayList<>();
        String sql = "SELECT * from FAQ";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                FAQ faq = new FAQ();
                faq.setFaqID(rs.getString("faqID"));
                faq.setSporsmal(rs.getString("sporsmal"));
                faq.setSvar(rs.getString("svar"));

                faqs.add(faq);
            }
            return faqs;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<City> getAllCities() {
        ArrayList<City> cities = new ArrayList<>();
        String sql = "SELECT * from Byen";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                City city = new City();
                city.setPostnr(rs.getString("postnr"));
                city.setNavn(rs.getString("navn"));

                cities.add(city);
            }
            return cities;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<AdminLog> getAllAdminLogs() {
        ArrayList<AdminLog> adminlogs = new ArrayList<>();
        String sql = "SELECT * from AdminLogg";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                AdminLog adminlog = new AdminLog();
                adminlog.setLoggID(rs.getString("loggID"));
                adminlog.setHandling(rs.getString("handling"));
                adminlog.setTidsstempel(rs.getString("tidsstempel"));
                adminlog.setDato(rs.getString("dato"));
                adminlog.setBrukerID(rs.getString("Bruker_brukerID"));

                adminlogs.add(adminlog);
            }
            return adminlogs;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Buy> getAllBuys() {
        ArrayList<Buy> buys = new ArrayList<>();
        String sql = "SELECT * from Kjop";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Buy buy = new Buy();
                buy.setKjopID(rs.getString("kjopID"));
                buy.setKjopsDato(rs.getString("kjopsDato"));
                buy.setAntall(rs.getString("antall"));
                buy.setTotalPris(rs.getString("totalPris"));
                buy.setBrukerID(rs.getString("Bruker_brukerID"));
                buy.setKursID(rs.getString("Kurs_kursID"));
                buy.setGjestNavn(rs.getString("gjestNavn"));
                buy.setGjestEpost(rs.getString("gjestEpost"));
                buy.setGjestTelefon(rs.getString("gjestTelefon"));

                buys.add(buy);
            }
            return buys;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * from Billett";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Ticket ticket = new Ticket();
                ticket.setBillettID(rs.getString("billettID"));
                ticket.setTittel(rs.getString("tittel"));
                ticket.setAntall(rs.getString("antall"));
                ticket.setPris(rs.getString("pris"));
                ticket.setKjopID(rs.getString("Kjop_kjopID"));

                tickets.add(ticket);
            }
            return tickets;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public ArrayList<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        String sql = "SELECT * from Bruker";

        try (Connection conn = SQLConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                User user = new User();
                user.setBrukerID(rs.getString("brukerID"));
                user.setNavn(rs.getString("navn"));
                user.setEtternavn(rs.getString("etternavn"));
                user.setTelefon(rs.getString("telefon"));
                user.setEpost(rs.getString("epost"));
                user.setRolle(rs.getString("rolle"));

                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            return new ArrayList<>();
        }
    }


}