package com.twu.biblioteca.options;

import com.twu.biblioteca.*;
import com.twu.biblioteca.libstores.AllLibraryStores;
import com.twu.biblioteca.libstores.LibraryMovieStore;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 19/6/15.
 */
public class ListMoviesOptionTest {
    private List<LibraryMovie> expectedMovies;

    @Before
    public void setUp() throws Exception {
        expectedMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 1", 2001, "Director 1", 1));
            add(new LibraryMovie("Name 2", 2002, "Director 2", 2));
            add(new LibraryMovie("Name 3", 2003, "Director 3", 3));
        }};
    }

    @Test
    public void testListEmptyLibraryWithoutUser() throws Exception {
        LibraryMovieStore libraryMovieStore = new LibraryMovieStore();
        AllLibraryStores libraryStores = new AllLibraryStores(libraryMovieStore);
        ListMoviesOption option = new ListMoviesOption();
        String feedback = option.execute(null, libraryStores);
        assertEquals("           NAME||  YEAR||       DIRECTOR||RATING", feedback);
    }

    @Test
    public void testListLibraryWithoutUser() throws Exception {
        LibraryMovieStore libraryMovieStore = new LibraryMovieStore(expectedMovies);
        AllLibraryStores libraryStores = new AllLibraryStores(libraryMovieStore);
        ListMoviesOption option = new ListMoviesOption();
        String feedback = option.execute(null, libraryStores);
        assertEquals("           NAME||  YEAR||       DIRECTOR||RATING\n" +
                "         Name 1||  2001||     Director 1||     1\n" +
                "         Name 2||  2002||     Director 2||     2\n" +
                "         Name 3||  2003||     Director 3||     3", feedback);
    }

    @Test
    public void testListLibraryWithUser() throws Exception {
        Stub_UserAccountManager userAccountManager = new Stub_UserAccountManager();
        userAccountManager.setCurrentUser(new User("000-0001", "email@email.com", "11111111"));
        AllLibraryStores libraryStores = new AllLibraryStores(new LibraryMovieStore(expectedMovies));
        ListMoviesOption option = new ListMoviesOption();
        String feedback = option.execute(userAccountManager, libraryStores);
        assertEquals("           NAME||  YEAR||       DIRECTOR||RATING\n" +
                "         Name 1||  2001||     Director 1||     1\n" +
                "         Name 2||  2002||     Director 2||     2\n" +
                "         Name 3||  2003||     Director 3||     3", feedback);
    }
}