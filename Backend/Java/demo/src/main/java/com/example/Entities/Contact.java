package com.example.Entities;

public class Contact {
    private String navn;
    private String etternavn;
    private String telefon;
    private String epost;
    private String posisjon;

    public Contact() {
    }

    // Getters and Setters
    public String getNavn() { return navn; }
    public void setNavn(String navn) { this.navn = navn; }

    public String getEtternavn() { return etternavn; }
    public void setEtternavn(String etternavn) { this.etternavn = etternavn; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getEpost() { return epost; }
    public void setEpost(String epost) { this.epost = epost; }

    public String getPosisjon() { return posisjon; }
    public void setPosisjon(String posisjon) { this.posisjon = posisjon; }
}