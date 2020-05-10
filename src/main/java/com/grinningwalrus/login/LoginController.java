package com.grinningwalrus.login;

import com.grinningwalrus.controller.BasicUserController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;

public class LoginController implements java.io.Serializable{

    private ArrayList<User> users = new ArrayList<User>();

    public LoginController()
    {
        initialize();
    }

    private void initialize()
    {
        try {
            File IN_FILE = new File("src/main/resources/database.xml");
            FileInputStream fis = new FileInputStream(IN_FILE);
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
            boolean reached_end_of_file = false;
            while(!reached_end_of_file)
            {
                try
                {
                    Object decoded_user = decoder.readObject();
                    users.add((User)decoded_user);
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
//        for(User u: users)
//        {
//            System.out.println(u.toString());
//        }
    }

    public void updateXML()
    {
        try
        {
            File OUT_FILE = new File("src/main/resources/database.xml");
            FileOutputStream fos = new FileOutputStream(OUT_FILE);
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
            for(User u:users)
            {
                encoder.writeObject(u);
            }
            encoder.close();
            fos.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

    public boolean login(String username, String pass)
    {
        for(User u:users)
        {
            if(u.getName().equalsIgnoreCase(username))
            {
                if(u.getPassword().equals(pass)) {
                    System.out.println("Successfully logged in");
                    BasicUserController.initialize(u.getRank(), u.getName());
                    return true;
                }
                else {
                    System.out.println("Invalid password");
                    return false;
                }
            }
        }
        System.out.println("Username does not exist");
        return false;
    }

    public void register(String username, String pass)
    {
        for(User u:users)
        {
            if(u.getName().equalsIgnoreCase(username)) {
                System.out.println("That username already exists");
                return;
            }
        }
        users.add(new User(username, pass, "hiker"));
        updateXML();
    }
}