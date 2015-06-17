package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class BibliotecaApp {

    private static final String MESSAGE_WELCOME = "Welcome to Biblioteca!";
    private static final String MESSAGE_EXIT = "Thank you for using Biblioteca!";
    private static final String MESSAGE_SELECT_OPTION = "Select an option: ";
    private static AllLibraryStores libraryStores;
    private static UserAccountManager userAccountManager;
    private static UserAccountVault userAccountVault;

    static {
        ArrayList<LibraryBook> expectedBooks = new ArrayList<LibraryBook>() {{
            add(new LibraryBook("Book 1", "Author 1", 2001));
            add(new LibraryBook("Book 2", "Author 2", 2002));
            add(new LibraryBook("Book 3", "Author 3", 2003));
        }};

        ArrayList<LibraryMovie> expectedMovies = new ArrayList<LibraryMovie>() {{
            add(new LibraryMovie("Name 1", 2001, "Director 1", 1));
            add(new LibraryMovie("Name 2", 2002, "Director 2", 2));
            add(new LibraryMovie("Name 3", 2003, "Director 3", 3));
        }};

        UserAccountVault userAccountVault = new UserAccountVault( new HashMap<LoginCredential, User>() {{
            put(new LoginCredential("user1", "password1"), new User());
            put(new LoginCredential("user2", "password2"), new User());
        }});

        userAccountManager = new UserAccountManager(userAccountVault);

        libraryStores = new AllLibraryStores(new LibraryBookStore(expectedBooks), new LibraryMovieStore(expectedMovies));
    }

    public static void main(String[] args) {
        BibliotecaApp app = new BibliotecaApp();
        app.start();
    }

    private void start() {
        System.out.println(MESSAGE_WELCOME);
        int input;
        String feedback;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do {
            MainMenu mainMenu = getMainMenu();
            printMainMenu(mainMenu);
            input = Integer.parseInt(getInput(reader));
            feedback = mainMenu.selectOption(input, userAccountManager, libraryStores);
            printFeedback(feedback);
        }
        while(feedback != null);
    }

    private void printMainMenu(MainMenu mainMenu) {
        System.out.println();
        System.out.println(MainMenuFormatter.format(mainMenu));
        System.out.print(MESSAGE_SELECT_OPTION);
    }

    private String getInput(BufferedReader reader) {
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    private void printFeedback(String feedback) {
        if(feedback != null)
            System.out.println(feedback);
        else
            System.out.println(MESSAGE_EXIT);
    }

    public MainMenu getMainMenu() {
        return new GuestMainMenu();
    }
}
