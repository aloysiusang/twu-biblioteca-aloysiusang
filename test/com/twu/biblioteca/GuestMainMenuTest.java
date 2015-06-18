package com.twu.biblioteca;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class GuestMainMenuTest {
    @Test
    public void testCheckGuestMainMenuOptions() throws Exception {
        GuestMainMenu guestMainMenu = new GuestMainMenu();
        List<MainMenuOption> options = guestMainMenu.getOptions();
        assertEquals(4, options.size());
        for(MainMenuOption option : options) {
            assertTrue(option.getClass() == ListBooksOption.class || option.getClass() == QuitOption.class ||
                    option.getClass() == ListMoviesOption.class || option.getClass() == LoginOption.class);
        }
    }
}