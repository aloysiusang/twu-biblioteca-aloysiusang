package com.twu.biblioteca.options;

import com.twu.biblioteca.libstores.AllLibraryStores;
import com.twu.biblioteca.UserAccountManager;

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
