package com.twu.biblioteca;

import com.twu.biblioteca.libresources.LibraryMovie;

import java.util.Comparator;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class MovieNameComparator implements Comparator {
    @Override
    public int compare(Object o, Object t1) {
        if(o.getClass() == String.class) {
            String o1 = (String) o;
            LibraryMovie o2 = (LibraryMovie) t1;
            return (o1).compareTo(o2.getName());
        }
        else {
            LibraryMovie b1 = (LibraryMovie)o;
            LibraryMovie b2 = (LibraryMovie)t1;
            return b1.getName().compareTo(b2.getName());
        }
    }
}
