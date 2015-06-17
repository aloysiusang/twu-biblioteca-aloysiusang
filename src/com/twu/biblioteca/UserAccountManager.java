package com.twu.biblioteca;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserAccountManager {
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean login(LoginCredential loginCredential) {
        UserAccountVault userAccountVault = UserAccountVault.getInstance();
        currentUser = userAccountVault.retrieveUser(loginCredential);
        return currentUser != null;
    }

}
