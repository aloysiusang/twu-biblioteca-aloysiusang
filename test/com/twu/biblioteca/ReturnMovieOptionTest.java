package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class ReturnMovieOptionTest {
    private AllLibraryStores libraryStores;
    private LibraryMovieStore libraryMovieStore;
    private ArrayList<LibraryMovie> availableMovies;
    private UserAccountManagerStub userAccountManager;
    private User stubUser;

    @Before
    public void setUp() throws Exception {
        availableMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 1", 2001, "Director 1", 1));
            add(new LibraryMovie("Name 2", 2002, "Director 2", 2));
            add(new LibraryMovie("Name 3", 2003, "Director 3", 3));
        }};
        libraryMovieStore = new LibraryMovieStore(availableMovies);
        libraryStores = new AllLibraryStores(libraryMovieStore);
        stubUser = new User("stubuser", "stub@email.com", "12345678");
        userAccountManager = new UserAccountManagerStub();
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
        userAccountManager.setCurrentUser(stubUser);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That is not a valid movie to return.", feedback);
        assertFalse(TestUtilities.movieNameExistsInCollection(invalidMovie, libraryStores.getAvailableMovies()));
        assertFalse(TestUtilities.movieNameExistsInCollection(invalidMovie, libraryStores.getCheckedOutMovies()));
    }

    @Test
    public void testCheckoutReturnMovie() throws Exception {
        String movieNameToReturn = availableMovies.get(0).getName();
        ReturnMovieOption option = new ReturnMovieOption();
        TestUtilities.setInput(movieNameToReturn);

        //TODO: do more tests for return, return same movie twice, etc
        libraryStores.checkoutMovie(stubUser, movieNameToReturn, new MovieNameComparator());
        userAccountManager.setCurrentUser(stubUser);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("Thank you for returning the movie.", feedback);
        assertTrue(TestUtilities.movieNameExistsInCollection(movieNameToReturn, libraryStores.getAvailableMovies()));
        assertFalse(TestUtilities.movieNameExistsInCollection(movieNameToReturn, libraryStores.getCheckedOutMovies()));
    }

    @Test
    public void testReturnAvailableMovie() throws Exception {
        String movieNameToReturn = availableMovies.get(0).getName();
        ReturnMovieOption option = new ReturnMovieOption();
        TestUtilities.setInput(movieNameToReturn);
        userAccountManager.setCurrentUser(stubUser);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That is not a valid movie to return.", feedback);
        assertTrue(TestUtilities.movieNameExistsInCollection(movieNameToReturn, libraryStores.getAvailableMovies()));
        assertFalse(TestUtilities.movieNameExistsInCollection(movieNameToReturn, libraryStores.getCheckedOutMovies()));
    }

    private class UserAccountManagerStub extends UserAccountManager {

        private User currUser;

        public UserAccountManagerStub() {
            super(null);
            this.currUser = stubUser;
        }

        @Override
        public User getCurrentUser() {
            return currUser;
        }

        public void setCurrentUser(User currUser) {
            this.currUser = currUser;
        }
    }

    //TODO: test with invalid user

}