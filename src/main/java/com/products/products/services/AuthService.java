package com.products.products.services;

import com.products.products.dto.SignInDTO;
import com.products.products.dto.SignupDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AuthService {

    ResponseEntity<Map<String,Object>> signin(SignInDTO sign);
    ResponseEntity<Map<String,Object>> signup(SignupDTO signup);
    ResponseEntity<Map<String,Object>> refresh(String refreshToken);
}
