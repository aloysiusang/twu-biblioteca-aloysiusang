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
public class CheckOutMovieOptionTest {
    private AllLibraryStores libraryStores;
    private LibraryMovieStore libraryMovieStore;
    private ArrayList<LibraryMovie> availableMovies;
    private ArrayList<LibraryMovie> checkedOutMovies;
    private User stubUser;

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
        stubUser = new User("stubuser", "stub@email.com", "11111111");
        TestUtilities.redirectOutput();
    }

    @After
    public void tearDown() throws Exception {
        TestUtilities.resetStreams();
    }

    @Test
    public void testCheckOutInvalidMovie() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        String invalidMovieName = "Invalid Movie";
        TestUtilities.setInput(invalidMovieName);
        String feedback = option.execute(new UserAccountManagerValidStub(), libraryStores);
        assertEquals("That movie is not available.", feedback);
        assertFalse(movieNameExistsInCollection(invalidMovieName, libraryStores.getAvailableMovies()));
        assertFalse(movieNameExistsInCollection(invalidMovieName, libraryStores.getCheckedOutMovies()));
    }

    @Test
    public void testCheckoutMovie() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        String movieName = availableMovies.get(0).getName();
        TestUtilities.setInput(movieName);
        String feedback = option.execute(new UserAccountManagerValidStub(), libraryStores);
        assertEquals("Thank you! Enjoy the movie", feedback);
        assertFalse(movieNameExistsInCollection(movieName, libraryStores.getAvailableMovies()));
        assertTrue(movieNameExistsInCollection(movieName, libraryStores.getCheckedOutMovies()));
        //TODO: check for user who checked out movie
    }

    @Test
    public void testCheckoutMovieThatHasAlreadyBeenCheckedOut() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        String movieName = checkedOutMovies.get(0).getName();
        TestUtilities.setInput(movieName);
        String feedback = option.execute(new UserAccountManagerValidStub(), libraryStores);
        assertEquals("That movie is not available.", feedback);
        assertFalse(movieNameExistsInCollection(movieName, libraryStores.getAvailableMovies()));
        assertTrue(movieNameExistsInCollection(movieName, libraryStores.getCheckedOutMovies()));
        //TODO:  user1 checks out movie, user2 checks out same movie and fails => check that movie is still recorded to be checked out by user1
    }

    private boolean movieNameExistsInCollection(String name, Collection<LibraryMovie> collection) {
        for (LibraryMovie movie : collection) {
            if (movie.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private class UserAccountManagerValidStub extends UserAccountManager {

        public UserAccountManagerValidStub() {
            super(null);
        }

        @Override
        public User getCurrentUser() {
            return stubUser;
        }
    }

    //TODO: check out with invalid user
    private class UserAccountManagerInvalidStub extends UserAccountManager {

        public UserAccountManagerInvalidStub() {
            super(null);
        }

        @Override
        public User getCurrentUser() {
            return null;
        }
    }

}