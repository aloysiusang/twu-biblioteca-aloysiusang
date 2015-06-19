package com.twu.biblioteca;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class LibraryBookStoreTest {

    private ArrayList<LibraryBook> expectedBooks;

    @Before
    public void setUp() throws Exception {
        expectedBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};
    }

    @Test
    public void testGetAllBooksWhenThereAreNoBooks() throws Exception {
        LibraryBookStore lib = new LibraryBookStore();
        List<LibraryBook> books = lib.getAvailableResource();
        assertEquals(0, books.size());
    }

    @Test
    public void testGetAllBooks() throws Exception {
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        List<LibraryBook> actualBooks = lib.getAvailableResource();
        assertEquals(expectedBooks, actualBooks);
        assertEquals("Book 1", actualBooks.get(0).getTitle());
        assertEquals("Author 1", actualBooks.get(0).getAuthor());
        assertEquals(2001, actualBooks.get(0).getYearPublished());
    }

    @Test
    public void testCheckoutBookWithNoUser() throws Exception {
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        String titleToCheckout = expectedBooks.get(0).getTitle();
        BookTitleComparator comparator = new BookTitleComparator();
        boolean successfulCheckout = lib.checkoutResource(null, titleToCheckout, comparator);
        assertFalse(successfulCheckout);
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testCheckoutBookWithUser() throws Exception {
        User user = new User("user1", "user1@user1.com", "11111111");
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        String titleToCheckout = expectedBooks.get(expectedBooks.size()-1).getTitle();
        BookTitleComparator comparator = new BookTitleComparator();
        boolean successfulCheckout = lib.checkoutResource(user, titleToCheckout, comparator);
        assertTrue(successfulCheckout);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertEquals(user, retrievedUser);
    }

    @Test
    public void testCheckoutBookThatHasBeenCheckedOut() throws Exception {
        User firstUser = new User("user1", "user1@user1.com", "11111111");
        User secondUser = new User("user2", "user2@user2.com", "22222222");
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        String titleToCheckout = expectedBooks.get(0).getTitle();
        BookTitleComparator comparator = new BookTitleComparator();
        boolean successfulCheckout = lib.checkoutResource(firstUser, titleToCheckout, comparator);
        assertTrue(successfulCheckout);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertEquals(firstUser, retrievedUser);

        successfulCheckout = lib.checkoutResource(secondUser, titleToCheckout, comparator);
        assertFalse(successfulCheckout);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertEquals(firstUser, retrievedUser);
    }

    @Test
    public void testCheckoutInvalidBook() throws Exception {
        User user = new User("user1", "user1@user1.com", "11111111");
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        String titleToCheckout = "invalid book";
        BookTitleComparator comparator = new BookTitleComparator();
        boolean successfulCheckout = lib.checkoutResource(user, titleToCheckout, comparator);
        assertFalse(successfulCheckout);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testReturnInvalidBook() throws Exception {
        User user = new User("user1", "user1@user1.com", "11111111");
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        String titleToReturn = "invalid book";
        BookTitleComparator comparator = new BookTitleComparator();
        boolean successfulReturn = lib.returnResource(user, titleToReturn, comparator);
        assertFalse(successfulReturn);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testReturnAvailableBook() throws Exception {
        User user = new User("user1", "user1@user1.com", "11111111");
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        String titleToReturn = expectedBooks.get(0).getTitle();
        BookTitleComparator comparator = new BookTitleComparator();
        boolean successfulReturn = lib.returnResource(user, titleToReturn, comparator);
        assertFalse(successfulReturn);
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testCheckoutWithUserReturnBookWithoutUser() throws Exception {
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        String titleToReturn = expectedBooks.get(0).getTitle();
        BookTitleComparator comparator = new BookTitleComparator();

        User user = new User("user1", "user1@user1.com", "11111111");
        assertTrue(lib.checkoutResource(user, titleToReturn, comparator));

        boolean successfulReturn = lib.returnResource(null, titleToReturn, comparator);
        assertFalse(successfulReturn);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(user, retrievedUser);
    }

    @Test
    public void testCheckoutWithUserReturnBookWithAnotherUser() throws Exception {
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        String titleToReturn = expectedBooks.get(0).getTitle();
        BookTitleComparator comparator = new BookTitleComparator();

        User user1 = new User("user1", "user1@user1.com", "11111111");
        assertTrue("Checkout book before attempting to return.", lib.checkoutResource(user1, titleToReturn, comparator));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(user1, retrievedUser);

        User user2 = new User("user2", "user2@user2.com", "22222222");
        boolean successfulReturn = lib.returnResource(user2, titleToReturn, comparator);
        assertFalse(successfulReturn);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(user1, retrievedUser);
    }

    @Test
    public void testCheckoutAndReturnWithSameUser() throws Exception {
        LibraryBookStore lib = new LibraryBookStore(expectedBooks);
        String titleToReturn = expectedBooks.get(expectedBooks.size()-1).getTitle();
        User user = new User("user1", "user1@user1.com", "11111111");
        BookTitleComparator comparator = new BookTitleComparator();
        assertTrue("Checkout book before attempting to return.", lib.checkoutResource(user, titleToReturn, comparator));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(user, retrievedUser);

        boolean successReturn = lib.returnResource(user, titleToReturn, comparator);
        assertTrue(successReturn);
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, lib.getCheckedOutResource()));
        retrievedUser = lib.getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertNull(retrievedUser);
    }

    @Test
    public void testFormatLibraryBookStoreOutput() throws Exception {
        LibraryBookStore libraryBookStore = new LibraryBookStore(expectedBooks);
        String feedback = libraryBookStore.availableResourceToString();
        assertEquals("          TITLE||         AUTHOR||  YEAR\n" +
                "         Book 1||       Author 1||  2001\n" +
                "         Book 2||       Author 2||  2002\n" +
                "         Book 3||       Author 3||  2003", feedback);
    }
}