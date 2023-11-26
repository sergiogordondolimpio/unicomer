package com.techforb.unicomer.entitites.response;

import com.techforb.unicomer.entitites.enums.DocumentType;
import com.techforb.unicomer.entitites.enums.Role;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    Integer id;
    String document;
    String name;
    String surname;
    DocumentType documentType;
    Role role;

}
