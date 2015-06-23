package com.twu.biblioteca;

import com.twu.biblioteca.libresources.LibraryBook;
import com.twu.biblioteca.libresources.LibraryMovie;

import java.io.*;
import java.util.Collection;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class TestUtilities {
    public static void redirectOutput() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    public static void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void resetStreams() {
        System.setIn(new FileInputStream(FileDescriptor.in));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    public static boolean bookTitleExistsInCollection(String title, Collection<LibraryBook> collection) {
        for (LibraryBook book : collection) {
            if (book.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }

    public static boolean movieNameExistsInCollection(String name, Collection<LibraryMovie> collection) {
        for (LibraryMovie movie : collection) {
            if (movie.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
