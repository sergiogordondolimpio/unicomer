package com.techforb.unicomer.controllers;

import com.techforb.unicomer.entitites.response.AuthResponse;
import com.techforb.unicomer.services.AuthService;
import com.techforb.unicomer.entitites.request.LoginRequest;
import com.techforb.unicomer.entitites.request.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping(value = "welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }


    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}
