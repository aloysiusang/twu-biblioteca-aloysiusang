package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 12/6/15.
 */
public class LibraryMovieStoreTest {

    private ArrayList<LibraryMovie> expectedMovies;

    @Before
    public void setUp() throws Exception {
        expectedMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 1", 2001, "Director 1", 1));
            add(new LibraryMovie("Name 2", 2002, "Director 2", 2));
            add(new LibraryMovie("Name 3", 2003, "Director 3", 3));
        }};
    }

    @Test
    public void testGetAllMovies() throws Exception {
        LibraryMovieStore movieLib = new LibraryMovieStore(expectedMovies);
        assertEquals(expectedMovies, movieLib.getAvailableMovies());
    }

    @Test
    public void testCheckoutMovie() throws Exception {
        LibraryMovieStore movieLib = new LibraryMovieStore(expectedMovies);
        String nameToCheckout = expectedMovies.get(0).getName();
        boolean successfulCheckout = movieLib.checkoutMovie(nameToCheckout);
        assertTrue(successfulCheckout);
        assertFalse(movieNameExistsInCollection(nameToCheckout, movieLib.getAvailableMovies()));
        assertTrue(movieNameExistsInCollection(nameToCheckout, movieLib.getCheckedOutMovies()));
    }

    private boolean movieNameExistsInCollection(String name, Collection<LibraryMovie> collection) {
        for(LibraryMovie movie : collection) {
            if(movie.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}