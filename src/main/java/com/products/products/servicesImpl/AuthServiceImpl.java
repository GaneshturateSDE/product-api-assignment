package com.products.products.servicesImpl;

import com.products.products.dto.SignInDTO;
import com.products.products.dto.SignupDTO;
import com.products.products.entity.RefreshToken;
import com.products.products.entity.User;
import com.products.products.repository.RefreshTokenRepository;
import com.products.products.repository.UserRepository;
import com.products.products.services.AuthService;
import com.products.products.services.RefreshTokenService;
import com.products.products.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    RefreshTokenService rts;

    @Autowired
    RefreshTokenRepository rtr;

    @Autowired
    UserRepository ur;


    @Override
    public ResponseEntity<Map<String, Object>> signin(SignInDTO sign) {
        User user=ur.findUserByEmail(sign.getEmail());
        if(user==null)
            return ResponseEntity.ok(Map.of("message","Account Not found "));

        if(!sign.getPassword().equals(user.getPassword()))
            return ResponseEntity.ok(Map.of("message","Invalid Credentials! "));


        String accessToken = JwtUtil.generateToken(sign.getEmail());
        RefreshToken refreshToken = rts.createRefreshToken(sign.getEmail());

        return ResponseEntity.ok(Map.of(
                "accessToken", accessToken,
                "refreshToken", refreshToken.getToken()
        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> signup(SignupDTO signup) {
        User user=ur.findUserByEmail(signup.getEmail());
        if(user!=null)
             return ResponseEntity.ok(Map.of("message","Email Already Used"));

        user=new User();
        user.setEmail(signup.getEmail());
        user.setPassword(signup.getPassword());
        user.setFullname(signup.getFullname());

        ur.save(user);

        return ResponseEntity.ok(Map.of(
                "message", "Signup Success!"
        ));
    }

    @Override
    public ResponseEntity<Map<String, Object>> refresh(String refreshToken) {

        RefreshToken token = (RefreshToken) rtr.findRefreshTokenByToken(refreshToken).get();
        System.out.println("------>"+token.toString());
        token=rts.verifyExpiration(token);
        if(token==null) return  ResponseEntity.status(401).body(Map.of("message","token expired"));
        String newAccessToken = JwtUtil.generateToken(token.getUsername());

        return ResponseEntity.ok(Map.of(
                "accessToken", newAccessToken
        ));
    }
}
