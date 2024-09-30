package dev.danvega.h2_demo.controller;


import dev.danvega.h2_demo.model.User;
import dev.danvega.h2_demo.repository.UserRepository;
import dev.danvega.h2_demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = {"http://localhost:8080/","http://localhost:8081/"})
@RequiredArgsConstructor


public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.findByUsername(username);
        return ResponseEntity.ok(Collections.singletonMap("role", user.getRole().getRoleName()));
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User userInfo){
        String kullaniciSifresi = userInfo.getPassword();
        String sifrelenmisPassword = new BCryptPasswordEncoder().encode(kullaniciSifresi);
        userInfo.setPassword(sifrelenmisPassword);
        userRepository.save(userInfo);
    }

}
