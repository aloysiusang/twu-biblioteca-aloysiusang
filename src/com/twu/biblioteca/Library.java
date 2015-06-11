package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class Library {
    private static ArrayList<LibraryBook> availableBooks;
    private static ArrayList<LibraryBook> checkedOutBooks;

    public Library() {
        availableBooks = new ArrayList<LibraryBook>();
        checkedOutBooks = new ArrayList<LibraryBook>();
    }

    public Library(ArrayList<LibraryBook> expectedBooks) {
        availableBooks = expectedBooks;
        checkedOutBooks = new ArrayList<LibraryBook>();
    }

    public Library(ArrayList<LibraryBook> availableBooks, ArrayList<LibraryBook> checkedOutBooks) {
        Library.availableBooks = availableBooks;
        Library.checkedOutBooks = checkedOutBooks;
    }

    public ArrayList<LibraryBook> getAvailableBooks() {
        return availableBooks;
    }

    public ArrayList<LibraryBook> getCheckedOutBooks() {
        return checkedOutBooks;
    }

    public boolean checkoutBook(String title) {
        return moveBook(title, availableBooks, checkedOutBooks);
    }

    public boolean returnBook(String title) {
        return moveBook(title, checkedOutBooks, availableBooks);
    }

    private boolean moveBook(String title, ArrayList<LibraryBook> from, ArrayList<LibraryBook> to) {
        boolean isMoved = false;
        for(Iterator<LibraryBook> iterator = from.iterator(); iterator.hasNext();) {
            LibraryBook book = iterator.next();
            if(book.getTitle().equals(title)) {
                to.add(book);
                iterator.remove();
                isMoved = true;
                break;
            }
        }
        return isMoved;
    }

}
