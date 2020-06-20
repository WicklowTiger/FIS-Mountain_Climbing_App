package com.grinningwalrus.baseclasses;

import junit.framework.TestCase;

public class ApplicationUserTest extends TestCase {

    private final ApplicationUser a1 = new Admin();
    private final ApplicationUser h1 = new Hiker();
    private final ApplicationUser t1 = new TripOrganizer();

    public void testGetRank()
    {
        assertEquals("admin", a1.getRank());
        assertEquals("hiker", h1.getRank());
        assertEquals("triporganizer", t1.getRank());
    }
}