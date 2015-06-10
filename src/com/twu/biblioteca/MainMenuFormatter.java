package com.twu.biblioteca;

import java.util.Formatter;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class MainMenuFormatter {
    public static String format(MainMenu mainMenu) {
        String format = "%3s|%-30s";
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatter.format("%10s-%4s-%10s", "", "MENU", "");
        int optionNumber = 1;
        for(String option : mainMenu.getOptions()) {
            formatter.format(System.getProperty("line.separator"));
            formatter.format(format, optionNumber++, option);
        }
        return sb.toString();
    }
}
