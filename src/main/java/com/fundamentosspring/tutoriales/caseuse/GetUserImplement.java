package com.fundamentosspring.tutoriales.caseuse;

import com.fundamentosspring.tutoriales.entity.User;
import com.fundamentosspring.tutoriales.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{

    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
