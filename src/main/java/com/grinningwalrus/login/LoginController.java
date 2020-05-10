//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.grinningwalrus.login;

import com.grinningwalrus.controller.BasicUserController;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class LoginController implements Serializable {
    private ArrayList<User> users = new ArrayList();

    public LoginController() {
        this.initialize();
    }

    private void initialize() {
        try {
            File IN_FILE = new File("src/main/resources/database.xml");
            FileInputStream fis = new FileInputStream(IN_FILE);
            XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(fis));
            boolean reached_end_of_file = false;

            while(!reached_end_of_file) {
                try {
                    Object decoded_user = decoder.readObject();
                    this.users.add((User)decoded_user);
                } catch (Exception var6) {
                    reached_end_of_file = true;
                }
            }
        } catch (IOException var7) {
            var7.printStackTrace();
        }

    }

    public void updateXML() {
        try {
            File OUT_FILE = new File("src/main/resources/database.xml");
            FileOutputStream fos = new FileOutputStream(OUT_FILE);
            XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(fos));
            Iterator var4 = this.users.iterator();

            while(var4.hasNext()) {
                User u = (User)var4.next();
                encoder.writeObject(u);
            }

            encoder.close();
            fos.close();
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public boolean login(String username, String pass) {
        Iterator var3 = this.users.iterator();

        User u;
        do {
            if (!var3.hasNext()) {
                System.out.println("Username does not exist");
                return false;
            }

            u = (User)var3.next();
        } while(!u.getName().equalsIgnoreCase(username));

        if (u.getPassword().equals(pass)) {
            System.out.println("Successfully logged in");
            BasicUserController.initialize(u.getRank(), u.getName());
            return true;
        } else {
            System.out.println("Invalid password");
            return false;
        }
    }

    public void register(String username, String pass) {
        Iterator var3 = this.users.iterator();

        User u;
        do {
            if (!var3.hasNext()) {
                this.users.add(new User(username, pass, "hiker"));
                this.updateXML();
                return;
            }

            u = (User)var3.next();
        } while(!u.getName().equalsIgnoreCase(username));

        System.out.println("That username already exists");
    }
}
