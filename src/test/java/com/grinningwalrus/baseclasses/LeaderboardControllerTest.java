package com.grinningwalrus.baseclasses;

import com.grinningwalrus.login.LoginController;
import com.grinningwalrus.login.User;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardControllerTest extends TestCase {
    private final LoginController lg = new LoginController();

    public void testSort()
    {
        ArrayList<User> database_users = lg.getUsers();
        LeaderboardController.sort(database_users);
        assertNotSame(database_users, LeaderboardController.getLeaderboard()); //Compare unsorted list with sorted list
        Collections.sort(database_users); //sort list and compare again
        assertEquals(database_users, LeaderboardController.getLeaderboard());
    }
}