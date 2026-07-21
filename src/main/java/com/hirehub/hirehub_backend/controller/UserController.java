package com.hirehub.hirehub_backend.controller;

import com.hirehub.hirehub_backend.dto.LoginRequest;
import com.hirehub.hirehub_backend.dto.LoginResponse;
import com.hirehub.hirehub_backend.dto.RegisterRequest;
import com.hirehub.hirehub_backend.dto.UserResponse;
import com.hirehub.hirehub_backend.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.hirehub.hirehub_backend.dto.LoginRequest;
import com.hirehub.hirehub_backend.dto.LoginResponse;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponse registerUser(@Valid @RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
    @PostMapping("/login")
public LoginResponse login(@Valid @RequestBody LoginRequest request) {
    return userService.login(request);
}
    
}