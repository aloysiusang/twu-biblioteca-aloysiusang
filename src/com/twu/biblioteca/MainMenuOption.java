package com.twu.biblioteca;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenuOption {
    private final String name;

    public MainMenuOption(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String execute(Library library) {
        return LibraryBookFormatter.format(library.getAllBooks());
    }
}
