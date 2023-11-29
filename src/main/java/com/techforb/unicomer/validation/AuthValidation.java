package com.techforb.unicomer.validation;

import com.techforb.unicomer.entitites.request.LoginRequest;
import com.techforb.unicomer.entitites.request.RegisterRequest;
import com.techforb.unicomer.handler.ResponseHandler;
import com.techforb.unicomer.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AuthValidation {

    private static final String MISSING_PARAM_BODY = "Falta algun parametro";
    private static final String EMPTY_PARAM_BODY = "Algún parametro está vacio";
    private static final String PASSWORD_INVALIDATED = "Password debe ser minimo 8 digitos, un numero, un caracter especial y una mayúscula";
    private static final String USER_NOT_FOUND = "Usuario no encontrado";

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity loginValidation(LoginRequest request) {
        if (request.getDocument() == null || request.getPassword() == null){
            return ResponseHandler.generateResponse(MISSING_PARAM_BODY, HttpStatus.BAD_REQUEST, null);
        }
        if (request.getDocument() == "" || request.getPassword() == ""){
            return ResponseHandler.generateResponse(EMPTY_PARAM_BODY, HttpStatus.BAD_REQUEST, null);
        }
        if (!userRepository.existsByUsername(request.getDocument())){
            return ResponseHandler.generateResponse(USER_NOT_FOUND, HttpStatus.BAD_REQUEST, null);
            
        }
        return null;
    }

    public ResponseEntity registerValidation(RegisterRequest request) {
        if (request.getName() == null || request.getPassword() == null || request.getSurname() == null || request.getDocument() == null){
            return ResponseHandler.generateResponse(MISSING_PARAM_BODY, HttpStatus.BAD_REQUEST, null);
        }
        if (request.getName() == "" || request.getPassword() == "" || request.getSurname() == "" || request.getDocument() == ""){
            return ResponseHandler.generateResponse(EMPTY_PARAM_BODY, HttpStatus.BAD_REQUEST, null);
        }
        if (isNotValidatedPassword(request.getPassword())) {
            return ResponseHandler.generateResponse(PASSWORD_INVALIDATED, HttpStatus.BAD_REQUEST, null);
        }

        return null;
    }

    private boolean isNotValidatedPassword(String password) {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        return !m.matches();
    }
}
