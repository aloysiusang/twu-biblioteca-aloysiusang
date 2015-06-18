package com.twu.biblioteca;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserAccountManager {
    private final UserAccountVault userAccountVault;
    private LoginCredential loginCredential;
    private User currentUser;

    public UserAccountManager(UserAccountVault userAccountVault) {
        this.userAccountVault = userAccountVault;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean login(LoginCredential loginCredential) {
        updateCurrentUser(loginCredential);
        updateLoginCredential(loginCredential);
        return currentUser!=null;
    }

    private void updateCurrentUser(LoginCredential loginCredential) {
        currentUser = userAccountVault.retrieveUser(loginCredential);
    }

    private void updateLoginCredential(LoginCredential loginCredential) {
        if(currentUser!=null)
            this.loginCredential = loginCredential;
        else
            this.loginCredential = null;
    }

    public String formatCurrentUser() {
        if(currentUser==null)
            return "No user is logged in.";
        return currentUser.formatUserString();
    }
}
