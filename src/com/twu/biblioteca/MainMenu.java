package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenu {
    private static final String FEEDBACK_INVALID_OPTION = "Select a valid option!";
    private final ArrayList<MainMenuOption> options;

    public MainMenu(ArrayList<MainMenuOption> mainMenuOptions) {
        this.options = mainMenuOptions;
    }

    public ArrayList<MainMenuOption> getOptions() {
        return options;
    }

    public String selectOption(int optionNumber, UserAccountManager userAccountManager, AllLibraryStores libraryBookStore) {
        try {
            return options.get(optionNumber - 1).execute(userAccountManager, libraryBookStore);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            return FEEDBACK_INVALID_OPTION;
        }
    }
}
