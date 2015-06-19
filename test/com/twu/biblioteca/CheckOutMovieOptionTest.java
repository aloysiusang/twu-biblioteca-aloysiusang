package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class CheckOutMovieOptionTest {
    private AllLibraryStores libraryStores;
    private LibraryMovieStore libraryMovieStore;
    private ArrayList<LibraryMovie> availableMovies;
    private Stub_UserAccountManager userAccountManager;
    private User stubUser1;
    private User stubUser2;

    @Before
    public void setUp() throws Exception {
        availableMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 1", 2001, "Director 1", 1));
            add(new LibraryMovie("Name 2", 2002, "Director 2", 2));
            add(new LibraryMovie("Name 3", 2003, "Director 3", 3));
        }};
        libraryMovieStore = new LibraryMovieStore(availableMovies);
        libraryStores = new AllLibraryStores(libraryMovieStore);
        stubUser1 = new User("stubuser1", "stub1@email.com", "11111111");
        stubUser2 = new User("stubuser2", "stub2@email.com", "22222222");
        userAccountManager = new Stub_UserAccountManager();
        TestUtilities.redirectOutput();
    }

    @After
    public void tearDown() throws Exception {
        TestUtilities.resetStreams();
    }

    @Test
    public void testCheckoutMovieWithoutUser() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        String movieName = availableMovies.get(availableMovies.size() - 1).getName();
        userAccountManager.setCurrentUser(null);
        TestUtilities.setInput(movieName);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That movie is not available.", feedback);
        assertTrue(TestUtilities.movieNameExistsInCollection(movieName, libraryStores.getMovieStore().getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(movieName, libraryStores.getMovieStore().getCheckedOutResource()));
    }

    @Test
    public void testCheckOutInvalidMovie() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        String invalidMovieName = "Invalid Movie";
        TestUtilities.setInput(invalidMovieName);
        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That movie is not available.", feedback);
        assertFalse(TestUtilities.movieNameExistsInCollection(invalidMovieName, libraryStores.getMovieStore().getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(invalidMovieName, libraryStores.getMovieStore().getCheckedOutResource()));
    }

    @Test
    public void testCheckoutMovie() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        String movieName = availableMovies.get(0).getName();
        TestUtilities.setInput(movieName);
        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("Thank you! Enjoy the movie", feedback);
        assertFalse(TestUtilities.movieNameExistsInCollection(movieName, libraryStores.getMovieStore().getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(movieName, libraryStores.getMovieStore().getCheckedOutResource()));

        User user = libraryStores.getMovieStore().getUserWhoCheckedOutResource(movieName, new MovieNameComparator());
        assertEquals(stubUser1, user);
    }

    @Test
    public void testCheckoutMovieThatHasAlreadyBeenCheckedOut() throws Exception {
        CheckOutMovieOption option = new CheckOutMovieOption();
        userAccountManager.setCurrentUser(stubUser1);
        String movieName = availableMovies.get(0).getName();

        TestUtilities.setInput(movieName);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("Thank you! Enjoy the movie", feedback);
        assertFalse(TestUtilities.movieNameExistsInCollection(movieName, libraryStores.getMovieStore().getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(movieName, libraryStores.getMovieStore().getCheckedOutResource()));

        userAccountManager.setCurrentUser(stubUser2);
        TestUtilities.setInput(movieName);
        feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That movie is not available.", feedback);
        assertFalse(TestUtilities.movieNameExistsInCollection(movieName, libraryStores.getMovieStore().getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(movieName, libraryStores.getMovieStore().getCheckedOutResource()));

        User user = libraryStores.getMovieStore().getUserWhoCheckedOutResource(movieName, new MovieNameComparator());
        assertEquals(stubUser1, user);
    }

}