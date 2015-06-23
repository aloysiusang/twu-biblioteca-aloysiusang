package com.twu.biblioteca.options;

import com.twu.biblioteca.*;
import com.twu.biblioteca.options.CheckOutBookOption;
import com.twu.biblioteca.options.MainMenuOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 11/6/15.
 */
public class CheckOutBookOptionTest {

    private AllLibraryStores libraryStores;
    private LibraryBookStore libraryBookStore;
    private ArrayList<LibraryBook> availableBooks;
    private Stub_UserAccountManager userAccountManager;
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
        stubUser1 = new User("stubUser1", "stub1@email.com", "stub1PhoneNumber");
        stubUser2 = new User("stubUser2", "stub2@email.com", "stub2PhoneNumber");
        userAccountManager = new Stub_UserAccountManager();
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

        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That book is not available.", feedback);

        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getCheckedOutResource()));
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getAvailableResource()));
        User user = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToCheckout, new BookTitleComparator());
        assertNull(user);
    }

    @Test
    public void testCheckoutBookWithNullUser() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckout = availableBooks.get(0).getTitle();
        userAccountManager.setCurrentUser(null);

        TestUtilities.setInput(titleToCheckout);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That book is not available.", feedback);

        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getCheckedOutResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getAvailableResource()));
        User user = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToCheckout, new BookTitleComparator());
        assertNull(user);
    }

    @Test
    public void testCheckoutBookWithValidUser() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckout = availableBooks.get(availableBooks.size()-1).getTitle();
        userAccountManager.setCurrentUser(stubUser1);

        TestUtilities.setInput(titleToCheckout);
        String feedback = option.execute(userAccountManager, libraryStores);

        assertEquals("Thank you! Enjoy the book", feedback);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getCheckedOutResource()));
        User retrievedUser = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToCheckout, new BookTitleComparator());
        assertEquals(stubUser1, retrievedUser);
    }

    @Test
    public void testCheckoutBookThatHasAlreadyBeenCheckedOut() throws Exception {
        MainMenuOption option = new CheckOutBookOption();
        String titleToCheckout = availableBooks.get(0).getTitle();
        TestUtilities.setInput(titleToCheckout);

        userAccountManager.setCurrentUser(stubUser1);
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("Thank you! Enjoy the book", feedback);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getCheckedOutResource()));
        User retrievedUser = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToCheckout, new BookTitleComparator());
        assertEquals(stubUser1, retrievedUser);

        TestUtilities.setInput(titleToCheckout);
        userAccountManager.setCurrentUser(stubUser2);
        feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("That book is not available.", feedback);
        assertFalse(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getAvailableResource()));
        assertTrue(TestUtilities.bookTitleExistsInCollection(titleToCheckout, libraryStores.getBookStore().getCheckedOutResource()));
        retrievedUser = libraryStores.getBookStore().getUserWhoCheckedOutResource(titleToCheckout, new BookTitleComparator());
        assertEquals(stubUser1, retrievedUser);
    }

}