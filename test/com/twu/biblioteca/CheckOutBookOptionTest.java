package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class CheckOutBookOptionTest {

    private AllLibraryStores libraryStores;
    private LibraryBookStore libraryBookStore;
    private ArrayList<LibraryBook> availableBooks;
    private ArrayList<LibraryBook> checkedOutBooks;
    private User stubUser;

    @Before
    public void setUp() throws Exception {
        availableBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};
        checkedOutBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 11", "Author 11", 2011));
            add(new LibraryBook("Book 12", "Author 12", 2012));
            add(new LibraryBook("Book 13", "Author 13", 2013));
        }};
        libraryBookStore = new LibraryBookStore(availableBooks, checkedOutBooks);
        libraryStores = new AllLibraryStores(libraryBookStore);
        stubUser = new User("stubUser", "stubEmail", "stubPhoneNumber");
        TestUtilities.redirectOutput();
    }

    @After
    public void tearDown() throws Exception {
        TestUtilities.resetStreams();
    }

    @Test
    public void testCheckoutBook() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckout = availableBooks.get(0).getTitle();

        TestUtilities.setInput(titleToCheckout);
        String feedback = option.execute(new UserAccountManagerValidStub(), libraryStores);

        assertEquals("Thank you! Enjoy the book", feedback);
        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryStores.getAvailableBooks()));
        assertTrue(bookTitleExistsInCollection(titleToCheckout, libraryStores.getCheckedOutBooks()));

    }

    @Test
    public void testCheckoutInvalidBook() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckout = "Invalid Book";

        TestUtilities.setInput(titleToCheckout);
        String feedback = option.execute(new UserAccountManagerValidStub(), libraryStores);
        assertEquals("That book is not available.", feedback);

        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryStores.getCheckedOutBooks()));
    }

    @Test
    public void testCheckoutBookThatHasAlreadyBeenCheckedOut() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckOut = checkedOutBooks.get(0).getTitle();
        TestUtilities.setInput(titleToCheckOut);
        String feedback = option.execute(new UserAccountManagerValidStub(), libraryStores);
        assertEquals("That book is not available.", feedback);
        assertFalse(bookTitleExistsInCollection(titleToCheckOut, libraryStores.getAvailableBooks()));
        assertTrue(bookTitleExistsInCollection(titleToCheckOut, libraryStores.getCheckedOutBooks()));
        //TODO: check out with user1, check out again with user2, and ensure the book is still recorded as checked out with user1
    }

    private boolean bookTitleExistsInCollection(String title, Collection<LibraryBook> collection) {
        for (LibraryBook book : collection) {
            if (book.getTitle().equals(title)) {
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