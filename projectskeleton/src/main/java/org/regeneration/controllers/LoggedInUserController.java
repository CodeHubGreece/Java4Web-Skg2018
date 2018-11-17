package org.regeneration.controllers;

import org.regeneration.exceptions.NoLoggedInUserException;
import org.regeneration.models.User;
import org.regeneration.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoggedInUserController {

    private final UserRepository userRepository;

    @Autowired
    public LoggedInUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/user")
    public User getLoggedInUser(Principal principal) {
        if (principal == null) {
            throw new NoLoggedInUserException();
        } else {
            User loggedInUser = userRepository.findByUsername(principal.getName());
            return loggedInUser;
        }
    }
}
