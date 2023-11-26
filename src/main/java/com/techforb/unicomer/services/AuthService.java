package com.techforb.unicomer.services;

import com.techforb.unicomer.entitites.*;
import com.techforb.unicomer.entitites.enums.DocumentType;
import com.techforb.unicomer.entitites.enums.Role;
import com.techforb.unicomer.entitites.request.LoginRequest;
import com.techforb.unicomer.entitites.request.RegisterRequest;
import com.techforb.unicomer.entitites.response.AuthResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techforb.unicomer.jwt.JwtService;
import com.techforb.unicomer.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getDocument(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getDocument()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getDocument())
            .password(passwordEncoder.encode( request.getPassword()))
            .documentType(DocumentType.valueOf(request.getDocumentType()))
            .name(request.getName())
            .surname(request.getSurname())
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}
