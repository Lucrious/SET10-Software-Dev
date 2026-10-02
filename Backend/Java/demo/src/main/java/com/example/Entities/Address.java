package com.example.Entities;

public class Address {
    private String gate;
    private String gatenr;
    private String postnr;
    private String byNavn;

    public Address() {
    }

    // Getters and Setters
    public String getGate() { return gate; }
    public void setGate(String gate) { this.gate = gate; }

    public String getGatenr() { return gatenr; }
    public void setGatenr(String gatenr) { this.gatenr = gatenr; }

    public String getPostnr() { return postnr; }
    public void setPostnr(String postnr) { this.postnr = postnr; }

    public String getByNavn() { return byNavn; }
    public void setByNavn(String byNavn) { this.byNavn = byNavn; }
}