package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class AllLibraryStores {
    private LibraryBookStore bookStore;
    private LibraryMovieStore movieStore;

    public AllLibraryStores() {
        this.bookStore = new LibraryBookStore();
        this.movieStore = new LibraryMovieStore();
    }

    public AllLibraryStores(LibraryBookStore bookStore) {
        this.bookStore = bookStore;
        this.movieStore = new LibraryMovieStore();
    }

    public AllLibraryStores(LibraryMovieStore movieStore) {
        this.movieStore = movieStore;
        this.bookStore = new LibraryBookStore();
    }

    public AllLibraryStores(LibraryBookStore bookStore, LibraryMovieStore movieStore) {
        this.bookStore = bookStore;
        this.movieStore = movieStore;
    }

    public boolean returnBook(String title, BookTitleComparator bookTitleComparator) {
        return bookStore.returnResource(title, bookTitleComparator);
    }

    public boolean checkoutBook(String title, BookTitleComparator bookTitleComparator) {
        return bookStore.checkoutResource(title, bookTitleComparator);
    }

    public ArrayList<LibraryBook> getAvailableBooks() {
        return bookStore.getAvailableResource();
    }

    public ArrayList<LibraryBook> getCheckedOutBooks() {
        return bookStore.getCheckedOutResource();
    }

    public ArrayList<LibraryMovie> getAvailableMovies() {
        return movieStore.getAvailableResource();
    }

    public ArrayList<LibraryMovie> getCheckedOutMovies() {
        return movieStore.getCheckedOutResource();
    }

    public boolean checkoutMovie(String name, MovieNameComparator movieNameComparator) {
        return movieStore.checkoutResource(name, movieNameComparator);
    }

    public boolean returnMovie(String name, MovieNameComparator movieNameComparator) {
        return movieStore.returnResource(name, movieNameComparator);
    }
}
