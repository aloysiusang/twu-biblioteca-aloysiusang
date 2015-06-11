package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BibliotecaApp {

    public static final String MESSAGE_WELCOME = "Welcome to Biblioteca!";
    public static final String MESSAGE_EXIT = "Thank you for using Biblioteca!";
    public static final String MESSAGE_SELECT_OPTION = "Select an option: ";
    private static final ArrayList<LibraryBook> books = new ArrayList<LibraryBook>() {{
        add(new LibraryBook("Book 1", "Author 1", 2001));
        add(new LibraryBook("Book 2", "Author 2", 2002));
        add(new LibraryBook("Book 3", "Author 3", 2003));
    }};
    private static Library library = new Library(books);

    public static void main(String[] args) {
        System.out.println(MESSAGE_WELCOME);
        int input;
        String feedback;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        MainMenu mainMenu = new MainMenu();
        do {
            printMainMenu(mainMenu);
            input = Integer.parseInt(getInput(reader));
            feedback = mainMenu.selectOption(input, library);
            printFeedback(feedback);
        }
        while(feedback != null);
    }

    private static void printMainMenu(MainMenu mainMenu) {
        System.out.println();
        System.out.println(MainMenuFormatter.format(mainMenu));
        System.out.print(MESSAGE_SELECT_OPTION);
    }

    private static String getInput(BufferedReader reader) {
        String input = "";
        try {
            input = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }

    private static void printFeedback(String feedback) {
        if(feedback != null)
            System.out.println(feedback);
        else
            System.out.println(MESSAGE_EXIT);
    }

}
