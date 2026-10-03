package com.example.Entities;

public class Participation {
    private String deltakelseID;
    private String oppmotestatus; // Enten 1 (JA) eller 0 (NEI) (TINYINT i MySQL)
    private String dato;
    private String kursID;
    private String brukerID;
    private String gjestNavn;
    private String gjestEpost;
    private String gjestTelefon;

    public String getDeltakelseID() {
        return deltakelseID;
    }

    public void setDeltakelseID(String deltakelseID) {
        this.deltakelseID = deltakelseID;
    }

    public String getOppmotestatus() {
        return oppmotestatus;
    }

    public void setOppmotestatus(String oppmotestatus) {
        this.oppmotestatus = oppmotestatus;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
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
