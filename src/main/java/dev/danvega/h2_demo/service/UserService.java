package dev.danvega.h2_demo.service;

import dev.danvega.h2_demo.model.Role;
import dev.danvega.h2_demo.model.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username);
    User findByUsername(String username);

    Role save(Role role);
    User save(User user);
}
