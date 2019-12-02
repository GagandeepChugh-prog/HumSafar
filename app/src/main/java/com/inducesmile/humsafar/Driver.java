package com.inducesmile.humsafar;

import java.sql.Time;

public class Driver {

    private String dSource;
    private String dDestination;
    private String dTime;
    private String dDate;
    private String dSeats;
    private String dName;

    public Driver()
    {


    }

    public Driver(String dSource, String dDestination, String dTime, String dDate, String dSeats, String dName) {
        this.dSource = dSource;
        this.dDestination = dDestination;
        this.dTime = dTime;
        this.dDate = dDate;
        this.dSeats = dSeats;
        this.dName = dName;
    }

    public String getdSource() {
        return dSource;
    }

    public void setdSource(String dSource) {
        this.dSource = dSource;
    }

    public String getdDestination() {
        return dDestination;
    }

    public void setdDestination(String dDestination) {
        this.dDestination = dDestination;
    }

    public String getdTime() {
        return dTime;
    }

    public void setdTime(String dTime) {
        this.dTime = dTime;
    }

    public String getdDate() {
        return dDate;
    }

    public void setdDate(String dDate) {
        this.dDate = dDate;
    }

    public String getdSeats() {
        return dSeats;
    }

    public void setdSeats(String dSeats) {
        this.dSeats = dSeats;
    }

    public String getdName() {
        return dName;
    }

    public void setdName(String dName) {
        this.dName = dName;
    }
}