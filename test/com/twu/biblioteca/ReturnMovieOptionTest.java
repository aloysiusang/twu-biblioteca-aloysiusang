package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class ReturnMovieOptionTest {
    private AllLibraryStores libraryStores;
    private LibraryMovieStore libraryMovieStore;
    private ArrayList<LibraryMovie> availableMovies;
    private UserAccountManagerStub userAccountManager;
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
        stubUser1 = new User("stubuser1", "stub1@user.com", "12345678");
        stubUser2 = new User("stubuser2", "stub2@user.com", "87654321");
        userAccountManager = new UserAccountManagerStub();
        TestUtilities.redirectOutput();
    }

    @After
    public void tearDown() throws Exception {
        TestUtilities.resetStreams();
    }

    @Test
    public void testReturnInvalidMovie() throws Exception {
        MainMenuOption option = new ReturnMovieOption();
        TestUtilities.setInput("Invalid Movie");
        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That is not a valid movie to return.", feedback);
    }

    @Test
    public void testCheckoutMovieWithValidUserReturnMovieWithInvalidUser() throws Exception {
        MainMenuOption option = new ReturnMovieOption();
        String titleToReturn = availableMovies.get(0).getName();
        MovieNameComparator comparator = new MovieNameComparator();
        checkoutMovie(stubUser1, titleToReturn, comparator);

        userAccountManager.setCurrentUser(null);
        TestUtilities.setInput(titleToReturn);
        option.execute(userAccountManager, libraryStores);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, libraryStores.getMovieStore().getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, libraryStores.getMovieStore().getCheckedOutResource()));
        User user = libraryStores.getMovieStore().getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(stubUser1, user);

    }

    @Test
    public void testCheckoutMovieAndReturnMovieWithAnotherUser() throws Exception {
        MainMenuOption option = new ReturnMovieOption();
        String titleToReturn = availableMovies.get(0).getName();
        MovieNameComparator comparator = new MovieNameComparator();
        checkoutMovie(stubUser1, titleToReturn, comparator);

        userAccountManager.setCurrentUser(stubUser2);
        TestUtilities.setInput(titleToReturn);
        option.execute(userAccountManager, libraryStores);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, libraryStores.getMovieStore().getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, libraryStores.getMovieStore().getCheckedOutResource()));
        User user = libraryStores.getMovieStore().getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(stubUser1, user);

    }

    @Test
    public void testCheckoutAndReturnMovieWithSameUser() throws Exception {
        MainMenuOption option = new ReturnMovieOption();
        MovieNameComparator comparator = new MovieNameComparator();
        String titleToReturn = availableMovies.get(0).getName();
        checkoutMovie(stubUser1, titleToReturn, comparator);


        TestUtilities.setInput(titleToReturn);
        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("Thank you for returning the movie.", feedback);
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, libraryStores.getMovieStore().getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, libraryStores.getMovieStore().getCheckedOutResource()));
        User user = libraryStores.getMovieStore().getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertNull(user);
    }

    @Test
    public void testReturnAvailableMovie() throws Exception {
        MainMenuOption option = new ReturnMovieOption();
        String titleToReturn = availableMovies.get(0).getName();
        TestUtilities.setInput(titleToReturn);
        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That is not a valid movie to return.", feedback);
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, libraryStores.getMovieStore().getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, libraryStores.getMovieStore().getCheckedOutResource()));
        User user = libraryStores.getMovieStore().getUserWhoCheckedOutResource(titleToReturn, new MovieNameComparator());
        assertNull(user);
    }

    private class UserAccountManagerStub extends UserAccountManager {

        private User currUser;
        public UserAccountManagerStub() {
            super(null);
            currUser = stubUser1;
        }

        @Override
        public User getCurrentUser() {
            return currUser;
        }

        public void setCurrentUser(User currUser) {
            this.currUser = currUser;
        }
    }

    private void checkoutMovie(User user, String titleToCheckout, Comparator comparator) {
        assertTrue(libraryStores.getMovieStore().checkoutResource(user, titleToCheckout, new MovieNameComparator()));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToCheckout, libraryStores.getMovieStore().getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToCheckout, libraryStores.getMovieStore().getCheckedOutResource()));
        User retrievedUser = libraryStores.getMovieStore().getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertEquals(user, retrievedUser);
    }


}