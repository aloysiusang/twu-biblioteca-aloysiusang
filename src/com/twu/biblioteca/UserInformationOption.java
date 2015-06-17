package com.twu.biblioteca;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserInformationOption extends MainMenuOption {
    public UserInformationOption() {
        super("User Information");
    }

    @Override
    public String execute(UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
        return userAccountManager.formatCurrentUser();
    }
}
