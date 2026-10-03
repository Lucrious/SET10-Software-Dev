package com.example.Entities;

public class TicketType {
    private String billettypeID;
    private String tittel;
    private String antall;
    private String pris;
    private String kursID;

    public String getBillettypeID() {
        return billettypeID;
    }

    public void setBillettypeID(String billettypeID) {
        this.billettypeID = billettypeID;
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

    public String getKursID() {
        return kursID;
    }

    public void setKursID(String kursID) {
        this.kursID = kursID;
    }
}
