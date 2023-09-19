package com.example.stepbackend.global.security.controller;

import com.example.stepbackend.aggregate.dto.user.CreateUserDTO;
import com.example.stepbackend.aggregate.dto.user.SignUpDTO;
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
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping("/signin")
    public String kakaoLogin(String code) {
        return null;
    }

    @PostMapping ("/signup")
    public ModelAndView signup(@RequestBody SignUpDTO signUpDTO) {

        // 1. 회원정보 받기, form, json
        System.out.println("sigupDTO" + signUpDTO);
        // 2. 회원정보를 token으로 말기

        // 3. 회원정보를 db에 저장

        // 4. token으로 만 정보(access_token) db에 저장

        // 5. 로그인

        // 6. 로그인이 성공하면 토큰을 localStorage or cookie에 담아준다

        // 7. 매 요청마다 헤더에 토큰을 담아준다

        // 7-1. 회원정보를 토큰에서 파싱해야 되는 경우면 jwt.decoded(); *라이브러리 다운로드 필요

        return null;
    }

    @GetMapping("/mypage")
    public ModelAndView mypage(@CurrentUser UserPrincipal userPrincipal, ModelAndView mv) {


        mv.setViewName("/auth/mypage");

        return mv;
    }
}
