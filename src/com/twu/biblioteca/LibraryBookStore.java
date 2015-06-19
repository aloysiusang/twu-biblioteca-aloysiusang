package com.twu.biblioteca;

import java.util.List;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class LibraryBookStore extends LibraryStoreTemplate<LibraryBook> {

    public LibraryBookStore() {
        super();
    }

    public LibraryBookStore(List<LibraryBook> availableBooks) {
        super(availableBooks);
    }

    @Override
    public String availableResourceToString() {
        return LibraryBookFormatter.format(this.getAvailableResource());
    }
}