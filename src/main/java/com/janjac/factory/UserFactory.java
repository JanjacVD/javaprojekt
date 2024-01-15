package com.janjac.factory;

import com.github.javafaker.Faker;
import com.janjac.abstractions.Factory;
import com.janjac.models.User;
import com.janjac.utils.FileWriter;
import com.janjac.utils.PasswordEncrypt;

import java.util.Random;

public class UserFactory extends Factory<User> {
    Faker faker = new Faker();
    Random random = new Random();

    @Override
    protected User create() {
        String username = faker.name().username();
        User mockUser = new User("name", faker.name().firstName(), "username", username, "hashedPassword", PasswordEncrypt.hashPassword("password"), "lastName", faker.name().lastName(), "isStudent", random.nextBoolean());
        FileWriter.createUserFile(username, "password");
        return mockUser;
    }
}
