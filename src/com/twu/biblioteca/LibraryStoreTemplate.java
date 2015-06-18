package com.twu.biblioteca;

import java.util.*;

/**
 * Created by aloysiusang on 16/6/15.
 */
public abstract class LibraryStoreTemplate<E> {
    private List<E> availableResource;
    private List<E> checkedOutResource;
    private Map<E, User> userCheckoutLog;

    public LibraryStoreTemplate() {
        availableResource = new ArrayList<E>();
        checkedOutResource = new ArrayList<E>();
        userCheckoutLog = new HashMap<E, User>();
    }

    public LibraryStoreTemplate(List<E> availableResource) {
        this.availableResource = availableResource;
        this.checkedOutResource = new ArrayList<E>();
        userCheckoutLog = new HashMap<E, User>();
    }

    public LibraryStoreTemplate(List<E> availableResource, List<E> checkedOutResource) {
        this.availableResource = availableResource;
        this.checkedOutResource = checkedOutResource;
        userCheckoutLog = new HashMap<E, User>();
    }

    public List<E> getAvailableResource() {
        return availableResource;
    }

    public List<E> getCheckedOutResource() {
        return checkedOutResource;
    }

    public boolean returnResource(User user, String title, Comparator comparator) {
        E resourceToBeReturned = findMatchingResource(title, comparator, checkedOutResource);
        if(resourceToBeReturned != null) {
            moveResource(resourceToBeReturned, checkedOutResource, availableResource);
        }
        return (resourceToBeReturned!=null);
    }

    public boolean checkoutResource(User user, String title, Comparator comparator) {
        if (user == null)
            return false;

        E resourceToBeCheckedOut = findMatchingResource(title, comparator, availableResource);
        if (resourceToBeCheckedOut != null) {
            moveResource(resourceToBeCheckedOut, availableResource, checkedOutResource);
            logCheckout(resourceToBeCheckedOut, user);
        }
        return (resourceToBeCheckedOut!=null);
    }

    public User getUserWhoCheckedOutResource(String title, Comparator comparator) {
        User user = null;
        E resource = findMatchingResource(title, comparator, userCheckoutLog.keySet());
        if(resource!=null) {
            user = userCheckoutLog.get(resource);
        }
        return user;
    }



    private void logCheckout(E resourceToBeCheckedOut, User user) {
        this.userCheckoutLog.put(resourceToBeCheckedOut, user);
    }

    private void moveResource(E resourceToBeMoved, List<E> from, List<E> to) {
        to.add(resourceToBeMoved);
        from.remove(resourceToBeMoved);
    }

    private E findMatchingResource(String title, Comparator comparator, Collection<E> listToFind) {
        E matchingResource = null;
        for (Iterator<E> iterator = listToFind.iterator(); iterator.hasNext(); ) {
            E resource = iterator.next();
            if (comparator.compare(title, resource) == 0) {
                matchingResource = resource;
                break;
            }
        }
        return matchingResource;
    }
}
