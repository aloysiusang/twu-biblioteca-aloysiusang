package com.twu.biblioteca.options;

import com.twu.biblioteca.libstores.AllLibraryStores;
import com.twu.biblioteca.BookTitleComparator;
import com.twu.biblioteca.UserAccountManager;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class CheckOutBookOption extends MainMenuOption{

    private static final String INPUT_TITLE = "Please enter the title of the book you wish to checkout: ";
    private static final String MESSAGE_CHECKOUT_SUCCESSFUL = "Thank you! Enjoy the book";
    private static final String MESSAGE_CHECKOUT_UNSUCCESSFUL = "That book is not available.";

    public CheckOutBookOption() {
        super("Checkout a book");
    }

    @Override
    public String execute(UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
        System.out.print(INPUT_TITLE);
        String title = getUserInput();
        boolean success = libraryStores.getBookStore().checkoutResource(userAccountManager.getCurrentUser(), title, new BookTitleComparator());
        return success ? MESSAGE_CHECKOUT_SUCCESSFUL : MESSAGE_CHECKOUT_UNSUCCESSFUL;
    }
}
