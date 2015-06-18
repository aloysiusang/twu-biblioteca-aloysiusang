package com.twu.biblioteca;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class User {
    private final String name;
    private final String email;
    private final String phoneNumber;

    public User(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String formatUserString() {
        return UserInformationFormatter.format(this);
    }
}
