package org.regeneration.security;

import org.regeneration.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MyUserDetails implements UserDetails {

    private final User user;

    private final Map<String, UserRole> userRolesMap;

    public MyUserDetails(User user) {
        this.user = user;
        this.userRolesMap = createPrivilegedUsersRoles(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = userRolesMap.getOrDefault(user.getUsername(), UserRole.USER).name();
        return Collections.singletonList(
                new SimpleGrantedAuthority("ROLE_" + userRole));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private Map<String, UserRole> createPrivilegedUsersRoles(User user) {
        HashMap<String, UserRole> map = new HashMap<>();
        map.put("admin", UserRole.ADMIN);
        map.put("moderator", UserRole.MODERATOR);
        return map;
    }

}
