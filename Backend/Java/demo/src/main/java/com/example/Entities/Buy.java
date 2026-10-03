package com.example.Entities;

public class Buy {
    private String kjopID;
    private String kjopsDato;
    private String antall;
    private String totalPris;
    private String brukerID;
    private String kursID;
    private String gjestNavn;
    private String gjestEpost;
    private String gjestTelefon;

    public String getKjopID() {
        return kjopID;
    }

    public void setKjopID(String kjopID) {
        this.kjopID = kjopID;
    }

    public String getKjopsDato() {
        return kjopsDato;
    }

    public void setKjopsDato(String kjopsDato) {
        this.kjopsDato = kjopsDato;
    }

    public String getAntall() {
        return antall;
    }

    public void setAntall(String antall) {
        this.antall = antall;
    }

    public String getTotalPris() {
        return totalPris;
    }

    public void setTotalPris(String totalPris) {
        this.totalPris = totalPris;
    }

    public String getBrukerID() {
        return brukerID;
    }

    public void setBrukerID(String brukerID) {
        this.brukerID = brukerID;
    }

    public String getKursID() {
        return kursID;
    }

    public void setKursID(String kursID) {
        this.kursID = kursID;
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
