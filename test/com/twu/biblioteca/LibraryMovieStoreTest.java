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
    private ArrayList<LibraryMovie> checkedOutMovies;

    @Before
    public void setUp() throws Exception {
        expectedMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 1", 2001, "Director 1", 1));
            add(new LibraryMovie("Name 2", 2002, "Director 2", 2));
            add(new LibraryMovie("Name 3", 2003, "Director 3", 3));
        }};

        checkedOutMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 4", 2004, "Director 4", 4));
            add(new LibraryMovie("Name 5", 2005, "Director 5", 5));
            add(new LibraryMovie("Name 6", 2006, "Director 6", 6));
        }};
    }

    @Test
    public void testGetAllMovies() throws Exception {
        LibraryMovieStore movieLib = new LibraryMovieStore(expectedMovies);
        assertEquals(expectedMovies, movieLib.getAvailableResource());
    }

    @Test
    public void testCheckoutMovie() throws Exception {
        LibraryMovieStore movieStore = new LibraryMovieStore(expectedMovies);
        String nameToCheckout = expectedMovies.get(0).getName();
        boolean successfulCheckout = movieStore.checkoutResource(nameToCheckout, new MovieNameComparator());
        assertTrue(successfulCheckout);
        assertFalse(movieNameExistsInCollection(nameToCheckout, movieStore.getAvailableResource()));
        assertTrue(movieNameExistsInCollection(nameToCheckout, movieStore.getCheckedOutResource()));
    }

    @Test
    public void testReturnMovie() throws Exception {
        LibraryMovieStore movieStore = new LibraryMovieStore(expectedMovies, checkedOutMovies);
        String titleToReturn = checkedOutMovies.get(0).getName();
        boolean successfulCheckout = movieStore.returnResource(titleToReturn, new MovieNameComparator());
        assertTrue(successfulCheckout);
        assertTrue(movieNameExistsInCollection(titleToReturn, movieStore.getAvailableResource()));
        assertFalse(movieNameExistsInCollection(titleToReturn, movieStore.getCheckedOutResource()));
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