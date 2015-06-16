package com.twu.biblioteca;

/**
 * Created by aloysiusang on 12/6/15.
 */
public class LibraryMovie {
    private final String name;
    private final int year;
    private final String director;
    private final int rating;

    public LibraryMovie(String name, int year, String director, int rating) {
        this.name = name;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public int getYear() {
        return year;
    }

    public String getDirector() {
        return director;
    }

    public int getRating() {
        return rating;
    }
}
