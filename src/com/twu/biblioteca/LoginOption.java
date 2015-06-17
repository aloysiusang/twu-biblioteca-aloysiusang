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
    public String execute(User user, AllLibraryStores libraryStores) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            LoginCredential loginCredential = getLoginCredential(br);
            user = getUserFromVault(loginCredential);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return user!=null? "Login successful." : "Invalid credentials.";
    }

    private User getUserFromVault(LoginCredential loginCredential) {
        User user;UserAccountVault userAccountVault = UserAccountVault.getInstance();
        user = userAccountVault.retrieveUser(loginCredential);
        return user;
    }

    private LoginCredential getLoginCredential(BufferedReader br) throws IOException {
        System.out.print("Username: ");
        String username = br.readLine();
        System.out.print("Password: ");
        String password = br.readLine();
        return new LoginCredential(username, password);
    }
}
