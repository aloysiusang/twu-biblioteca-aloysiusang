package com.twu.biblioteca;

import com.twu.biblioteca.options.ListBooksOption;
import com.twu.biblioteca.options.ListMoviesOption;
import com.twu.biblioteca.options.LoginOption;
import com.twu.biblioteca.options.MainMenuOption;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class GuestMainMenu extends MainMenu {
    public GuestMainMenu() {
        super( new ArrayList<MainMenuOption>() {{
            add(new LoginOption());
            add(new ListBooksOption());
            add(new ListMoviesOption());
            add(new QuitOption());
        }} );
    }
}