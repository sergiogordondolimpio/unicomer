package com.techforb.unicomer.entitites.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String document;
    String password;
    String documentType;
    String name;
    String surname;
}
