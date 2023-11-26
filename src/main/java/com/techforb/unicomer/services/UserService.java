package com.techforb.unicomer.services;

import com.techforb.unicomer.entitites.User;
import com.techforb.unicomer.entitites.response.UserResponse;
import com.techforb.unicomer.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    public User getUserByDocument(String document) {
        return userRepository.findByUsername(document).get();
    }

    public UserResponse getUserByAuth(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return UserResponse.builder()
                .id(user.getId())
                .document(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .documentType(user.getDocumentType())
                .role(user.getRole())
                .build();
    }
}
