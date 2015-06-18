package com.twu.biblioteca;

import java.util.Comparator;
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

    public boolean returnBook(User user, String title, Comparator comparator) {
        return bookStore.returnResource(user, title, comparator);
    }

    public boolean checkoutBook(User user, String title, Comparator comparator) {
        return bookStore.checkoutResource(user, title, comparator);
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

    public boolean returnMovie(User user, String name, MovieNameComparator movieNameComparator) {
        return movieStore.returnResource(user, name, movieNameComparator);
    }

    public User getUserWhoCheckedOutBook(String titleToCheckout, Comparator bookTitleComparator) {
        return bookStore.getUserWhoCheckedOutResource(titleToCheckout, bookTitleComparator);
    }

    public User getUserWhoCheckedOutMovie(String movieName, Comparator movieNameComparator) {
        return movieStore.getUserWhoCheckedOutResource(movieName, movieNameComparator);
    }
}
