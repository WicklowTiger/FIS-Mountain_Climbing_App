package com.grinningwalrus.baseclasses;

import com.grinningwalrus.login.User;
import javafx.collections.ObservableList;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardController {

    private static ArrayList<User> leaderboard = new ArrayList<User>();

    public LeaderboardController()
    {

    }

    public static void sort(ArrayList<User> users)
    {
        leaderboard.addAll(users);
        Collections.sort(leaderboard);
    }

    public static ArrayList<User> getLeaderboard() {
        return leaderboard;
    }

}
