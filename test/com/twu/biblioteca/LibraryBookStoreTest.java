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
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertEquals(firstUser, retrievedUser);

        successfulCheckout = lib.checkoutResource(secondUser, titleToCheckout, comparator);
        assertFalse(successfulCheckout);
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
        User retrievedUser = lib.getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertNull(retrievedUser);
    }
}