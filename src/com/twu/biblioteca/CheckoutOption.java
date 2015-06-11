package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class CheckoutOption extends MainMenuOption{

    public static final String INPUT_TITLE = "Please enter the title of the book you wish to checkout: ";
    public static final String MESSAGE_CHECKOUT_SUCCESSFUL = "Thank you! Enjoy the book";
    public static final String MESSAGE_CHECKOUT_UNSUCCESSFUL = "That book is not available.";

    public CheckoutOption() {
        super("Checkout a book");
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
        boolean success = library.checkoutBook(title);
        return success ? MESSAGE_CHECKOUT_SUCCESSFUL : MESSAGE_CHECKOUT_UNSUCCESSFUL;
    }
}
