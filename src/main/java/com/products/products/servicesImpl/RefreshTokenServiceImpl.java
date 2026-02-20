package com.products.products.servicesImpl;

import com.products.products.entity.RefreshToken;
import com.products.products.repository.RefreshTokenRepository;
import com.products.products.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository repository;

    @Override
    public RefreshToken createRefreshToken(String username) {

        RefreshToken token = repository.findRefreshTokenByUsername(username);
        if(token!=null) return token;
        token=new RefreshToken();
        token.setUsername(username);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusSeconds(REFRESH_TOKEN_DURATION));
        return repository.save(token);
    }

    @Override
    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(Instant.now())) {
            repository.delete(token);
            return null;
        }
        return token;
    }
}
