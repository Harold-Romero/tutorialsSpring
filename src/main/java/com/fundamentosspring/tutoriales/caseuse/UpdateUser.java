package com.fundamentosspring.tutoriales.caseuse;

import com.fundamentosspring.tutoriales.entity.User;
import com.fundamentosspring.tutoriales.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UpdateUser {

    private UserService userService;

    public UpdateUser(UserService userService) {
        this.userService = userService;
    }

    public User update(User newUser, Long id) {
        return userService.update(newUser, id);
    }
}
