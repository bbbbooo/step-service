package com.example.stepbackend.global.security.service;

import com.example.stepbackend.aggregate.entity.enumType.Role;
import com.example.stepbackend.global.security.token.UserPrincipal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
class IssueTokenServiceTest {

    static CustomTokenService customTokenService;
    static IssueTokenService issueTokenService;
    static UserPrincipal userPrincipal;
    static String defaultAccessToken;

    @BeforeAll
    static void beforeAll() {
        customTokenService = Mockito.mock(CustomTokenService.class);
        issueTokenService = Mockito.mock(IssueTokenService.class);

        userPrincipal = UserPrincipal.builder(1L, "팽구리", Role.USER.name(), "profile").build();
        defaultAccessToken = customTokenService.createToken(userPrincipal.getId(), userPrincipal.getRole());
    }

    @DisplayName("UserPrincipal을 통해 정상적인 토큰이 발행 되는 지 테스트")
    @Test
    @WithAnonymousUser
    void testIssueTokenByUserPrincipal() {
        when(issueTokenService.issueTokenByUserPrincipal(userPrincipal)).thenReturn(anyString());

        Assertions.assertNotNull(issueTokenService.issueTokenByUserPrincipal(userPrincipal));
    }

    @DisplayName("잘못된 토큰 값 입력 시 IllegalArgumentException이 발생 하는지 테스트")
    @Test
    void testIllegalArgumentExceptionInIssueTokenByAccessToken() {
        when(issueTokenService.issueTokenByAccessToken(""))
                .thenThrow(new IllegalArgumentException());
        assertThrows(IllegalArgumentException.class, () -> issueTokenService.issueTokenByAccessToken(""));
    }

    @DisplayName("Access Token을 통해 정상적으로 토큰 발행이 되는지 테스트")
    @Test
    void testIssueTokenByAccessToken() {
        when(issueTokenService.issueTokenByAccessToken(defaultAccessToken)).thenReturn(anyString());
        Assertions.assertNotNull(issueTokenService.issueTokenByAccessToken(defaultAccessToken));
    }
}
