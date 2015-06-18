package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

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

    public boolean checkoutBook(User user, String title, BookTitleComparator bookTitleComparator) {
        return bookStore.checkoutResource(user, title, bookTitleComparator);
    }

    public List<LibraryBook> getAvailableBooks() {
        return bookStore.getAvailableResource();
    }

    public List<LibraryBook> getCheckedOutBooks() {
        return bookStore.getCheckedOutResource();
    }

    public List<LibraryMovie> getAvailableMovies() {
        return movieStore.getAvailableResource();
    }

    public List<LibraryMovie> getCheckedOutMovies() {
        return movieStore.getCheckedOutResource();
    }

    public boolean checkoutMovie(User user, String name, MovieNameComparator movieNameComparator) {
        return movieStore.checkoutResource(user, name, movieNameComparator);
    }

    public boolean returnMovie(String name, MovieNameComparator movieNameComparator) {
        return movieStore.returnResource(name, movieNameComparator);
    }

    public User getUserWhoCheckedOutBook(String titleToCheckout, BookTitleComparator bookTitleComparator) {
        return bookStore.getUserWhoCheckedOutResource(titleToCheckout, bookTitleComparator);
    }
}
