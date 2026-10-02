package com.example.Entities;

public class Course {
    private String kursID;
    private String tittel;
    private String dato;
    private String minPris;
    private String maksPris;
    private String beskrivelse;
    private String bilde;
    private String arrangorID;
    private String kategoriID;

    public Course() {
    }

    // Getters and Setters
    public String getKursID() { return kursID; }
    public void setKursID(String kursID) { this.kursID = kursID; }

    public String getTittel() { return tittel; }
    public void setTittel(String tittel) { this.tittel = tittel; }

    public String getDato() { return dato; }
    public void setDato(String dato) { this.dato = dato; }

    public String getMinPris() { return minPris; }
    public void setMinPris(String minPris) { this.minPris = minPris; }

    public String getMaksPris() { return maksPris; }
    public void setMaksPris(String maksPris) { this.maksPris = maksPris; }

    public String getBeskrivelse() { return beskrivelse; }
    public void setBeskrivelse(String beskrivelse) { this.beskrivelse = beskrivelse; }

    public String getBilde() { return bilde; }
    public void setBilde(String bilde) { this.bilde = bilde; }

    public String getArrangorID() { return arrangorID; }
    public void setArrangorID(String arrangorID) { this.arrangorID = arrangorID; }

    public String getKategoriID() { return kategoriID; }
    public void setKategoriID(String kategoriID) { this.kategoriID = kategoriID; }
}