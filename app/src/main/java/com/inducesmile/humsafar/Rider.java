package com.inducesmile.humsafar;

public class Rider {

    private String  rSource;
    private String rDestination;
    private String rTime;
    private String rDate;
    private String rSeat;
    private String rName;

    public Rider()
    {

    }

    public Rider(String rSource, String rDestination, String rTime, String rDate, String rSeat, String rName) {
        this.rSource = rSource;
        this.rDestination = rDestination;
        this.rTime = rTime;
        this.rDate = rDate;
        this.rSeat = rSeat;
        this.rName = rName;
    }

    public String getrSource() {
        return rSource;
    }

    public void setrSource(String rSource) {
        this.rSource = rSource;
    }

    public String getrDestination() {
        return rDestination;
    }

    public void setrDestination(String rDestination) {
        this.rDestination = rDestination;
    }

    public String getrTime() {
        return rTime;
    }

    public void setrTime(String rTime) {
        this.rTime = rTime;
    }

    public String getrDate() {
        return rDate;
    }

    public void setrDate(String rDate) {
        this.rDate = rDate;
    }

    public String getrSeat() {
        return rSeat;
    }

    public void setrSeat(String rSeat) {
        this.rSeat = rSeat;
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName;
    }
}