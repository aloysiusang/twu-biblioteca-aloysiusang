package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class ReturnOption extends MainMenuOption{
    private static final String INPUT_TITLE = "Please enter the title of the book you wish to return: ";
    private static final String MESSAGE_RETURN_SUCCESSFUL = "Thank you for returning the book.";
    private static final String MESSAGE_RETURN_UNSUCCESSFUL = "That is not a valid book to return.";

    public ReturnOption() {
        super("Return a book");
    }

    @Override
    public String execute(Library library) {
        System.out.print(INPUT_TITLE);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String title = null;
        try {
            title = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean success = library.returnBook(title);
        return success ? MESSAGE_RETURN_SUCCESSFUL : MESSAGE_RETURN_UNSUCCESSFUL;
    }
}
