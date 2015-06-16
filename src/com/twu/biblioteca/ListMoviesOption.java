package com.twu.biblioteca;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class ListMoviesOption extends MainMenuOption {
    public ListMoviesOption() {
        super("List Movies");
    }

    @Override
    public String execute(AllLibraryStores libraryStores) {
        return LibraryMovieFormatter.format(libraryStores.getAvailableMovies());
    }
}