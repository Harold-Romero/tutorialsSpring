package com.fundamentosspring.tutoriales.caseuse;

import com.fundamentosspring.tutoriales.entity.User;
import com.fundamentosspring.tutoriales.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {

    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
