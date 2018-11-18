package org.regeneration.controllers;

import org.regeneration.exceptions.NoLoggedInUserException;
import org.regeneration.models.User;
import org.regeneration.repositories.UserRepository;
import org.regeneration.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoggedInUserController {

    private final UserService userService;

    @Autowired
    public LoggedInUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public User getLoggedInUser(Principal principal) {
        if (principal == null) {
            throw new NoLoggedInUserException();
        } else {
            return userService.getUserByUsername(principal.getName());
        }
    }
}
