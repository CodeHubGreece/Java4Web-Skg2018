package org.regeneration.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class User {

    private Long id;
    private String username;
    private String password;
    private String role;

    public User() {}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }
    public void setId(Long id) { this.id = id; }

    @Column(unique = true, nullable = false)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonIgnore()
    public String getPassword() { return password; }
    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Transient
    public String getRole() { return role; }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }


}
