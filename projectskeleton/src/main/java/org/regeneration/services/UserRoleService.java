package org.regeneration.services;

import org.regeneration.models.User;
import org.regeneration.security.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserRoleService {

    private Map<String, UserRole> userRolesMap;

    @PostConstruct
    void initUserRoles() {
        userRolesMap = createPrivilegedUsersRoles();
    }

    private Map<String, UserRole> createPrivilegedUsersRoles() {
        Map<String, UserRole> map = new HashMap<>();
        map.put("admin", UserRole.ADMIN);
        map.put("moderator", UserRole.MODERATOR);
        return map;
    }

    public UserRole getRole(User loggedInUser) {
        return userRolesMap.getOrDefault(loggedInUser.getUsername(), UserRole.USER);
    }
}
