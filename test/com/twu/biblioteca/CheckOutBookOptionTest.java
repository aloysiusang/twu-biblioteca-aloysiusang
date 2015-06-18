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
    private User stubUser1;
    private User stubUser2;

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
        stubUser1 = new User("stubUser1", "stub1@email.com", "stub1PhoneNumber");
        stubUser2 = new User("stubUser2", "stub2@email.com", "stub2PhoneNumber");
        TestUtilities.redirectOutput();
    }

    @After
    public void tearDown() throws Exception {
        TestUtilities.resetStreams();
    }

    @Test
    public void testCheckoutInvalidBook() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckout = "Invalid Book";

        TestUtilities.setInput(titleToCheckout);
        String feedback = option.execute(new UserAccountManagerValidStub1(), libraryStores);
        assertEquals("That book is not available.", feedback);

        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryStores.getCheckedOutBooks()));
        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryStores.getAvailableBooks()));
        User user = libraryStores.getUserWhoCheckedOutBook(titleToCheckout, new BookTitleComparator());
        assertNull(user);
    }

    @Test
    public void testCheckoutBookWithInvalidUser() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckout = availableBooks.get(0).getTitle();

        TestUtilities.setInput(titleToCheckout);
        String feedback = option.execute(new UserAccountManagerInvalidStub(), libraryStores);
        assertEquals("That book is not available.", feedback);

        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryStores.getCheckedOutBooks()));
        assertTrue(bookTitleExistsInCollection(titleToCheckout, libraryStores.getAvailableBooks()));
        User user = libraryStores.getUserWhoCheckedOutBook(titleToCheckout, new BookTitleComparator());
        assertNull(user);
    }

    @Test
    public void testCheckoutBookWithValidUser() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckout = availableBooks.get(availableBooks.size()-1).getTitle();

        TestUtilities.setInput(titleToCheckout);
        String feedback = option.execute(new UserAccountManagerValidStub1(), libraryStores);

        assertEquals("Thank you! Enjoy the book", feedback);
        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryStores.getAvailableBooks()));
        assertTrue(bookTitleExistsInCollection(titleToCheckout, libraryStores.getCheckedOutBooks()));
        User retrievedUser = libraryStores.getUserWhoCheckedOutBook(titleToCheckout, new BookTitleComparator());
        assertEquals(stubUser1, retrievedUser);
    }

    @Test
    public void testCheckoutBookThatHasAlreadyBeenCheckedOut() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckout = availableBooks.get(0).getTitle();
        TestUtilities.setInput(titleToCheckout);
        String feedback = option.execute(new UserAccountManagerValidStub1(), libraryStores);
        assertEquals("Thank you! Enjoy the book", feedback);
        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryStores.getAvailableBooks()));
        assertTrue(bookTitleExistsInCollection(titleToCheckout, libraryStores.getCheckedOutBooks()));
        User retrievedUser = libraryStores.getUserWhoCheckedOutBook(titleToCheckout, new BookTitleComparator());
        assertEquals(stubUser1, retrievedUser);

        TestUtilities.setInput(titleToCheckout);
        feedback = option.execute(new UserAccountManagerValidStub2(), libraryStores);
        assertEquals("That book is not available.", feedback);
        assertFalse(bookTitleExistsInCollection(titleToCheckout, libraryStores.getAvailableBooks()));
        assertTrue(bookTitleExistsInCollection(titleToCheckout, libraryStores.getCheckedOutBooks()));
        retrievedUser = libraryStores.getUserWhoCheckedOutBook(titleToCheckout, new BookTitleComparator());
        assertEquals(stubUser1, retrievedUser);
    }

    private boolean bookTitleExistsInCollection(String title, Collection<LibraryBook> collection) {
        for (LibraryBook book : collection) {
            if (book.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    private class UserAccountManagerValidStub1 extends UserAccountManager {

        public UserAccountManagerValidStub1() {
            super(null);
        }

        @Override
        public User getCurrentUser() {
            return stubUser1;
        }
    }

    private class UserAccountManagerValidStub2 extends UserAccountManager {

        public UserAccountManagerValidStub2() {
            super(null);
        }

        @Override
        public User getCurrentUser() {
            return stubUser2;
        }
    }

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