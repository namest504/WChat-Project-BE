package com.list.WChatProject.repository;

import com.list.WChatProject.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByKey(String key);
    void deleteByKey(String key);
    Long findRefreshTokenByValue(String value);
}
