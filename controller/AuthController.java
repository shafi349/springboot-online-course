
package com.example.onlinecourse.controller;

import com.example.onlinecourse.dto.AuthRequest;
import com.example.onlinecourse.dto.AuthResponse;
import com.example.onlinecourse.dto.RegisterRequest;
import com.example.onlinecourse.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Validated @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().body("User registered");
    }
}
