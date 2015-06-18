package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class ReturnBookOptionTest {
    private AllLibraryStores libraryStores;
    private LibraryBookStore libraryBookStore;
    private ArrayList<LibraryBook> availableBooks;
    private UserAccountManagerStub userAccountManager;
    private User stubUser1;
    private User stubUser2;

    @Before
    public void setUp() throws Exception {
        availableBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};
        libraryBookStore = new LibraryBookStore(availableBooks);
        libraryStores = new AllLibraryStores(libraryBookStore);
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
    public void testReturnInvalidBook() throws Exception {
        MainMenuOption option = new ReturnBookOption();
        TestUtilities.setInput("Invalid Book");
        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That is not a valid book to return.", feedback);
    }

    @Test
    public void testCheckoutBookWithValidUserReturnBookWithInvalidUser() throws Exception {
        MainMenuOption option = new ReturnBookOption();
        String titleToReturn = availableBooks.get(0).getTitle();
        BookTitleComparator comparator = new BookTitleComparator();
        checkoutBook(stubUser1, titleToReturn, comparator);

        userAccountManager.setCurrentUser(null);
        TestUtilities.setInput(titleToReturn);
        option.execute(userAccountManager, libraryStores);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryStores.getBookStore().getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryStores.getBookStore().getCheckedOutResource()));
        User user = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(stubUser1, user);

    }

    @Test
    public void testCheckoutBookAndReturnBookWithAnotherUser() throws Exception {
        MainMenuOption option = new ReturnBookOption();
        String titleToReturn = availableBooks.get(0).getTitle();
        BookTitleComparator comparator = new BookTitleComparator();
        checkoutBook(stubUser1, titleToReturn, comparator);

        userAccountManager.setCurrentUser(stubUser2);
        TestUtilities.setInput(titleToReturn);
        option.execute(userAccountManager, libraryStores);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryStores.getBookStore().getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryStores.getBookStore().getCheckedOutResource()));
        User user = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertEquals(stubUser1, user);

    }

    @Test
    public void testCheckoutAndReturnBookWithSameUser() throws Exception {
        MainMenuOption option = new ReturnBookOption();
        BookTitleComparator comparator = new BookTitleComparator();
        String titleToReturn = availableBooks.get(0).getTitle();
        checkoutBook(stubUser1, titleToReturn, comparator);


        TestUtilities.setInput(titleToReturn);
        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("Thank you for returning the book.", feedback);
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getCheckedOutResource()));
        User user = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToReturn, comparator);
        assertNull(user);
    }

    @Test
    public void testReturnAvailableBook() throws Exception {
        MainMenuOption option = new ReturnBookOption();
        String titleToReturn = availableBooks.get(0).getTitle();
        TestUtilities.setInput(titleToReturn);
        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That is not a valid book to return.", feedback);
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getCheckedOutResource()));
        User user = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToReturn, new BookTitleComparator());
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

    private void checkoutBook(User user, String titleToCheckout, Comparator comparator) {
        assertTrue(libraryStores.getBookStore().checkoutResource(user, titleToCheckout, new BookTitleComparator()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getCheckedOutResource()));
        User retrievedUser = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToCheckout, comparator);
        assertEquals(user, retrievedUser);
    }
}