package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class GuestMainMenuTest {
    @Test
    public void testCheckGuestMainMenuOptions() throws Exception {
        GuestMainMenu guestMainMenu = new GuestMainMenu();
        ArrayList<MainMenuOption> options = guestMainMenu.getOptions();
        assertEquals(4, options.size());
        for(MainMenuOption option : options) {
            assertTrue(option.getClass() == ListBooksOption.class || option.getClass() == QuitOption.class ||
                    option.getClass() == ListMoviesOption.class || option.getClass() == LoginOption.class);
        }
    }
}