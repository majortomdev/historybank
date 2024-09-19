package com.majortomdev.historybank.controllers;

import com.majortomdev.historybank.dto.LoginDto;
import com.majortomdev.historybank.dto.UserDto;
import com.majortomdev.historybank.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        userService.register(userDto);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        String token = userService.login(loginDto);
        return ResponseEntity.ok(token);  // Return JWT token to frontend
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        // Handle logout (clear client-side token)
        return ResponseEntity.ok("Logged out successfully");
    }
}
