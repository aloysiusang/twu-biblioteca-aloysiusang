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
        ArrayList<MainMenuOption> options = mainMenu.getOptions();
        assertEquals(2, options.size());
        assertEquals("List Books", options.get(0).getName());
    }

    @Test
    public void testSelectListBooks() throws Exception {
        MainMenu mainMenu = new MainMenu();
        String feedback = mainMenu.selectOption(1, library);
        assertEquals("          TITLE||         AUTHOR||  YEAR\n" +
                "         Book 1||       Author 1||  2001\n" +
                "         Book 2||       Author 2||  2002\n" +
                "         Book 3||       Author 3||  2003", feedback);
    }

    @Test
    public void testSelectInvalidOption() throws Exception {
        ArrayList<MainMenuOption> options = new ArrayList<MainMenuOption>() {{
            add(new ListBooksOption());
            add(new QuitOption());
        }};
        MainMenu menu = new MainMenu(options);
        String feedback = menu.selectOption(0, library);
        assertEquals("Select a valid option!", feedback);
    }

    @Test
    public void testQuit() throws Exception {
        boolean hasExecuted = false;
        String feedback = "";
        MainMenu menu = new MainMenu();
        ArrayList<MainMenuOption> options = menu.getOptions();
        for(int i = 0; i < options.size(); i++) {
            if(options.get(i).getClass() == QuitOption.class) {
                feedback = menu.selectOption(i+1, library); //+1 for indexing
                hasExecuted = true;
            }
        }
        assertTrue(hasExecuted);
        assertEquals(null, feedback);
    }

}