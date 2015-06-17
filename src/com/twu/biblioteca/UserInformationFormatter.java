package com.twu.biblioteca;

import java.util.Formatter;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserInformationFormatter {

    public static final String FORMAT_STYLE = "%-15s:%30s";

    public String format(User user) {
        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);
        formatField("Name", user.getName(), formatter);
        formatField("Email", user.getEmail(), formatter);
        formatField("Phone Number", user.getPhoneNumber(), formatter);
        return sb.toString();
    }

    private void formatField(String fieldType, String fieldValue, Formatter formatter) {
        formatter.format(FORMAT_STYLE, fieldType, fieldValue);
        formatter.format(System.getProperty("line.separator"));
    }
}
