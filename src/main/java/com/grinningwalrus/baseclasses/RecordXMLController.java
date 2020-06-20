package com.grinningwalrus.baseclasses;

import javafx.collections.ObservableList;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;

public class RecordXMLController implements Serializable{


    private static ArrayList<Record> records = new ArrayList<Record>();

    public RecordXMLController()
    {

    }

    public static void initialize(ObservableList<Record> list)
    {
        try {
            File IN_FILE = new File("classes/record_database.xml");
            FileInputStream fis = new FileInputStream(IN_FILE);
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
            boolean reached_end_of_file = false;
            while(!reached_end_of_file)
            {
                try
                {
                    Object decoded_record = decoder.readObject();
                    records.add((Record)decoded_record);
                    list.add((Record)decoded_record);
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
            File OUT_FILE = new File("classes/record_database.xml");
            FileOutputStream fos = new FileOutputStream(OUT_FILE);
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
            for(Record t:records)
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

    public static ArrayList<Record> getRecords() {
        return records;
    }

}
