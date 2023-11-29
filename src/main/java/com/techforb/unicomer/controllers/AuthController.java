package com.techforb.unicomer.controllers;

import com.techforb.unicomer.entitites.response.AuthResponse;
import com.techforb.unicomer.handler.ResponseHandler;
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

    @PostMapping(value = "register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request)
    {
        return authService.register(request);
    }
}
