package com.grinningwalrus.baseclasses;

import java.util.ArrayList;

public class Record {
    private String name;
    private String location;
    private String altitude;

    public Record()
    {

    }

    public Record(String name, String location, String altitude)
    {
        this.name = name;
        this.location = location;
        this.altitude = altitude;
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

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String toString()
    {
        return this.name + " - " + this.location + " - " + this.altitude;
    }
}
