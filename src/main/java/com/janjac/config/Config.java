package com.janjac.config;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {
    public static String getKey(String key) {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/java/com/janjac/config/.properties")) {
            properties.load(input);
            return properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
