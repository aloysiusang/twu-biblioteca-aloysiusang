package com.twu.biblioteca;

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
    public String execute(AllLibraryStores libraryStores) {
        System.out.println(INPUT_TITLE);
        String name = getUserInput();
        boolean success = libraryStores.returnMovie(name, new MovieNameComparator());
        return success ? MESSAGE_RETURN_SUCCESSFUL : MESSAGE_RETURN_UNSUCCESSFUL;
    }
}
