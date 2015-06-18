package com.twu.biblioteca;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class ReturnBookOptionTest {
    private AllLibraryStores libraryStores;
    private LibraryBookStore libraryBookStore;
    private ArrayList<LibraryBook> availableBooks;
    private UserAccountManagerStub userAccountManager;
    private User stubUser;

    @Before
    public void setUp() throws Exception {
        availableBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};
        libraryBookStore = new LibraryBookStore(availableBooks);
        libraryStores = new AllLibraryStores(libraryBookStore);
        stubUser = new User("stubuser", "stub@user.com", "12345678");
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
        userAccountManager.setCurrentUser(stubUser);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That is not a valid book to return.", feedback);
    }

    @Test
    public void testCheckoutAndReturnBook() throws Exception {
        MainMenuOption option = new ReturnBookOption();
        String titleToReturn = availableBooks.get(0).getTitle();
        libraryStores.checkoutBook(stubUser, titleToReturn, new BookTitleComparator());

        TestUtilities.setInput(titleToReturn);
        userAccountManager.setCurrentUser(stubUser);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("Thank you for returning the book.", feedback);
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getCheckedOutResource()));
    }

    @Test
    public void testReturnAvailableBook() throws Exception {
        MainMenuOption option = new ReturnBookOption();
        String titleToReturn = availableBooks.get(0).getTitle();
        TestUtilities.setInput(titleToReturn);
        userAccountManager.setCurrentUser(stubUser);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That is not a valid book to return.", feedback);
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getCheckedOutResource()));
    }

    private class UserAccountManagerStub extends UserAccountManager {

        private User currUser;
        public UserAccountManagerStub() {
            super(null);
            currUser = stubUser;
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