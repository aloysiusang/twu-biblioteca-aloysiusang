package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenu {
    private ArrayList<MainMenuOption> options;

    public ArrayList<MainMenuOption> getOptions() {
        return new ArrayList<MainMenuOption>() {{
            add(new MainMenuOption("List Books"));
        }};
    }
}
