package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by aloysiusang on 12/6/15.
 */
public class LibraryMovieStore {

    private ArrayList<LibraryMovie> availableMovies;
    private ArrayList<LibraryMovie> checkedOutMovies;

    public LibraryMovieStore() {
        availableMovies = new ArrayList<LibraryMovie>();
        checkedOutMovies = new ArrayList<LibraryMovie>();
    }

    public LibraryMovieStore(ArrayList<LibraryMovie> availableMovies) {
        this.availableMovies = availableMovies;
        checkedOutMovies = new ArrayList<LibraryMovie>();
    }

    public ArrayList<LibraryMovie> getAvailableMovies() {
        return availableMovies;
    }

    public ArrayList<LibraryMovie> getCheckedOutMovies() {
        return checkedOutMovies;
    }

    public boolean checkoutMovie(String name) {
        return moveMovie(name, availableMovies, checkedOutMovies);
    }

    public boolean returnMovie(String name) {
        return moveMovie(name, availableMovies, checkedOutMovies);
    }

    private boolean moveMovie(String title, ArrayList<LibraryMovie> from, ArrayList<LibraryMovie> to) {
        boolean isMoved = false;
        for(Iterator<LibraryMovie> iterator = from.iterator(); iterator.hasNext();) {
            LibraryMovie movie = iterator.next();
            if(movie.getName().equals(title)) {
                to.add(movie);
                iterator.remove();
                isMoved = true;
                break;
            }
        }
        return isMoved;
    }

}
