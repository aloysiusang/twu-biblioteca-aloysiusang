package com.twu.biblioteca;

import java.util.Comparator;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class BookTitleComparator implements Comparator {

    @Override
    public int compare(Object o, Object t1) {
        if(o.getClass() == String.class) {
            String o1 = (String) o;
            LibraryBook o2 = (LibraryBook) t1;
            return (o1).compareTo(o2.getTitle());
        }
        else {
            LibraryBook b1 = (LibraryBook)o;
            LibraryBook b2 = (LibraryBook)t1;
            return b1.getTitle().compareTo(b2.getTitle());
        }
    }

}
