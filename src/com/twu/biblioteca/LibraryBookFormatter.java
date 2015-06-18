package com.twu.biblioteca;

import java.util.Formatter;
import java.util.List;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class LibraryBookFormatter {

    private static final String FORMAT_STYLE = "%15s||%15s||%6s";

    public static String format(List<LibraryBook> books) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format(FORMAT_STYLE, "TITLE", "AUTHOR", "YEAR");
        for(LibraryBook book : books) {
            formatter.format(System.getProperty("line.separator"));
            formatter.format(FORMAT_STYLE, book.getTitle(), book.getAuthor(), book.getYearPublished());
        }
        return sb.toString();
    }
}
