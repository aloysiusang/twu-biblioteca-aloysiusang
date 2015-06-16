package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by aloysiusang on 16/6/15.
 */
public abstract class LibraryStoreTemplate<E> {
    private ArrayList<E> availableResource;
    private ArrayList<E> checkedOutResource;

    public LibraryStoreTemplate() {
        availableResource = new ArrayList<E>();
        checkedOutResource = new ArrayList<E>();
    }

    public LibraryStoreTemplate(ArrayList<E> availableResource) {
        this.availableResource = availableResource;
        this.checkedOutResource = new ArrayList<E>();
    }

    public LibraryStoreTemplate(ArrayList<E> availableResource, ArrayList<E> checkedOutResource) {
        this.availableResource = availableResource;
        this.checkedOutResource = checkedOutResource;
    }

    public ArrayList<E> getAvailableResource() {
        return availableResource;
    }

    public ArrayList<E> getCheckedOutResource() {
        return checkedOutResource;
    }

    public boolean checkoutResource(String title, Comparator comparator) {
        return moveResource(title, comparator, availableResource, checkedOutResource);
    }

    public boolean returnResource(String title, Comparator comparator) {
        return moveResource(title, comparator, checkedOutResource, availableResource);
    }

    private boolean moveResource(String title, Comparator comparator, ArrayList<E> from, ArrayList<E> to) {
        boolean isMoved = false;
        for(Iterator<E> iterator = from.iterator(); iterator.hasNext();) {
            E resource = iterator.next();
            if(comparator.compare(title, resource) == 0) {
                to.add(resource);
                iterator.remove();
                isMoved = true;
                break;
            }
        }
        return isMoved;
    }

}
