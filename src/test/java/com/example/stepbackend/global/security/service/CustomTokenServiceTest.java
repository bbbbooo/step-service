package com.example.stepbackend.global.security.service;

import com.example.stepbackend.aggregate.entity.enumType.Role;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest

public class CustomTokenServiceTest {

    @Autowired
    private CustomTokenService customTokenService;

    @Value("${app.auth.tokenSecret}")
    private String JWT_SECRET;

    @Value("${app.auth.tokenExpirationMsec}")
    private int JWT_EXPIRATION_MS;

    private static Stream<Arguments> getTokenInfo() {
        return Stream.of(
                Arguments.of(
                        1L, Role.USER.name()
                )
        );
    }

    @DisplayName("JWT 토큰의 user id가 정상적으로 생성되는 지 확인")
    @ParameterizedTest
    @MethodSource("getTokenInfo")
    void TestAccessTokenUsesUserId(long userId, String userRole) {

        String tokenValue = customTokenService.createToken(userId, userRole);
        String subject = Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(tokenValue)
                .getBody()
                .getSubject();

        Assertions.assertEquals(Long.parseLong(subject), userId);
    }

    @DisplayName("JWT 토큰의 user Role이 정상적으로 생성되는지 확인")
    @ParameterizedTest
    @MethodSource("getTokenInfo")
    void TestAccessTokenUsesMemberRole (long userId, String userRole) {

        String tokenValue = customTokenService.createToken(userId, userRole);
        String tokenRole = (String) Jwts.parserBuilder()
                .setSigningKey(JWT_SECRET)
                .build()
                .parseClaimsJws(tokenValue)
                .getBody()
                .get("role");

        Assertions.assertEquals(tokenRole, userRole);
    }

    @DisplayName("JWT 토큰을 통해 유저의 ID를 정상적으로 출력되는 지 TEST")
    @ParameterizedTest
    @MethodSource("getTokenInfo")
    void testGetUserIdFromToken(long userId, String userRole) {
        String tokenValue = customTokenService.createToken(userId, userRole);
        Long getUserIdFromToken = customTokenService.getUserIdFromToken(tokenValue);
        Assertions.assertEquals(userId, getUserIdFromToken);
    }
}
