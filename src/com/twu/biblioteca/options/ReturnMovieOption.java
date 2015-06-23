package com.twu.biblioteca.options;

import com.twu.biblioteca.AllLibraryStores;
import com.twu.biblioteca.MovieNameComparator;
import com.twu.biblioteca.UserAccountManager;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class ReturnMovieOption extends MainMenuOption {
    private static final String INPUT_TITLE = "Please enter the name of the movie you wish to return: ";
    private static final String MESSAGE_RETURN_SUCCESSFUL = "Thank you for returning the movie.";
    private static final String MESSAGE_RETURN_UNSUCCESSFUL = "That is not a valid movie to return.";

    public ReturnMovieOption() {
        super("Return a movie");
    }

    @Override
    public String execute(UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
        System.out.println(INPUT_TITLE);
        String name = getUserInput();
        boolean success = libraryStores.getMovieStore().returnResource(userAccountManager.getCurrentUser(), name, new MovieNameComparator());
        return success ? MESSAGE_RETURN_SUCCESSFUL : MESSAGE_RETURN_UNSUCCESSFUL;
    }
}
