package net.QuinX.quinx.controller;

import net.QuinX.quinx.entity.User;
import net.QuinX.quinx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users") // ✅ RESTful naming
public class UserController {

    @Autowired
    private UserService userservice;

    // ✅ Get all users
    @GetMapping
    public List<User> getAllUsers() {
        return userservice.getall();
    }

    // ✅ Register new user (with password encoding)
    @PostMapping("/user")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if username already exists
        if (userservice.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.badRequest().body("Username already taken.");
        }

        userservice.saveNewUser(user);
        return ResponseEntity.ok("User registered successfully.");
    }
}
