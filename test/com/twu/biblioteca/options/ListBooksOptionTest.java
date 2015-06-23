package com.twu.biblioteca.options;

import com.twu.biblioteca.*;
import com.twu.biblioteca.libresources.LibraryBook;
import com.twu.biblioteca.libstores.AllLibraryStores;
import com.twu.biblioteca.libstores.LibraryBookStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 19/6/15.
 */
public class ListBooksOptionTest {
    private List<LibraryBook> expectedBooks;

    @Before
    public void setUp() throws Exception {
        expectedBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};
    }

    @Test
    public void testListEmptyLibraryWithoutUser() throws Exception {
        LibraryBookStore libraryBookStore = new LibraryBookStore();
        AllLibraryStores libraryStores = new AllLibraryStores(libraryBookStore);
        ListBooksOption option = new ListBooksOption();
        String feedback = option.execute(null, libraryStores);
        assertEquals("          TITLE||         AUTHOR||  YEAR", feedback);
    }

    @Test
    public void testListLibraryWithoutUser() throws Exception {
        LibraryBookStore libraryBookStore = new LibraryBookStore(expectedBooks);
        AllLibraryStores libraryStores = new AllLibraryStores(libraryBookStore);
        ListBooksOption option = new ListBooksOption();
        String feedback = option.execute(null, libraryStores);
        assertEquals("          TITLE||         AUTHOR||  YEAR\n" +
                "         Book 1||       Author 1||  2001\n" +
                "         Book 2||       Author 2||  2002\n" +
                "         Book 3||       Author 3||  2003", feedback);
    }

    @Test
    public void testListLibraryWithUser() throws Exception {
        Stub_UserAccountManager userAccountManager = new Stub_UserAccountManager();
        userAccountManager.setCurrentUser(new User("000-0001", "email@email.com", "11111111"));
        AllLibraryStores libraryStores = new AllLibraryStores(new LibraryBookStore(expectedBooks));
        ListBooksOption option = new ListBooksOption();
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("          TITLE||         AUTHOR||  YEAR\n" +
                "         Book 1||       Author 1||  2001\n" +
                "         Book 2||       Author 2||  2002\n" +
                "         Book 3||       Author 3||  2003", feedback);
    }
}