package com.twu.biblioteca.menus;

import com.twu.biblioteca.options.*;

import java.util.ArrayList;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserMainMenu extends MainMenu {

    public UserMainMenu() {
        super(new ArrayList<MainMenuOption>() {{
            add(new ListBooksOption());
            add(new CheckOutBookOption());
            add(new ReturnBookOption());
            add(new ListMoviesOption());
            add(new CheckOutMovieOption());
            add(new ReturnMovieOption());
            add(new UserInformationOption());
            add(new QuitOption());
        }});
    }
}
