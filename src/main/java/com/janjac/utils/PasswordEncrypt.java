package com.janjac.utils;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordEncrypt {
        public static String hashPassword(String password) {
            return BCrypt.hashpw(password, BCrypt.gensalt());
        }
        public static boolean checkPassword(String enteredPassword, String hashedPassword) {
            return BCrypt.checkpw(enteredPassword, hashedPassword);
        }
}
