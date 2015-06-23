package com.twu.biblioteca.options;

import com.twu.biblioteca.AllLibraryStores;
import com.twu.biblioteca.UserAccountManager;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class ListBooksOption extends MainMenuOption {
    public ListBooksOption() {
        super("List Books");
    }

    @Override
    public String execute(UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
        return libraryStores.getBookStore().availableResourceToString();
    }
}
