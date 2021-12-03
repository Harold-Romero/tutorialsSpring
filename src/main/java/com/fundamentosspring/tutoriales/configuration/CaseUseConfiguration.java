package com.fundamentosspring.tutoriales.configuration;

import com.fundamentosspring.tutoriales.caseuse.GetUser;
import com.fundamentosspring.tutoriales.caseuse.GetUserImplement;
import com.fundamentosspring.tutoriales.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
