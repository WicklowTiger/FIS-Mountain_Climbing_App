package com.grinningwalrus.baseclasses;

import com.grinningwalrus.controller.BasicUserController;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public class TripXMLController implements java.io.Serializable{


    private static ArrayList<Trip> trips = new ArrayList<Trip>();

    public TripXMLController()
    {

    }

    public static void initialize(ObservableList<Trip> list)
    {
        try {
            File IN_FILE = new File("src/main/resources/trip_database.xml");
            FileInputStream fis = new FileInputStream(IN_FILE);
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
            boolean reached_end_of_file = false;
            while(!reached_end_of_file)
            {
                try
                {
                    Object decoded_trip = decoder.readObject();
                    list.add((Trip)decoded_trip);
                    trips.add((Trip)decoded_trip);
                }
                catch(Exception e)
                {
                    reached_end_of_file = true;
                }

            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public static void updateXML()
    {
        try
        {
            File OUT_FILE = new File("src/main/resources/trip_database.xml");
            FileOutputStream fos = new FileOutputStream(OUT_FILE);
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
            for(Trip t:trips)
            {
                encoder.writeObject(t);
            }
            encoder.close();
            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public static ArrayList<Trip> getTrips() {
        return trips;
    }

}
