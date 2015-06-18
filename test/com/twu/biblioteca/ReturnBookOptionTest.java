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
public class ReturnBookOptionTest {
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
        stubUser = new User("stubuser", "stub@user.com", "12345678");
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
        String feedback = option.execute(new UserAccountManagerValidStub(), libraryStores);
        assertEquals("That is not a valid book to return.", feedback);
    }

    @Test
    public void testReturnBook() throws Exception {
        MainMenuOption option = new ReturnBookOption();
        String titleToReturn = checkedOutBooks.get(0).getTitle();
        TestUtilities.setInput(titleToReturn);
        String feedback = option.execute(new UserAccountManagerValidStub(), libraryStores);
        assertEquals("Thank you for returning the book.", feedback);
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getCheckedOutResource()));
    }

    @Test
    public void testReturnAvailableBook() throws Exception {
        MainMenuOption option = new ReturnBookOption();
        String titleToReturn = availableBooks.get(0).getTitle();
        TestUtilities.setInput(titleToReturn);
        String feedback = option.execute(new UserAccountManagerValidStub(), libraryStores);
        assertEquals("That is not a valid book to return.", feedback);
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getAvailableResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToReturn, libraryBookStore.getCheckedOutResource()));
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