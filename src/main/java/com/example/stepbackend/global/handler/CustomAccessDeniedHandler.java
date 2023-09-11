package com.example.stepbackend.global.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
    AccessDeniedHandler 클래스는 Spring Security 프레임워크에서 사용되는 인터페이스
    보안 규칙을 적용, 특정 리소스 및 액세스 원한이 없을 경우의 처리를 정의한다.
    예를 들어 권한이 없으면 로그인 페이지로 redirect / 오류 메세지 출력
*/
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final HandlerExceptionResolver resolver;

    @Autowired
    // @Qualifier : 어떤 빈을 주입할 지 명시적으로 지정하는 방식
    public CustomAccessDeniedHandler(@Qualifier("handlerExceptionResolver")HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        resolver.resolveException(request, response, null, accessDeniedException);
    }

}
