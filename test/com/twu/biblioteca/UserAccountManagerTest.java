package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserAccountManagerTest {
    private LoginCredential validLoginCredential;
    private LoginCredential invalidLoginCredential;
    private User validUser;

    @Before
    public void setUp() throws Exception {
        validLoginCredential = new LoginCredential("000-0001", "password1");
        invalidLoginCredential = new LoginCredential("999-9999", "invalidpassword");
        validUser = new User("user1", "user1@user1.com", "11111111");
    }

    @Test
    public void testLoginWithStubVault() throws Exception {
        UserAccountManager userAccountManager = new UserAccountManager(new UserAccountVaultStub());
        userAccountManager.login(validLoginCredential);
        assertEquals(validUser, userAccountManager.getCurrentUser());
    }

    @Test
    public void testLoginWithWrongCredentialsWithStubVault() throws Exception {
        UserAccountManager userAccountManager = new UserAccountManager(new UserAccountVaultStub());
        userAccountManager.login(invalidLoginCredential);
        assertNull(userAccountManager.getCurrentUser());
    }

    private class UserAccountVaultStub extends UserAccountVault {
        public UserAccountVaultStub() {
            super(new HashMap<LoginCredential, User>());
        }

        @Override
        public User retrieveUser(LoginCredential loginCredential) {
            if(loginCredential.equals(validLoginCredential)) {
                return validUser;
            }
            else {
                return null;
            }
        }
    }
}