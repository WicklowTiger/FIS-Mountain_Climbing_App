package com.grinningwalrus.baseclasses;

public class TripOrganizer extends ApplicationUser
{
    public TripOrganizer()
    {
        System.out.println("Hi, I am a trip organizer!");
    }

    public String getRank()
    {
        return "triporganizer";
    }
}
