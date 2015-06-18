package com.twu.biblioteca;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class ReturnBookOption extends MainMenuOption{
    private static final String INPUT_TITLE = "Please enter the title of the book you wish to return: ";
    private static final String MESSAGE_RETURN_SUCCESSFUL = "Thank you for returning the book.";
    private static final String MESSAGE_RETURN_UNSUCCESSFUL = "That is not a valid book to return.";

    public ReturnBookOption() {
        super("Return a book");
    }

    @Override
    public String execute(UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
        System.out.print(INPUT_TITLE);
        String title = getUserInput();
        boolean success = libraryStores.getBookStore().returnResource(userAccountManager.getCurrentUser(), title, new BookTitleComparator());
        return success ? MESSAGE_RETURN_SUCCESSFUL : MESSAGE_RETURN_UNSUCCESSFUL;
    }

}
