package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

    Optional<Token> findTokenByUser_Id(long userId);

    Optional<Token> findTokenByAccessToken(String accessToken);
}
