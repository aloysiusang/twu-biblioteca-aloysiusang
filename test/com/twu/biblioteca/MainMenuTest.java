package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenuTest {
    private Library library;
    private ArrayList<LibraryBook> expectedBooks;

    @Before
    public void setUp() throws Exception {
        expectedBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};
        library = new Library(expectedBooks);
    }

    @Test
    public void testGetMainMenuOptions() throws Exception {
        MainMenu mainMenu = new MainMenu();
        ArrayList<String> options = mainMenu.getOptions();
        assertEquals(1, options.size());
        assertEquals("List Books", options.get(0));
    }

    @Test
    public void testSelectListBooks() throws Exception {
        MainMenu mainMenu = new MainMenu();
        String feedback = mainMenu.selectOption(0, library);
        assertEquals("          TITLE||         AUTHOR||  YEAR\n" +
                "         Book 1||       Author 1||  2001\n" +
                "         Book 2||       Author 2||  2002\n" +
                "         Book 3||       Author 3||  2003", feedback);
    }

}