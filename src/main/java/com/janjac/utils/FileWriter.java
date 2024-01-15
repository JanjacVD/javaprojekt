package com.janjac.utils;

import java.io.IOException;
import java.io.PrintWriter;

public class FileWriter {
    private static final String USER_FILES_FOLDER = "user_files";

    public static void createUserFile(String username, String password) {
        String fileName = USER_FILES_FOLDER + "/" + username + ".txt";  // Include folder path

        try (PrintWriter writer = new PrintWriter(new java.io.FileWriter(fileName))) {
            writer.println("Username: " + username);
            writer.println("Password: " + password);
            System.out.println("User file created: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
