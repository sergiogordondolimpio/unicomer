package com.techforb.unicomer.controllers;

import com.techforb.unicomer.entitites.User;
import com.techforb.unicomer.entitites.response.UserResponse;
import com.techforb.unicomer.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public UserResponse getUser(Authentication authentication) {
        return userService.getUserByAuth(authentication);
    }

}
