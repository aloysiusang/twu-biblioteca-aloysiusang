package com.twu.biblioteca;

/**
 * Created by aloysiusang on 10/6/15.
 */
public abstract class MainMenuOption {
    private final String name;

    public MainMenuOption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String execute(Library library);
}
