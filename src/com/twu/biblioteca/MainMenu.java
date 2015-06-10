package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenu {
    private final ArrayList<MainMenuOption> options;

    public MainMenu() {
        options = new ArrayList<MainMenuOption>() {{
            add(new MainMenuOption("List Books"));
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
        return options.get(optionNumber).execute(library);
    }
}
