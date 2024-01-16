package com.janjac.utils;

import com.janjac.exceptions.InvalidFileExtension;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class UiFileReader {
    String title;
    String fileExt;

    public UiFileReader(String title, String fileExt) {
        this.title = title;
        this.fileExt = fileExt;
    }

    public String readFile() throws InvalidFileExtension{
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(this.title);
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if(!verifyFileExtension(selectedFile.getName())) throw new InvalidFileExtension("File must be type of "+this.fileExt);
        if (selectedFile != null) {
            return readFile(selectedFile);
        } else {
            throw new InvalidFileExtension("No file selected...");
        }
    }

    private static String readFile(File file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    private String getFileExtension(String filePath) {
        // Get the file extension from the file path
        int lastDotIndex = filePath.lastIndexOf(".");
        if (lastDotIndex != -1 && lastDotIndex < filePath.length() - 1) {
            return filePath.substring(lastDotIndex).toLowerCase();
        }
        return ""; // Return an empty string if no extension found
    }

    private boolean verifyFileExtension(String filePath) {
        return this.getFileExtension(filePath).equals(this.fileExt);
    }
}
