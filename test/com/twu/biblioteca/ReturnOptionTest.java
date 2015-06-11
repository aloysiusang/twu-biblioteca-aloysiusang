package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class ReturnOptionTest {
    private Library library;
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
        library = new Library(availableBooks, checkedOutBooks);
        redirectOutput(new PrintStream(new ByteArrayOutputStream()));
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(new FileInputStream(FileDescriptor.in));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void testReturnInvalidBook() throws Exception {
        MainMenuOption option = new ReturnOption();
        setInput("Invalid Book");
        String feedback = option.execute(library);
        assertEquals("That is not a valid book to return.", feedback);
    }

    @Test
    public void testReturnBook() throws Exception {
        MainMenuOption option = new ReturnOption();
        String titleToReturn = checkedOutBooks.get(0).getTitle();
        setInput(titleToReturn);
        String feedback = option.execute(library);
        assertEquals("Thank you for returning the book.", feedback);
        assertTrue(bookTitleExistsInCollection(titleToReturn, library.getAvailableBooks()));
        assertFalse(bookTitleExistsInCollection(titleToReturn, library.getCheckedOutBooks()));
    }

    @Test
    public void testReturnAvailableBook() throws Exception {
        MainMenuOption option = new ReturnOption();
        String titleToReturn = availableBooks.get(0).getTitle();
        setInput(titleToReturn);
        String feedback = option.execute(library);
        assertEquals("That is not a valid book to return.", feedback);
        assertTrue(bookTitleExistsInCollection(titleToReturn, library.getAvailableBooks()));
        assertFalse(bookTitleExistsInCollection(titleToReturn, library.getCheckedOutBooks()));
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