package com.twu.biblioteca;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class CheckOutMovieOption extends MainMenuOption {
    private static final String INPUT_TITLE = "Please enter the name of the movie you wish to checkout: ";
    private static final String MESSAGE_CHECKOUT_SUCCESSFUL = "Thank you! Enjoy the movie";
    private static final String MESSAGE_CHECKOUT_UNSUCCESSFUL = "That movie is not available.";

    public CheckOutMovieOption() {
        super("Checkout a movie");
    }

    @Override
    public String execute(AllLibraryStores libraryStores) {
        System.out.println(INPUT_TITLE);
        String title = getUserInput();
        boolean success = libraryStores.checkoutMovie(title, new MovieNameComparator());
        return success ? MESSAGE_CHECKOUT_SUCCESSFUL : MESSAGE_CHECKOUT_UNSUCCESSFUL;
    }
}
