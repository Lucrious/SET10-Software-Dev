package com.example.Entities;

public class WaitingList {
    private String ventelisteID;
    private String pameldingsdato;
    private String status;
    private String kursID;
    private String brukerID;
    private String gjestNavn;
    private String gjestEpost;
    private String gjestTelefon;

    public String getVentelisteID() {
        return ventelisteID;
    }

    public void setVentelisteID(String ventelisteID) {
        this.ventelisteID = ventelisteID;
    }

    public String getPameldingsdato() {
        return pameldingsdato;
    }

    public void setPameldingsdato(String pameldingsdato) {
        this.pameldingsdato = pameldingsdato;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKursID() {
        return kursID;
    }

    public void setKursID(String kursID) {
        this.kursID = kursID;
    }

    public String getBrukerID() {
        return brukerID;
    }

    public void setBrukerID(String brukerID) {
        this.brukerID = brukerID;
    }

    public String getGjestNavn() {
        return gjestNavn;
    }

    public void setGjestNavn(String gjestNavn) {
        this.gjestNavn = gjestNavn;
    }

    public String getGjestEpost() {
        return gjestEpost;
    }

    public void setGjestEpost(String gjestEpost) {
        this.gjestEpost = gjestEpost;
    }

    public String getGjestTelefon() {
        return gjestTelefon;
    }

    public void setGjestTelefon(String gjestTelefon) {
        this.gjestTelefon = gjestTelefon;
    }
}
