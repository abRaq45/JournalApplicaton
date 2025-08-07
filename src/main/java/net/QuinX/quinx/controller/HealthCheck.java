package net.QuinX.quinx.controller;

import net.QuinX.quinx.entity.User;
import net.QuinX.quinx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class HealthCheck {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "ok";
    }

    @PostMapping("/create-user")
    public String createUser(@RequestBody User user) {
        if (userService.findByUsername(user.getUsername()) != null) {
            return "Username already exists.";
        }

        userService.saveNewUser(user);
        return "User created successfully.";
    }
}
