package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class ReturnMovieOptionTest {
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
        TestUtilities.redirectOutput();
    }

    @After
    public void tearDown() throws Exception {
        TestUtilities.resetStreams();
    }

    @Test
    public void testReturnInvalidMovie() throws Exception {
        String invalidMovie = "Invalid Movie";
        ReturnMovieOption option = new ReturnMovieOption();
        TestUtilities.setInput(invalidMovie);
        String feedback = option.execute(null, libraryStores);
        assertEquals("That is not a valid movie to return.", feedback);
        assertFalse(TestUtilities.movieNameExistsInCollection(invalidMovie, libraryStores.getAvailableMovies()));
        assertFalse(TestUtilities.movieNameExistsInCollection(invalidMovie, libraryStores.getCheckedOutMovies()));
    }

    @Test
    public void testReturnMovie() throws Exception {
        String movieNameToReturn = checkedOutMovies.get(0).getName();
        ReturnMovieOption option = new ReturnMovieOption();
        TestUtilities.setInput(movieNameToReturn);
        String feedback = option.execute(null, libraryStores);
        assertEquals("Thank you for returning the movie.", feedback);
        assertTrue(TestUtilities.movieNameExistsInCollection(movieNameToReturn, libraryStores.getAvailableMovies()));
        assertFalse(TestUtilities.movieNameExistsInCollection(movieNameToReturn, libraryStores.getCheckedOutMovies()));
    }

    @Test
    public void testReturnAvailableMovie() throws Exception {
        String movieNameToReturn = availableMovies.get(0).getName();
        ReturnMovieOption option = new ReturnMovieOption();
        TestUtilities.setInput(movieNameToReturn);
        String feedback = option.execute(null, libraryStores);
        assertEquals("That is not a valid movie to return.", feedback);
        assertTrue(TestUtilities.movieNameExistsInCollection(movieNameToReturn, libraryStores.getAvailableMovies()));
        assertFalse(TestUtilities.movieNameExistsInCollection(movieNameToReturn, libraryStores.getCheckedOutMovies()));
    }

}