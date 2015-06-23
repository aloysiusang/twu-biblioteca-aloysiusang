package com.twu.biblioteca.libstores;

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

    public LibraryBookStore getBookStore() {
        return bookStore;
    }

    public LibraryMovieStore getMovieStore() {
        return movieStore;
    }
}
