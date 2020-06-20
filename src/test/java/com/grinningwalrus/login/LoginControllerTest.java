package com.grinningwalrus.login;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.grinningwalrus.controller.BasicUserController;
import com.grinningwalrus.jfx.JFX_APP;
import junit.framework.TestCase;

import java.util.ArrayList;

public class LoginControllerTest extends TestCase {

    private final LoginController lg = new LoginController();

    public void testLogin()
    {
        assertEquals("hiker", lg.login("razvan", "marmota"));
        System.out.println("TESTING - Hiker login test success");
        assertEquals("triporganizer", lg.login("dani", "parola"));
        System.out.println("TESTING - Trip organizer login test success");
        assertEquals("admin", lg.login("cosmin", "parola123"));
        System.out.println("TESTING - Admin login test success");
        assertEquals("error", lg.login("cosmin", "dsadas"));
        System.out.println("TESTING - Invalid password login test success");
        assertEquals("error", lg.login("dsadadsa", "dsadas"));
        System.out.println("TESTING - Invalid username login test success");
    }

    public void testRegister()
    {
        assertEquals("exists", lg.register("razvan", "marmota"));
        System.out.println("TESTING - Existing register test success");
        assertEquals("exists", lg.register("razvan", "dasdsa"));
        System.out.println("TESTING - Existing register test success (different password)");
        assertEquals("registered", lg.register("razvan1", "marmota"));
        System.out.println("TESTING - Existing register test success (different name - same pass)");
        assertEquals("exists", lg.register("razvan1", "marmota1"));
        System.out.println("TESTING - Existing register test success (different name - different pass)");
    }

    public void testGetUsers()
    {
        ArrayList<User> user_array = lg.getUsers();
        lg.register("new_user", "pass");
        ArrayList<User> user_array_out = lg.getUsers();
        assertEquals(user_array, user_array_out);
        System.out.println("Users database update test success.");
    }
}