package com.twu.biblioteca;

import com.twu.biblioteca.options.ListBooksOption;
import com.twu.biblioteca.options.MainMenuOption;
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
            add(new ListBooksOption());
            add(new QuitOption());
        }};
        MainMenu mainMenu = new MainMenu(mainMenuOptions);
        String mainMenuToString = MainMenuFormatter.format(mainMenu);
        assertEquals("          -MENU-          \n" +
                "  1|List Books                    \n" +
                "  2|Quit                          ", mainMenuToString);
    }
}