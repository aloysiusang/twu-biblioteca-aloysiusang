package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenuTest {
    @Test
    public void testGetMainMenuOptions() throws Exception {
        MainMenu mainMenu = new MainMenu();
        ArrayList<MainMenuOption> options = mainMenu.getOptions();
        assertEquals(7, options.size());
        assertEquals("List Books", options.get(0).getName());
    }

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

    @Test
    public void testListBookIsInMenu() throws Exception {
        MainMenu mainMenu = new MainMenu();
        Integer optionInMenu = findOption(mainMenu, ListBooksOption.class);
        assertNotEquals(null, optionInMenu);
    }

    @Test
    public void testQuitIsInMenu() throws Exception {
        MainMenu mainMenu = new MainMenu();
        Integer optionInMenu = findOption(mainMenu, QuitOption.class);
        assertNotEquals(null, optionInMenu);
    }

    @Test
    public void testCheckoutIsInMenu() throws Exception {
        MainMenu mainMenu = new MainMenu();
        Integer optionInMenu = findOption(mainMenu, CheckOutBookOption.class);
        assertNotEquals(null, optionInMenu);
    }

    @Test
    public void testReturnIsInMenu() throws Exception {
        MainMenu mainMenu = new MainMenu();
        Integer optionInMenu = findOption(mainMenu, ReturnBookOption.class);
        assertNotEquals(null, optionInMenu);
    }

    private class MockOption extends MainMenuOption {

        public MockOption() {
            super("Mock Option");
        }

        @Override
        public String execute(User user, AllLibraryStores libraryStores) {
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