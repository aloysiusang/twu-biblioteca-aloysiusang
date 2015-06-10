package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenuFormatterTest {

    @Test
    public void testGetMainMenuOptionsToString() throws Exception {
        ArrayList<MainMenuOption> mainMenuOptions = new ArrayList<MainMenuOption>() {{
            add(new MainMenuOption("TEST OPTION 1"));
            add(new MainMenuOption("TEST OPTION 2"));
        }};
        MainMenu mainMenu = new MainMenu(mainMenuOptions);
        String mainMenuToString = MainMenuFormatter.format(mainMenu);
        assertEquals("          -MENU-          \n" +
                "  1|TEST OPTION 1                 \n" +
                "  2|TEST OPTION 2                 ", mainMenuToString);
    }
}