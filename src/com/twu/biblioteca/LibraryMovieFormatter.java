package com.twu.biblioteca;

import java.util.Formatter;
import java.util.List;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class LibraryMovieFormatter {

    private static final String FORMAT_STYLE = "%15s||%6s||%15s||%6s";

    public static String format(List<LibraryMovie> movies) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format(FORMAT_STYLE, "NAME", "YEAR", "DIRECTOR", "RATING");
        for(LibraryMovie movie : movies) {
            formatter.format(System.getProperty("line.separator"));
            formatter.format(FORMAT_STYLE, movie.getName(), movie.getYear(), movie.getDirector(), movie.getRating());
        }
        return sb.toString();
    }
}
