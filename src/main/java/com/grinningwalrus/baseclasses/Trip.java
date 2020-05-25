package com.grinningwalrus.baseclasses;

public class Trip {
    private String name;
    private String location;
    private String date;

    public Trip()
    {

    }

    public Trip(String name, String location, String date)
    {
        this.name = name;
        this.location = location;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String toString()
    {
        return this.name + " - " + this.location + " - " + this.date;
    }
}
