package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class LoginOptionTest {

    @Before
    public void setUp() throws Exception {
        TestUtilities.redirectOutput();
    }

    @Test
    public void testLoginWithInvalidCredentials() throws Exception {
        LoginOption option = new LoginOption();
        User user = null;
        TestUtilities.setInput("invaliduser" + System.getProperty("line.separator") + "invalidpassword");
        String feedback = option.execute(user, null);
        assertEquals("Invalid credentials.",  feedback);
        assertNull(user);
    }

    @Test
    public void testLoginWithValidCredentials() throws Exception {
        LoginOption option = new LoginOption();
        User user = null;
        UserAccountVault userAccountVault = UserAccountVault.getInstance();
        userAccountVault.setUserAccounts(new HashMap<LoginCredential, User>() {{
            put(new LoginCredential("000-0001", "password1"), new User());
        }});
        TestUtilities.setInput("000-0001" + System.getProperty("line.separator") + "password1");
        String feedback = option.execute(user, null);
        assertEquals("Login successful.", feedback);
    }
}