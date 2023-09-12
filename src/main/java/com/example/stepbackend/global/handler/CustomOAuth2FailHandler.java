package com.example.stepbackend.global.handler;

import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/*
    SimpleUrlAuthenticationFailureHandler
    기본 인증 실패 핸들러, 인증 실패 시 어떤 URL로 redirect 할 지 설정하는데 사용한다.
*/
@Component
public class CustomOAuth2FailHandler extends SimpleUrlAuthenticationFailureHandler {


}
