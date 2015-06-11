package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by aloysiusang on 10/6/15.
 */
public class BibliotecaAppTest {
    @Test
    public void testWelcomeMessage() throws Exception {
        BibliotecaApp app = new BibliotecaApp();
        assertEquals("Welcome to Biblioteca!", app.MESSAGE_WELCOME);
    }

}