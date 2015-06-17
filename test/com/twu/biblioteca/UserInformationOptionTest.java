package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserInformationOptionTest {
    private UserAccountManager userAccountManager;
    private UserAccountVault userAccountVault;
    private UserAccountVaultStub userAccountVaultStub;
    private Map<LoginCredential, User> userAccounts;

    @Before
    public void setUp() throws Exception {
        TestUtilities.redirectOutput();
        userAccounts = new HashMap<LoginCredential, User>() {{
            put(new LoginCredential("000-0001", "password1"), new User("user1", "user1@user1.com", "11111111"));
            put(new LoginCredential("000-0002", "password2"), new User("user2", "user2@user2.com", "22222222"));
            put(new LoginCredential("000-0003", "password3"), new User("user3", "user3@user3.com", "33333333"));
        }};
        userAccountVault = new UserAccountVault(userAccounts);
        userAccountVaultStub = new UserAccountVaultStub();
    }

    @After
    public void tearDown() throws Exception {
        TestUtilities.resetStreams();
    }

    @Test
    public void testGetUserInformationWithoutLogin() throws Exception {
        userAccountManager = new UserAccountManager(userAccountVaultStub);
        UserInformationOption option = new UserInformationOption();
        String feedback = option.execute(userAccountManager, null);
        assertEquals("No user is logged in.", feedback);
    }

    @Test
    public void testGetUserInformationWithStubbedVault() throws Exception {
        userAccountManager = new UserAccountManager(userAccountVaultStub);
        userAccountManager.login(null);
        UserInformationOption option = new UserInformationOption();
        String feedback = option.execute(userAccountManager, null);
        assertEquals("Name           :                      mockuser\n" +
                "Email          :                mock@email.com\n" +
                "Phone Number   :                       1234567\n", feedback);
    }

    @Test
    public void testGetUserInformationWithVault() throws Exception {
        userAccountManager = new UserAccountManager(userAccountVault);
        userAccountManager.login(new LoginCredential("000-0001", "password1"));
        UserInformationOption option = new UserInformationOption();
        String feedback = option.execute(userAccountManager, null);
        assertEquals("Name           :                         user1\n" +
                "Email          :               user1@user1.com\n" +
                "Phone Number   :                      11111111\n", feedback);
    }

    @Test
    public void testGetUserInformationWithLoginOption() throws Exception {
        userAccountManager = new UserAccountManager(userAccountVault);
        LoginOption loginOption = new LoginOption();
        TestUtilities.setInput("000-0003" + System.getProperty("line.separator") + "password3");
        String feedback = loginOption.execute(userAccountManager, null);
        assertEquals("Login successful.", feedback);
        UserInformationOption userInformationOption = new UserInformationOption();
        feedback = userInformationOption.execute(userAccountManager, null);
        assertEquals("Name           :                         user3\n" +
                "Email          :               user3@user3.com\n" +
                "Phone Number   :                      33333333\n", feedback);
    }

    private class UserAccountVaultStub extends UserAccountVault {
        public UserAccountVaultStub() {
            super(new HashMap<LoginCredential, User>());
        }

        @Override
        public User retrieveUser(LoginCredential loginCredential) {
            return new User("mockuser", "mock@email.com", "1234567");
        }
    }

}