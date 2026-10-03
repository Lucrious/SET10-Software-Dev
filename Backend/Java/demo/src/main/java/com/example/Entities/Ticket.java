package com.example.Entities;

public class Ticket {
    private String billettID;
    private String tittel;
    private String antall;
    private String pris;
    private String kjopID;

    public String getBillettID() {
        return billettID;
    }

    public void setBillettID(String billettID) {
        this.billettID = billettID;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getAntall() {
        return antall;
    }

    public void setAntall(String antall) {
        this.antall = antall;
    }

    public String getPris() {
        return pris;
    }

    public void setPris(String pris) {
        this.pris = pris;
    }

    public String getKjopID() {
        return kjopID;
    }

    public void setKjopID(String kjopID) {
        this.kjopID = kjopID;
    }
}
