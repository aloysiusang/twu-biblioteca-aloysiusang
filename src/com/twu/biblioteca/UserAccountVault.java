package com.twu.biblioteca;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserAccountVault {
    private Map<LoginCredential, User> userAccounts;

    public UserAccountVault(Map<LoginCredential, User> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public User retrieveUser(LoginCredential loginCredential) {
        return userAccounts.get(loginCredential);
    }

}
