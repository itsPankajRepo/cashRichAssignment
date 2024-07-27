package com.assignment.cashRich.repository;

import com.assignment.cashRich.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserTokenRepository extends JpaRepository<UserToken,Long> {

    Optional<UserToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query(value = "UPDATE user_token SET token = :token WHERE username = :username", nativeQuery = true)
    void updateToken(@Param("token") String token, @Param("username") String username);

    Optional<UserToken> findByUsername(String username);

    @Query(value = "Select count(*) from user_token WHERE token = :token", nativeQuery = true)
    int isTokenPresent(@Param("token")String token);
}
