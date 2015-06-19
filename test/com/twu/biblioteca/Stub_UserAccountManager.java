package com.twu.biblioteca;

/**
 * Created by aloysiusang on 19/6/15.
 */
public class Stub_UserAccountManager extends UserAccountManager{
    private User currUser;

    public Stub_UserAccountManager() {
        super(null);
    }

    public Stub_UserAccountManager(User currUser) {
        super(null);
        this.currUser = currUser;
    }

    @Override
    public User getCurrentUser() {
        return currUser;
    }

    public void setCurrentUser(User currUser) {
        this.currUser = currUser;
    }

}
