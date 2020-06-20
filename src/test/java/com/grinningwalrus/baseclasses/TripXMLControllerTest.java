package com.grinningwalrus.baseclasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

public class TripXMLControllerTest extends TestCase {

    public void test_addNewTrip()
    {
        ObservableList<Trip> trips_obs_list = FXCollections.observableArrayList();
        TripXMLController.initialize(trips_obs_list);
        ArrayList<Trip> initial_trips = new ArrayList<Trip>();
        initial_trips.addAll(TripXMLController.getTrips());
        TripXMLController.getTrips().add(new Trip("cooltrip", "retezat", "14/5/1234"));
        TripXMLController.updateXML();
        ArrayList<Trip> out_trips = TripXMLController.getTrips();
        assertNotSame(initial_trips, out_trips);
        System.out.println("Adding new trip with updating passed!");
    }

    public void test_addNewTripWithoutUpdate()
    {
        ObservableList<Trip> trips_obs_list = FXCollections.observableArrayList();
        TripXMLController.initialize(trips_obs_list);
        ArrayList<Trip> initial_trips = new ArrayList<Trip>();
        initial_trips.addAll(TripXMLController.getTrips());
        TripXMLController.getTrips().add(new Trip("cooltrip", "retezat", "14/5/1234"));
        TripXMLController.initialize(trips_obs_list);
        ArrayList<Trip> out_trips = TripXMLController.getTrips();
        for(int i = 0; i < out_trips.size(); i++)
        {
            assertEquals(initial_trips.get(i).getName(), out_trips.get(i).getName());
            assertEquals(initial_trips.get(i).getDate(), out_trips.get(i).getDate());
            assertEquals(initial_trips.get(i).getLocation(), out_trips.get(i).getLocation());
        }
        System.out.println("Adding new trip without updating passed!");
    }
}