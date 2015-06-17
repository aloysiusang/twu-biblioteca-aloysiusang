package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class BibliotecaAppTest {
    @Test
    public void testGetUserWhenNotLoggedIn() throws Exception {
        BibliotecaApp app = new BibliotecaApp();
        User user = app.getCurrentUser();
        assertEquals(null, user);
    }

    @Test
    public void testGetGuestMenu() throws Exception {
        BibliotecaApp app = new BibliotecaApp();
        MainMenu menu = app.getMainMenu();
        assertEquals(GuestMainMenu.class, menu.getClass());
    }
}