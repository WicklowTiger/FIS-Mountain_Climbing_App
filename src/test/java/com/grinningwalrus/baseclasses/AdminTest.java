package com.grinningwalrus.baseclasses;

import junit.framework.TestCase;

public class AdminTest extends TestCase {

    private final Admin a1 = new Admin();

    public void testGetRank()
    {
        assertEquals("admin", a1.getRank());
    }
}