package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class CheckoutOptionTest {


    private LibraryBookStore libraryBookStore;
    private ArrayList<LibraryBook> availableBooks;
    private ArrayList<LibraryBook> checkedOutBooks;

    @Before
    public void setUp() throws Exception {
        availableBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};
        checkedOutBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 11", "Author 11", 2011));
            add(new LibraryBook("Book 12", "Author 12", 2012));
            add(new LibraryBook("Book 13", "Author 13", 2013));
        }};
        libraryBookStore = new LibraryBookStore(availableBooks, checkedOutBooks);
        redirectOutput(new PrintStream(new ByteArrayOutputStream()));
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(new FileInputStream(FileDescriptor.in));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void testCheckoutBook() throws Exception {
        MainMenuOption option = new CheckoutOption();
        String titleToCheckout = availableBooks.get(0).getTitle();

        setInput(titleToCheckout);
        String feedback = option.execute(libraryBookStore);

        assertEquals("Thank you! Enjoy the book", feedback);
        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryBookStore.getAvailableResource()));
        assertTrue(bookTitleExistsInCollection(titleToCheckout, libraryBookStore.getCheckedOutResource()));
    }

    @Test
    public void testCheckoutInvalidBook() throws Exception {
        MainMenuOption option = new CheckoutOption();
        String titleToCheckout = "Invalid Book";

        setInput(titleToCheckout);
        String feedback = option.execute(libraryBookStore);
        assertEquals("That book is not available.", feedback);

        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryBookStore.getAvailableResource()));
    }

    @Test
    public void testCheckoutBookThatHasAlreadyBeenCheckedOut() throws Exception {
        MainMenuOption option = new CheckoutOption();
        String titleToCheckOut = checkedOutBooks.get(0).getTitle();
        setInput(titleToCheckOut);
        String feedback = option.execute(libraryBookStore);
        assertEquals("That book is not available.", feedback);
        assertFalse(bookTitleExistsInCollection(titleToCheckOut, libraryBookStore.getAvailableResource()));
    }

    private boolean bookTitleExistsInCollection(String title, Collection<LibraryBook> collection) {
        for(LibraryBook book : collection) {
            if(book.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    private void redirectOutput(PrintStream printStream) {
        System.setOut(printStream);
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }
}