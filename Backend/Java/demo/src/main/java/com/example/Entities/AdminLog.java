package com.example.Entities;

public class AdminLog {
    private String loggID;
    private String handling;
    private String tidsstempel;
    private String dato;
    private String brukerID;

    public String getLoggID() {
        return loggID;
    }

    public void setLoggID(String loggID) {
        this.loggID = loggID;
    }

    public String getHandling() {
        return handling;
    }

    public void setHandling(String handling) {
        this.handling = handling;
    }

    public String getTidsstempel() {
        return tidsstempel;
    }

    public void setTidsstempel(String tidsstempel) {
        this.tidsstempel = tidsstempel;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public String getBrukerID() {
        return brukerID;
    }

    public void setBrukerID(String brukerID) {
        this.brukerID = brukerID;
    }
    
}
