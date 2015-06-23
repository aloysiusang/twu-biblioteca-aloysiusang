package com.twu.biblioteca;

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
