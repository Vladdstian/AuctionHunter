package com.vladnichifor.auctionhunter.controllers;

import com.vladnichifor.auctionhunter.models.LoginRequest;
import com.vladnichifor.auctionhunter.services.AuthService;
import com.vladnichifor.auctionhunter.models.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        try {
            return ResponseEntity.ok(authService.register(registerRequest));
        } catch (Exception e) {
            return new ResponseEntity<>("Something went wrong", HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.ok(authService.login(loginRequest));
        } catch (Exception e) {
            return new ResponseEntity<>("Email or password incorrect", HttpStatusCode.valueOf(HttpStatus.BAD_REQUEST.value()));
        }
    }
}
