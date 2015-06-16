package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class LibraryBookStore extends LibraryStore<LibraryBook> {

    public LibraryBookStore() {
        super();
    }

    public LibraryBookStore(ArrayList<LibraryBook> availableBooks) {
        super(availableBooks);
    }

    public LibraryBookStore(ArrayList<LibraryBook> availableBooks, ArrayList<LibraryBook> checkedOutBooks) {
        super(availableBooks, checkedOutBooks);
    }

}