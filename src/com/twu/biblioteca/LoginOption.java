package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class LoginOption extends MainMenuOption {
    public LoginOption() {
        super("Login");
    }

    @Override
    public String execute(UserAccountManager userAccountManager, AllLibraryStores libraryStores) {
        boolean success = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            LoginCredential loginCredential = getLoginCredential(br);
            success = attemptLoginWithCredentials(userAccountManager, loginCredential);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return success ? "Login successful." : "Invalid credentials.";
    }

    private boolean attemptLoginWithCredentials(UserAccountManager userAccountManager, LoginCredential loginCredential) {
        boolean success = userAccountManager.login(loginCredential);
        return success;
    }

    private LoginCredential getLoginCredential(BufferedReader br) throws IOException {
        System.out.print("Username: ");
        String username = br.readLine();
        System.out.print("Password: ");
        String password = br.readLine();
        return new LoginCredential(username, password);
    }
}
