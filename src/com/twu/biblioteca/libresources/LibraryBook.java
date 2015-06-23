package com.twu.biblioteca.libresources;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class LibraryBook {
    private String title;
    private String author;
    private int yearPublished;

    public LibraryBook(String title, String author, int yearPublished) {
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYearPublished() {
        return yearPublished;
    }
}
