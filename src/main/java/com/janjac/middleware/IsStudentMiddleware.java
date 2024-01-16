package com.janjac.middleware;

import com.janjac.interfaces.MiddlewareInterface;
import com.janjac.models.User;

public class IsStudentMiddleware implements MiddlewareInterface<User> {
    @Override
    public boolean authorize(User user){
        return user.isStudent();
    }
}
