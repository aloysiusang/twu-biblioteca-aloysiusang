package com.twu.biblioteca;

import com.twu.biblioteca.options.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserMainMenuTest {
    private Set<Class> menuClasses;

    @Before
    public void setUp() throws Exception {
        menuClasses = new HashSet<Class>() {{
            add(CheckOutMovieOption.class);
            add(QuitOption.class);
            add(ListBooksOption.class);
            add(UserInformationOption.class);
            add(ReturnBookOption.class);
            add(CheckOutBookOption.class);
            add(ListMoviesOption.class);
            add(ReturnMovieOption.class);
        }};
    }

    @Test
    public void testGetOptions() throws Exception {
        UserMainMenu userMainMenu = new UserMainMenu();
        List<MainMenuOption> options = userMainMenu.getOptions();
        for(MainMenuOption option : options) {
            if(menuClasses.contains(option.getClass())) {
                menuClasses.remove(option.getClass());
            }
            else {
                assertTrue("User Main Menu does not contain expected classes", false);
            }
        }
        assertTrue("User Main Menu does not contain expected classes", menuClasses.size() == 0);
    }
}