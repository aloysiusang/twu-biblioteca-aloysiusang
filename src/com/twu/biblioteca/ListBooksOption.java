package com.twu.biblioteca;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class ListBooksOption extends MainMenuOption {
    public ListBooksOption() {
        super("List Books");
    }

    @Override
    public String execute(AllLibraryStores libraryBookStore) {
        return LibraryBookFormatter.format(libraryBookStore.getAvailableBooks());
    }
}
