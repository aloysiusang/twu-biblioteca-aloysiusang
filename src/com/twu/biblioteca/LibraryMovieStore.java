package com.twu.biblioteca;

import java.util.List;

/**
 * Created by aloysiusang on 12/6/15.
 */
public class LibraryMovieStore extends LibraryStoreTemplate<LibraryMovie> {
    public LibraryMovieStore() {
        super();
    }

    public LibraryMovieStore(List<LibraryMovie> availableMovie) {
        super(availableMovie);
    }

    @Override
    public String availableResourceToString() {
        return LibraryMovieFormatter.format(this.getAvailableResource());
    }
}
