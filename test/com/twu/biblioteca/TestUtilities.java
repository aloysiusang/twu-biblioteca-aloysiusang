package com.twu.biblioteca;

import java.io.*;

/**
 * Created by aloysiusang on 16/6/15.
 */
public class TestUtilities {
    public static void redirectOutput() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    public static void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    public static void resetStreams() {
        System.setIn(new FileInputStream(FileDescriptor.in));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }
}
