package com.products.products.services;

import com.products.products.entity.RefreshToken;

public interface RefreshTokenService {
    long REFRESH_TOKEN_DURATION = 7 * 24 * 60 * 60;
    public RefreshToken createRefreshToken(String username);
    public RefreshToken verifyExpiration(RefreshToken token);
}
