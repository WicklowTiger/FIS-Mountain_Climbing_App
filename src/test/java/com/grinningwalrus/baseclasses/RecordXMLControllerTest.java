package com.grinningwalrus.baseclasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Collections;

public class RecordXMLControllerTest extends TestCase {

    public void test_addNewRecord()
    {
        ObservableList<Record> record_obs_list = FXCollections.observableArrayList();
        RecordXMLController.initialize(record_obs_list);
        ArrayList<Record> initial_records = new ArrayList<Record>();
        initial_records.addAll(RecordXMLController.getRecords());
        RecordXMLController.getRecords().add(new Record("razvan", "bej", "436"));
        RecordXMLController.updateXML();
        ArrayList<Record> out_records = RecordXMLController.getRecords();
        assertNotSame(initial_records, out_records);
        System.out.println("Adding new record with updating passed!");
    }

    public void test_addNewRecordWithoutUpdate()
    {
        ObservableList<Record> record_obs_list = FXCollections.observableArrayList();
        RecordXMLController.initialize(record_obs_list);
        ArrayList<Record> initial_records = new ArrayList<Record>();
        initial_records.addAll(RecordXMLController.getRecords());
        RecordXMLController.getRecords().add(new Record("razvan", "bej", "436"));
        RecordXMLController.initialize(record_obs_list);
        ArrayList<Record> out_records = RecordXMLController.getRecords();
        for(int i = 0; i < out_records.size(); i++)
        {
            assertEquals(initial_records.get(i).getName(), out_records.get(i).getName());
            assertEquals(initial_records.get(i).getAltitude(), out_records.get(i).getAltitude());
            assertEquals(initial_records.get(i).getLocation(), out_records.get(i).getLocation());
        }
        System.out.println("Adding new record without updating passed!");
    }
}