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
}
