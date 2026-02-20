package com.products.products.repository;

import com.products.products.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Integer> {
    Optional<RefreshToken> findRefreshTokenByToken(String token);
    void deleteByUsername(String username);
    RefreshToken findRefreshTokenByUsername(String username);
}
