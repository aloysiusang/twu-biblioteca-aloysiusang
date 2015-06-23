package com.twu.biblioteca.options;

import com.twu.biblioteca.AllLibraryStores;
import com.twu.biblioteca.UserAccountManager;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class ListMoviesOption extends MainMenuOption {
    public ListMoviesOption() {
        super("List Movies");
    }

    @Override
    public String execute(UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
        return libraryStores.getMovieStore().availableResourceToString();
    }
}
