package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class Library {
    private static ArrayList<LibraryBook> allBooks;

    public Library() {
        allBooks = new ArrayList<LibraryBook>();
    }

    public Library(ArrayList<LibraryBook> expectedBooks) {
        allBooks = expectedBooks;
    }

    public ArrayList<LibraryBook> getAllBooks() {
        return allBooks;
    }
}
