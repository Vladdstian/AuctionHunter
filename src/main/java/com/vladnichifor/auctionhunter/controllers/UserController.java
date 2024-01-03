package com.vladnichifor.auctionhunter.controllers;

import com.vladnichifor.auctionhunter.models.User;
import com.vladnichifor.auctionhunter.services.UserService;
import com.vladnichifor.auctionhunter.utils.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.createUser(user);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("User created successfully")
                .data(createdUser)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("Users retrieved successfully")
                .data(users)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@Positive @PathVariable Long id) {
        User user = userService.getUserById(id);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("User retrieved successfully")
                .data(user)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getUserByEmail(@Email @PathVariable String email) {
        User user = userService.getUserByEmail(email);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("User retrieved successfully")
                .data(user)
                .build();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@Positive @PathVariable Long id, @Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("User updated successfully")
                .data(updatedUser)
                .build();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUserById(@Positive @PathVariable Long id) {
        userService.deleteUserById(id);
        ApiResponse response = ApiResponse.builder()
                .status(200)
                .message("User deleted successfully")
                .build();
        return ResponseEntity.ok(response);
    }
}
