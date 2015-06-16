package com.twu.biblioteca;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class CheckoutOption extends MainMenuOption{

    private static final String INPUT_TITLE = "Please enter the title of the book you wish to checkout: ";
    private static final String MESSAGE_CHECKOUT_SUCCESSFUL = "Thank you! Enjoy the book";
    private static final String MESSAGE_CHECKOUT_UNSUCCESSFUL = "That book is not available.";

    public CheckoutOption() {
        super("Checkout a book");
    }

    @Override
    public String execute(LibraryBookStore libraryBookStore) {
        System.out.print(INPUT_TITLE);
        String title = getUserInput();
        boolean success = libraryBookStore.checkoutBook(title);
        return success ? MESSAGE_CHECKOUT_SUCCESSFUL : MESSAGE_CHECKOUT_UNSUCCESSFUL;
    }
}
