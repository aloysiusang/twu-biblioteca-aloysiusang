package com.twu.biblioteca;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserAccountManager {
    private final UserAccountVault userAccountVault;
    private User currentUser;

    public UserAccountManager(UserAccountVault userAccountVault) {
        this.userAccountVault = userAccountVault;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean login(LoginCredential loginCredential) {
        updateCurrentUser(loginCredential);
        return currentUser!=null;
    }

    public String formatCurrentUser() {
        if(currentUser==null)
            return "No user is logged in.";
        return currentUser.formatUserString();
    }

    private void updateCurrentUser(LoginCredential loginCredential) {
        currentUser = userAccountVault.retrieveUser(loginCredential);
    }
}
