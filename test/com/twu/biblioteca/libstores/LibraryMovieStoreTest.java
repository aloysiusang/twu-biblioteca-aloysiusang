package com.twu.biblioteca.libstores;

import com.twu.biblioteca.libresources.LibraryMovie;
import com.twu.biblioteca.MovieNameComparator;
import com.twu.biblioteca.TestUtilities;
import com.twu.biblioteca.User;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
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
    public void testGetAllMoviesWhenThereAreNoMovies() throws Exception {
        LibraryMovieStore lib = new LibraryMovieStore();
        List<LibraryMovie> movies = lib.getAvailableResource();
        assertEquals(0, movies.size());
    }

    @Test
    public void testGetAllMovies() throws Exception {
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        List<LibraryMovie> actualMovies = lib.getAvailableResource();
        assertEquals(expectedMovies, actualMovies);
        assertEquals("Name 1", actualMovies.get(0).getName());
        assertEquals(2001, actualMovies.get(0).getYear());
        assertEquals("Director 1", actualMovies.get(0).getDirector());
        assertEquals(1, actualMovies.get(0).getRating());
    }

    @Test
    public void testCheckoutMovieWithNoUser() throws Exception {
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        String titleToCheckout = expectedMovies.get(0).getName();
        MovieNameComparator comparator = new MovieNameComparator();
        boolean successfulCheckout = lib.checkoutResource(null, titleToCheckout, comparator);
        assertFalse(successfulCheckout);
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testCheckoutMovieWithUser() throws Exception {
        User user = new User("user1", "user1@user1.com", "11111111");
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        String titleToCheckout = expectedMovies.get(expectedMovies.size()-1).getName();
        MovieNameComparator comparator = new MovieNameComparator();
        boolean successfulCheckout = lib.checkoutResource(user, titleToCheckout, comparator);
        assertTrue(successfulCheckout);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertEquals(user, retrievedUser);
    }

    @Test
    public void testCheckoutMovieThatHasBeenCheckedOut() throws Exception {
        User firstUser = new User("user1", "user1@user1.com", "11111111");
        User secondUser = new User("user2", "user2@user2.com", "22222222");
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        String titleToCheckout = expectedMovies.get(0).getName();
        MovieNameComparator comparator = new MovieNameComparator();
        boolean successfulCheckout = lib.checkoutResource(firstUser, titleToCheckout, comparator);
        assertTrue(successfulCheckout);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertEquals(firstUser, retrievedUser);

        successfulCheckout = lib.checkoutResource(secondUser, titleToCheckout, comparator);
        assertFalse(successfulCheckout);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertEquals(firstUser, retrievedUser);
    }

    @Test
    public void testCheckoutInvalidMovie() throws Exception {
        User user = new User("user1", "user1@user1.com", "11111111");
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        String titleToCheckout = "invalid movie";
        MovieNameComparator comparator = new MovieNameComparator();
        boolean successfulCheckout = lib.checkoutResource(user, titleToCheckout, comparator);
        assertFalse(successfulCheckout);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testReturnInvalidMovie() throws Exception {
        User user = new User("user1", "user1@user1.com", "11111111");
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        String titleToReturn = "invalid movie";
        MovieNameComparator comparator = new MovieNameComparator();
        boolean successfulReturn = lib.returnResource(user, titleToReturn, comparator);
        assertFalse(successfulReturn);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testReturnAvailableMovie() throws Exception {
        User user = new User("user1", "user1@user1.com", "11111111");
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        String titleToReturn = expectedMovies.get(0).getName();
        MovieNameComparator comparator = new MovieNameComparator();
        boolean successfulReturn = lib.returnResource(user, titleToReturn, comparator);
        assertFalse(successfulReturn);
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testCheckoutWithUserReturnMovieWithoutUser() throws Exception {
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        String titleToReturn = expectedMovies.get(0).getName();
        MovieNameComparator comparator = new MovieNameComparator();

        User user = new User("user1", "user1@user1.com", "11111111");
        assertTrue(lib.checkoutResource(user, titleToReturn, comparator));

        boolean successfulReturn = lib.returnResource(null, titleToReturn, comparator);
        assertFalse(successfulReturn);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(user, retrievedUser);
    }

    @Test
    public void testCheckoutWithUserReturnMovieWithAnotherUser() throws Exception {
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        String titleToReturn = expectedMovies.get(0).getName();
        MovieNameComparator comparator = new MovieNameComparator();

        User user1 = new User("user1", "user1@user1.com", "11111111");
        assertTrue("Checkout Movie before attempting to return.", lib.checkoutResource(user1, titleToReturn, comparator));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(user1, retrievedUser);

        User user2 = new User("user2", "user2@user2.com", "22222222");
        boolean successfulReturn = lib.returnResource(user2, titleToReturn, comparator);
        assertFalse(successfulReturn);
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(user1, retrievedUser);
    }

    @Test
    public void testCheckoutAndReturnWithSameUser() throws Exception {
        LibraryMovieStore lib = new LibraryMovieStore(expectedMovies);
        String titleToReturn = expectedMovies.get(expectedMovies.size()-1).getName();
        User user = new User("user1", "user1@user1.com", "11111111");
        MovieNameComparator comparator = new MovieNameComparator();
        assertTrue("Checkout Movie before attempting to return.", lib.checkoutResource(user, titleToReturn, comparator));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(user, retrievedUser);

        boolean successReturn = lib.returnResource(user, titleToReturn, comparator);
        assertTrue(successReturn);
        assertTrue(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertFalse(TestUtilities.movieNameExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testFormatMovieStoreOutput() throws Exception {
        LibraryMovieStore libraryMovieStore = new LibraryMovieStore(expectedMovies);
        String feedback = libraryMovieStore.availableResourceToString();
        assertEquals("           NAME||  YEAR||       DIRECTOR||RATING\n" +
                "         Name 1||  2001||     Director 1||     1\n" +
                "         Name 2||  2002||     Director 2||     2\n" +
                "         Name 3||  2003||     Director 3||     3", feedback);
    }

}