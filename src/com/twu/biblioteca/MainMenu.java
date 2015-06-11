package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenu {
    public static final String FEEDBACK_INVALID_OPTION = "Select a valid option!";
    private final ArrayList<MainMenuOption> options;

    public MainMenu() {
        options = new ArrayList<MainMenuOption>() {{
            add(new ListBooksOption());
            add(new QuitOption());
        }};
    }

    public MainMenu(ArrayList<MainMenuOption> mainMenuOptions) {
        this.options = mainMenuOptions;
    }

    public ArrayList<String> getOptions() {
        ArrayList<String> optionsToString = new ArrayList<String>();
        for(MainMenuOption option : options) {
            optionsToString.add(option.getName());
        }
        return optionsToString;
    }

    public String selectOption(int optionNumber, Library library) {
        try {
            return options.get(optionNumber - 1).execute(library);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            return FEEDBACK_INVALID_OPTION;
        }
    }
}
