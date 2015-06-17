package com.twu.biblioteca;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class LoginCredential {
    private final String libraryNumber;
    private final String password;

    public LoginCredential(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginCredential that = (LoginCredential) o;

        if (!libraryNumber.equals(that.libraryNumber)) return false;
        return password.equals(that.password);

    }

    @Override
    public int hashCode() {
        int result = libraryNumber.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }
}
