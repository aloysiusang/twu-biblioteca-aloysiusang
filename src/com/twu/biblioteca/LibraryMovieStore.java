package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 12/6/15.
 */
public class LibraryMovieStore extends LibraryStore<LibraryMovie>{
    public LibraryMovieStore() {
        super();
    }

    public LibraryMovieStore(ArrayList<LibraryMovie> availableMovie) {
        super(availableMovie);
    }

    public LibraryMovieStore(ArrayList<LibraryMovie> availableMovie, ArrayList<LibraryMovie> checkedOutMovie) {
        super(availableMovie, checkedOutMovie);
    }

}
