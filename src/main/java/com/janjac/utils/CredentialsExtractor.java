package com.janjac.utils;

import com.janjac.exceptions.CredentialsFileException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CredentialsExtractor {
    public static Credentials extract(String input) throws CredentialsFileException {
        Pattern pattern = Pattern.compile("Username: (.+)\nPassword: (.+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String username = matcher.group(1);
            String password = matcher.group(2);
            return new Credentials(username, password);
        } else {
            throw new CredentialsFileException("Pattern not found in the input string.");
        }
    }
}
