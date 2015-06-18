package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

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
        assertEquals(expectedMovies, movieLib.getAvailableResource());
    }

    @Test
    public void testCheckoutMovie() throws Exception {
        LibraryMovieStore movieStore = new LibraryMovieStore(expectedMovies);
        String nameToCheckout = expectedMovies.get(0).getName();
        User user = new User("user2", "user2@user2.com", "22222222");
        boolean successfulCheckout = movieStore.checkoutResource(user, nameToCheckout, new MovieNameComparator());
        assertTrue(successfulCheckout);
        assertFalse(TestUtilities.movieNameExistsInCollection(nameToCheckout, movieStore.getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(nameToCheckout, movieStore.getCheckedOutResource()));
        //TODO: check out movie with user
//        assertEquals(user, movieStore.getUserWhoCheckedOutResource(nameToCheckout, new MovieNameComparator()));
    }

    @Test
    public void testCheckoutAndReturnMovie() throws Exception {
        LibraryMovieStore movieStore = new LibraryMovieStore(expectedMovies);
        String titleToReturn = expectedMovies.get(0).getName();
        //TODO: added user here for green. check test case again
        User user = new User("user1", "user1@user1.com", "11111111");
        boolean successfulCheckout = movieStore.checkoutResource(user, titleToReturn, new MovieNameComparator());
        assertTrue(successfulCheckout);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, movieStore.getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, movieStore.getCheckedOutResource()));

        boolean successfulReturn = movieStore.returnResource(user, titleToReturn, new MovieNameComparator());
        assertTrue(successfulReturn);
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, movieStore.getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, movieStore.getCheckedOutResource()));
    }
}