package com.twu.biblioteca.libstores;

import com.twu.biblioteca.libresources.LibraryBook;
import com.twu.biblioteca.LibraryBookFormatter;

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