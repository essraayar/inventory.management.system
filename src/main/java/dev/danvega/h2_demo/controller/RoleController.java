package dev.danvega.h2_demo.controller;


import dev.danvega.h2_demo.service.UserService;
import dev.danvega.h2_demo.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = {"http://localhost:8080/","http://localhost:8081/"})
@RequiredArgsConstructor

public class RoleController {

    private final UserService userService;

    @PostMapping
    public Role save(@RequestBody Role role) {
        return userService.save(role);
    }
}
