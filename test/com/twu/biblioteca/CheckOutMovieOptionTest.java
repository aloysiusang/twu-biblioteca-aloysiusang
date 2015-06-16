package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class CheckOutMovieOptionTest {
    private AllLibraryStores libraryStores;
    private LibraryMovieStore libraryMovieStore;
    private ArrayList<LibraryMovie> availableMovies;
    private ArrayList<LibraryMovie> checkedOutMovies;

    @Before
    public void setUp() throws Exception {
        availableMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 1", 2001, "Director 1", 1));
            add(new LibraryMovie("Name 2", 2002, "Director 2", 2));
            add(new LibraryMovie("Name 3", 2003, "Director 3", 3));
        }};
        checkedOutMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 4", 2004, "Director 4", 4));
            add(new LibraryMovie("Name 5", 2005, "Director 5", 5));
            add(new LibraryMovie("Name 6", 2006, "Director 6", 6));
        }};
        libraryMovieStore = new LibraryMovieStore(availableMovies, checkedOutMovies);
        libraryStores = new AllLibraryStores(libraryMovieStore);
        redirectOutput(new PrintStream(new ByteArrayOutputStream()));
    }

    @After
    public void tearDown() throws Exception {
        System.setIn(new FileInputStream(FileDescriptor.in));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    public void testCheckOutInvalidMovie() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        String invalidMovieName = "Invalid Movie";
        setInput(invalidMovieName);
        String feedback = option.execute(libraryStores);
        assertEquals("That movie is not available.", feedback);
        assertFalse(movieNameExistsInCollection(invalidMovieName, libraryStores.getAvailableMovies()));
        assertFalse(movieNameExistsInCollection(invalidMovieName, libraryStores.getCheckedOutMovies()));
    }

    @Test
    public void testCheckoutMovie() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        String movieName = availableMovies.get(0).getName();
        setInput(movieName);
        String feedback = option.execute(libraryStores);
        assertEquals("Thank you! Enjoy the movie", feedback);
        assertFalse(movieNameExistsInCollection(movieName, libraryStores.getAvailableMovies()));
        assertTrue(movieNameExistsInCollection(movieName, libraryStores.getCheckedOutMovies()));
    }

    @Test
    public void testCheckoutMovieThatHasAlreadyBeenCheckedOut() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        String movieName = checkedOutMovies.get(0).getName();
        setInput(movieName);
        String feedback = option.execute(libraryStores);
        assertEquals("That movie is not available.", feedback);
        assertFalse(movieNameExistsInCollection(movieName, libraryStores.getAvailableMovies()));
        assertTrue(movieNameExistsInCollection(movieName, libraryStores.getCheckedOutMovies()));
    }

    private boolean movieNameExistsInCollection(String name, Collection<LibraryMovie> collection) {
        for(LibraryMovie movie : collection) {
            if(movie.getName().equals(name)) {
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