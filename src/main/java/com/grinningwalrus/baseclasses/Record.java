package com.grinningwalrus.baseclasses;

import java.util.ArrayList;

public class Trip {
    private String name;
    private String location;
    private String date;

    private ArrayList<String> signed_up_users = new ArrayList<String>();

    public Trip()
    {

    }

    public Trip(String name, String location, String date, ArrayList<String> signed_up_users)
    {
        this.name = name;
        this.location = location;
        this.date = date;
        this.signed_up_users = signed_up_users;
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

    public ArrayList<String> getSigned_up_users() {
        return signed_up_users;
    }

    public void setSigned_up_users(ArrayList<String> signed_up_users) {
        this.signed_up_users = signed_up_users;
    }

    public String toString()
    {
        return this.name + " - " + this.location + " - " + this.date;
    }
}
