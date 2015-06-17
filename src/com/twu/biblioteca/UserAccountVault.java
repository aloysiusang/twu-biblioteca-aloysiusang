package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserAccountVault {
    private static UserAccountVault instance = null;

    private static Map<LoginCredential, User> userAccounts;

    private UserAccountVault() {
        userAccounts = new HashMap<LoginCredential, User>();
    }

    public static UserAccountVault getInstance() {
        if(instance == null) {
            instance = new UserAccountVault();
        }
        return instance;
    }

    public static void setUserAccounts(Map<LoginCredential, User> userAccounts) {
        UserAccountVault.userAccounts = userAccounts;
    }

    public User retrieveUser(LoginCredential loginCredential) {
        return userAccounts.get(loginCredential);
    }

}
