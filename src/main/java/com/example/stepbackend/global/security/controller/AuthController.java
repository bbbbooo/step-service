package com.example.stepbackend.global.security.controller;

import com.example.stepbackend.global.common.annotation.CurrentUser;
import com.example.stepbackend.global.common.response.api.ApiResponse;
import com.example.stepbackend.global.security.dto.AuthResponse;
import com.example.stepbackend.global.security.dto.AuthResponseBody;
import com.example.stepbackend.global.security.service.IssueTokenService;
import com.example.stepbackend.global.security.token.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final IssueTokenService issueTokenService;

    @Autowired
    public AuthController(IssueTokenService issueTokenService) {
        this.issueTokenService = issueTokenService;
    }

    @PostMapping("/token")
    public ResponseEntity<?> issueToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String bearerToken) {
        String accessToken = bearerToken.substring(7);

        String issuedToken = issueTokenService.issueTokenByAccessToken(accessToken);

        ApiResponse apiResponse = new ApiResponse()
                .setStatus(HttpStatus.CREATED.value())
                .setMessage("새로운 토큰 발급 완료")
                .setTimestamp(LocalDateTime.now());

        AuthResponseBody authResponseBody = new AuthResponseBody()
                .setAccessToken(issuedToken);

        AuthResponse authResponse = new AuthResponse()
                .setApiResponse(apiResponse)
                .setBody(authResponseBody);

        return ResponseEntity.status(HttpStatus.CREATED).body(authResponse);
    }

    @GetMapping("name")
    public ResponseEntity<?> issueToken(@CurrentUser UserPrincipal userPrincipal) {
        System.out.println("userPrincipal = " + userPrincipal);
        System.out.println("userPrincipal.getNickname() = " + userPrincipal.getNickname());

        Map<String , String> responseBody = new HashMap<>();
        responseBody.put("nickname", userPrincipal.getNickname());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
