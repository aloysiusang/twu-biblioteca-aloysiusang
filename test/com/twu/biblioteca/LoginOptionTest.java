package com.twu.biblioteca;

import org.junit.After;
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

    @After
    public void tearDown() throws Exception {
        TestUtilities.resetStreams();
    }

    @Test
    public void testLoginWithInvalidCredentials() throws Exception {
        LoginOption option = new LoginOption();
        UserAccountManager userAccountManager = new UserAccountManager();
        TestUtilities.setInput("invaliduser" + System.getProperty("line.separator") + "invalidpassword");
        String feedback = option.execute(userAccountManager, null);
        assertEquals("Invalid credentials.",  feedback);
        assertNull(userAccountManager.getCurrentUser());
    }

    @Test
    public void testLoginWithValidCredentials() throws Exception {
        LoginOption option = new LoginOption();
        UserAccountManager userAccountManager = new UserAccountManager();
        UserAccountVault userAccountVault = UserAccountVault.getInstance();
        userAccountVault.setUserAccounts(new HashMap<LoginCredential, User>() {{
            put(new LoginCredential("000-0001", "password1"), new User());
        }});
        TestUtilities.setInput("000-0001" + System.getProperty("line.separator") + "password1");
        String feedback = option.execute(userAccountManager, null);
        assertEquals("Login successful.", feedback);
        assertNotNull(userAccountManager.getCurrentUser());
    }
}