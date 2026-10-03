package com.example.Entities;

public class Post {
    private String nyhetID;
    private String tittel;
    private String tekst;
    private String publiseringsdato;
    private String arrangorID;

    public String getNyhetID() {
        return nyhetID;
    }

    public void setNyhetID(String nyhetID) {
        this.nyhetID = nyhetID;
    }

    public String getTittel() {
        return tittel;
    }

    public void setTittel(String tittel) {
        this.tittel = tittel;
    }

    public String getTekst() {
        return tekst;
    }

    public void setTekst(String tekst) {
        this.tekst = tekst;
    }

    public String getPubliseringsdato() {
        return publiseringsdato;
    }

    public void setPubliseringsdato(String publiseringsdato) {
        this.publiseringsdato = publiseringsdato;
    }

    public String getArrangorID() {
        return arrangorID;
    }

    public void setArrangorID(String arrangorID) {
        this.arrangorID = arrangorID;
    }
}
