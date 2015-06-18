package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenuTest {

    @Test
    public void testSelectInvalidOption() throws Exception {
        ArrayList<MainMenuOption> options = new ArrayList<MainMenuOption>() {{
            add(new ListBooksOption());
            add(new QuitOption());
        }};
        MainMenu menu = new MainMenu(options);
        String feedback = menu.selectOption(0, null, null);
        assertEquals("Select a valid option!", feedback);
    }

    @Test
    public void testSelectValidOption() throws Exception {
        ArrayList<MainMenuOption> options = new ArrayList<MainMenuOption>() {{
            add(new MockOption());
            add(new MockOption());
        }};
        MainMenu menu = new MainMenu(options);
        String feedback = menu.selectOption(1, null, null);
        assertEquals("Mock Executed", feedback);
    }

    private class MockOption extends MainMenuOption {

        public MockOption() {
            super("Mock Option");
        }

        @Override
        public String execute(UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
            return "Mock Executed";
        }
    }

    private Integer findOption(MainMenu menu, Class optionClass) {
        Integer option = null;
        ArrayList<MainMenuOption> options = menu.getOptions();
        for(int i=0; i<options.size(); i++) {
            if(options.get(i).getClass() == optionClass) {
                option = i+1;
            }
        }
        return option;
    }
}