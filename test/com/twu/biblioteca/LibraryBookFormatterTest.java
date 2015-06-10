package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class LibraryBookFormatterTest {
    @Test
    public void testFormatBooksToString() throws Exception {
        ArrayList<LibraryBook> expectedBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};

        String formattedBooks = LibraryBookFormatter.format(expectedBooks);
        assertEquals("          TITLE||         AUTHOR||  YEAR\n" +
                "         Book 1||       Author 1||  2001\n" +
                "         Book 2||       Author 2||  2002\n" +
                "         Book 3||       Author 3||  2003", formattedBooks);
    }
}