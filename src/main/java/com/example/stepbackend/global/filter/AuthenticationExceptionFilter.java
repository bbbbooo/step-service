package com.example.stepbackend.global.filter;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationExceptionFilter extends OncePerRequestFilter {

    private final HandlerExceptionResolver resolver;

    public AuthenticationExceptionFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response); // TokenAuthenticationFilter 이동
        } catch (AuthenticationException ex) {
            // TokenAuthenticationFilter 예외 발생하면 바로 setErrorResponse 호출
            System.out.println("setErrorResponse 호출");
            setErrorResponse(request, response, ex);
        }

    }

    private void setErrorResponse(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {

        //        String requestUrl = null;
//        if(ex.getClass() == UserNotFoundException.class) {
//            requestUrl = "/oauth2/authorize/github";
//        } else if (ex.getClass() == OAuth2AuthenticationProcessingException.class) {
//            requestUrl = "추후 access token을 재발생하는 api url";
//        }

//        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
//        // response 상태 코드를 401로 설정
//        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        final Map<String, Object> body = new HashMap<>();
//        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
//        body.put("error", "Unauthorized");
//        body.put("request_url", requestUrl);
//        body.put("message", ex.getMessage());
//        body.put("path", request.getServletPath());
//        final ObjectMapper mapper = new ObjectMapper();
//        mapper.writeValue(response.getOutputStream(), body);


        resolver.resolveException(request, response, null, exception);
    }
}
