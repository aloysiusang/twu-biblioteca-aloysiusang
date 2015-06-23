package com.twu.biblioteca;

import com.twu.biblioteca.libresources.LibraryMovie;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class LibraryMovieFormatterTest {
    @Test
    public void testFormatMoviesToString() throws Exception {
        ArrayList<LibraryMovie> expectedMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 1", 2001, "Director 1", 1));
            add(new LibraryMovie("Name 2", 2002, "Director 2", 2));
            add(new LibraryMovie("Name 3", 2003, "Director 3", 3));
        }};

        String formattedMovies = LibraryMovieFormatter.format(expectedMovies);
        assertEquals("           NAME||  YEAR||       DIRECTOR||RATING\n" +
                "         Name 1||  2001||     Director 1||     1\n" +
                "         Name 2||  2002||     Director 2||     2\n" +
                "         Name 3||  2003||     Director 3||     3", formattedMovies);
    }
}