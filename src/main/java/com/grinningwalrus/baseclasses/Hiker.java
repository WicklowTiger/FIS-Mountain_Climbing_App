package com.grinningwalrus.baseclasses;

public class Hiker extends ApplicationUser
{
    public Hiker()
    {
        System.out.println("Hi, I am a hiker!");
    }

    public String getRank()
    {
        return "hiker";
    }
}
