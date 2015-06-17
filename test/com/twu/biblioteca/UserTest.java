package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 17/6/15.
 */
public class UserTest {
    @Test
    public void testFormatUserInformation() throws Exception {
        User user = new User("user1", "user1@user1.com", "11111111");
        String formattedUser = user.formatUserString(new UserInformationFormatter());
        assertEquals("Name           :                         user1\n" +
                "Email          :               user1@user1.com\n" +
                "Phone Number   :                      11111111\n", formattedUser);
    }
}