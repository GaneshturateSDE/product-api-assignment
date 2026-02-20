package com.products.products.controllers;

import com.products.products.dto.SignInDTO;
import com.products.products.dto.SignupDTO;
import com.products.products.services.AuthService;
import com.products.products.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {


    @Autowired
    AuthService as;


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody SignInDTO request) {

      return as.signin(request);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupDTO request) {
        return as.signup(request);
    }

//    @PostMapping("/refresh/{token}")
//    public ResponseEntity<?> refreshToken(@PathVariable("token")  String refreshToken) {
//        return  as.refresh(refreshToken);
//    }

    @PostMapping("/refresh/")
    public ResponseEntity<?> refreshToken(@CookieValue("refreshToken")  String refreshToken) {
        return  as.refresh(refreshToken);
    }


}
