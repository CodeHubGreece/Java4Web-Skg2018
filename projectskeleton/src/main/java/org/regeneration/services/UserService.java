package org.regeneration.services;

import org.regeneration.exceptions.UserNotFoundException;
import org.regeneration.models.User;
import org.regeneration.repositories.UserRepository;
import org.regeneration.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.role != 'ADMIN'")
    public List<User> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::populateRole)
                .collect(Collectors.toList());
    }

    @PostAuthorize("hasRole('ROLE_ADMIN') or returnObject.username == authentication.principal.username")
    public User getUserById(Long id) {
        return getUserByIdOrError(id);
    }

    public User getUserByUsername(String username) {
        User loggedInUser = userRepository.findByUsername(username);
        return populateRole(loggedInUser);

    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MODERATOR')")
    public void deleteUser(Long id) {
        User user = getUserByIdOrError(id);
        userRepository.delete(user);
    }

    public UserRole getUserRole(User user) {
       return null;
    }

    private User populateRole(User user) {
        user.setRole(userRoleService.getRole(user).name());
        return user;
    }

    private User getUserByIdOrError(Long id) {
        return userRepository.findById(id)
                .map(this::populateRole)
                    .orElseThrow(() -> new UserNotFoundException(id));
    }

}
