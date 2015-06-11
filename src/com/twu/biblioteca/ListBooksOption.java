package com.twu.biblioteca;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class ListBooksOption extends MainMenuOption {
    public ListBooksOption() {
        super("List Books");
    }

    @Override
    public String execute(Library library) {
        return LibraryBookFormatter.format(library.getAllBooks());
    }
}
