package com.grinningwalrus.controller;

import com.grinningwalrus.baseclasses.Admin;
import com.grinningwalrus.baseclasses.ApplicationUser;
import com.grinningwalrus.baseclasses.Hiker;
import com.grinningwalrus.baseclasses.TripOrganizer;

public class BasicUserController
{
    private static ApplicationUser logged_user;

    public BasicUserController()
    {

    }

    public static void initialize(String Rank, String username)
    {
        if(Rank.equalsIgnoreCase("hiker"))
            logged_user = new Hiker();
        else if(Rank.equalsIgnoreCase("triporganizer"))
            logged_user = new TripOrganizer();
        else if(Rank.equalsIgnoreCase("admin"))
            logged_user = new Admin();
        logged_user.setUsername(username);
    }
}