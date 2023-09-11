package com.example.stepbackend.global.security.advice;

import com.example.stepbackend.global.advice.AuthControllerAdvice;
import com.example.stepbackend.global.common.response.error.ErrorResponse;
import com.example.stepbackend.global.exception.OAuth2AuthenticationProcessingException;
import com.example.stepbackend.global.security.service.CustomTokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerAdviceUnitTest {

    @Autowired
    private AuthControllerAdvice authControllerAdvice;

    @Autowired
    private CustomTokenService customTokenService;

    @DisplayName("토큰이 없을 경우 정상적으로 401 상태 코드를 응답하는 지 테스트")
    @Test
    public void testHandleAuthenticationExceptionReturn401() {
        String token = "";

        OAuth2AuthenticationProcessingException exception = assertThrows(OAuth2AuthenticationProcessingException.class, () -> customTokenService.validateToken(token));
        ResponseEntity<ErrorResponse> responseEntity = authControllerAdvice.handleAuthenticationException(exception);
        assertEquals(401, responseEntity.getStatusCodeValue());
    }

}
