package com.techforb.unicomer.services;

import com.techforb.unicomer.entitites.*;
import com.techforb.unicomer.entitites.enums.DocumentType;
import com.techforb.unicomer.entitites.enums.Role;
import com.techforb.unicomer.entitites.request.LoginRequest;
import com.techforb.unicomer.entitites.request.RegisterRequest;
import com.techforb.unicomer.entitites.response.AuthResponse;
import com.techforb.unicomer.handler.ResponseHandler;
import com.techforb.unicomer.validation.AuthValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Autowired
    private AuthValidation authValidation;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getDocument(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getDocument()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

    }

    public ResponseEntity<Object> register(RegisterRequest request) {

        ResponseEntity validationResponse = authValidation.registerValidation(request);
        if (validationResponse != null){
            return validationResponse;
        }

        User user = User.builder()
            .username(request.getDocument())
            .password(passwordEncoder.encode( request.getPassword()))
            .documentType(DocumentType.valueOf(request.getDocumentType()))
            .name(request.getName())
            .surname(request.getSurname())
            .role(Role.USER)
            .build();

        userRepository.save(user);

        return ResponseHandler.generateResponse(null, HttpStatus.OK, AuthResponse.builder()
                                    .token(jwtService.getToken(user))
                                    .build());
    }

    public boolean existsByUsername(String document) {
        return userRepository.existsByUsername(document);
    }
}
