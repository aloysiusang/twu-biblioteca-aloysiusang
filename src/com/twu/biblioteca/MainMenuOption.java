package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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

    protected String getUserInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String userInput = null;
        try {
            userInput = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userInput;
    }

    public abstract String execute(Library library);
}
