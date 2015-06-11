package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class LibraryTest {

    private ArrayList<LibraryBook> expectedBooks;

    @Before
    public void setUp() throws Exception {
        expectedBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};
    }

    @Test
    public void testGetAllBooksWhenThereAreNoBooks() throws Exception {
        Library lib = new Library();
        ArrayList<LibraryBook> books = lib.getAllBooks();
        assertEquals(0, books.size());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        Library lib = new Library(expectedBooks);
        ArrayList<LibraryBook> actualBooks = lib.getAllBooks();
        assertEquals(expectedBooks, actualBooks);
        assertEquals("Book 1", actualBooks.get(0).getTitle());
        assertEquals("Author 1", actualBooks.get(0).getAuthor());
        assertEquals(2001, actualBooks.get(0).getYearPublished());
    }
    
}