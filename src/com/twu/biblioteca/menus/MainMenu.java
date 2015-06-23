package com.twu.biblioteca.menus;

import com.twu.biblioteca.UserAccountManager;
import com.twu.biblioteca.libstores.AllLibraryStores;
import com.twu.biblioteca.options.MainMenuOption;

import java.util.List;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenu {
    private static final String FEEDBACK_INVALID_OPTION = "Select a valid option!";
    private final List<MainMenuOption> options;

    public MainMenu(List<MainMenuOption> mainMenuOptions) {
        this.options = mainMenuOptions;
    }

    public List<MainMenuOption> getOptions() {
        return options;
    }

    public String selectOption(int optionNumber, UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
        try {
            return options.get(optionNumber - 1).execute(userAccountManager, libraryStores);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            return FEEDBACK_INVALID_OPTION;
        }
    }
}
