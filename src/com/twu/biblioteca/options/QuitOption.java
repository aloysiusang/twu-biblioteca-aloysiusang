package com.twu.biblioteca.options;

import com.twu.biblioteca.AllLibraryStores;
import com.twu.biblioteca.UserAccountManager;
import com.twu.biblioteca.options.MainMenuOption;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class QuitOption extends MainMenuOption {
    public QuitOption() {
        super("Quit");
    }

    @Override
    public String execute(UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
        return null;
    }
}
