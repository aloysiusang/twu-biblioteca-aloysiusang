package com.twu.biblioteca;

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