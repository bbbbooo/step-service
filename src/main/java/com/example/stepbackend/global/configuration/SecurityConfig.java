package com.example.stepbackend.global.configuration;

import com.example.stepbackend.aggregate.entity.enumType.Role;
import com.example.stepbackend.global.filter.AuthenticationExceptionFilter;
import com.example.stepbackend.global.filter.NullPointExceptionFilter;
import com.example.stepbackend.global.filter.TokenAuthenticationFilter;
import com.example.stepbackend.global.handler.CustomAccessDeniedHandler;
import com.example.stepbackend.global.handler.CustomOAuth2FailHandler;
import com.example.stepbackend.global.handler.CustomOAuth2SuccessHandler;
import com.example.stepbackend.global.security.service.CustomOAuth2UserService;
import com.example.stepbackend.global.security.service.CustomTokenService;
import com.example.stepbackend.global.security.service.CustomUserDetailService;
import com.example.stepbackend.repository.HttpCookieOAuth2AuthorizationRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2SuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomOAuth2FailHandler oAuth2AuthenticationFailureHandler;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;
    private final NullPointExceptionFilter nullPointExceptionFilter;

    @Autowired
    @Qualifier("RestAuthenticationEntryPoint")
    private AuthenticationEntryPoint authEntryPoint;
    @Autowired
    private CustomTokenService customTokenService;
    @Autowired
    private CustomUserDetailService customUserDetailService;
    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    AuthenticationExceptionFilter authenticationExceptionFilter(HandlerExceptionResolver resolver) {
        return new AuthenticationExceptionFilter(resolver);
    }

//    NullPointExceptionFilter nullPointExceptionFilter(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
//        return new NullPointExceptionFilter(resolver);
//    }

    TokenAuthenticationFilter tokenAuthenticationFilter(CustomTokenService customTokenService,
                                                        CustomUserDetailService customUserDetailService) {
        return new TokenAuthenticationFilter(customTokenService, customUserDetailService);
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieAuthorizationRequestRepository() {
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Bean
    @Order(0)
    public SecurityFilterChain exceptionSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .requestCache().disable()
                .securityContext().disable()
                .sessionManagement().disable()
                .requestMatchers((matchers) ->
                        matchers
                                .antMatchers(
                                        "/", "/error","/favicon.ico", "/**/*.png",
                                        "/**/*.gif", "/**/*.svg", "/**/*.jpg",
                                        "/**/*.html", "/**/*.css", "/**/*.js"
                                )
                                .antMatchers(
                                        "/swagger", "/swagger-ui.html", "/swagger-ui/**",
                                        "/api-docs", "/api-docs/**", "/v3/api-docs/**"
                                )
                                .antMatchers(
                                        "/auth/**"
                                )
                )
                .authorizeHttpRequests((authorize) -> authorize.anyRequest().permitAll());

        return http.build();
    }
    @Bean
    @Order(1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
//                .and()
                .csrf()
                .disable()
                .formLogin()
                .disable()
                .exceptionHandling()
                //.authenticationEntryPoint(authEntryPoint)
                .and()

                .authorizeRequests()
                .antMatchers("/question/**")
                .permitAll()
                .antMatchers("/oauth2/**")
                .hasRole(Role.USER.name())
                .antMatchers("/blog/**", "/member/**")
                .permitAll()
                .antMatchers("/admin/**")
                .hasRole(Role.ADMIN.name())
                .and()
                .oauth2Login()
                .authorizationEndpoint()
                .and()
                .userInfoEndpoint()
                .userService(customOAuth2UserService)
                .and()
                .successHandler(oAuth2AuthenticationSuccessHandler)
                .failureHandler(oAuth2AuthenticationFailureHandler);

        http
                .exceptionHandling()
                .accessDeniedHandler(customAccessDeniedHandler);

//        http
//                .addFilterBefore(tokenAuthenticationFilter(customTokenService, customUserDetailService), UsernamePasswordAuthenticationFilter.class)
//                .addFilterBefore(authenticationExceptionFilter(resolver), TokenAuthenticationFilter.class)
//                .addFilterBefore(nullPointExceptionFilter, AuthenticationExceptionFilter.class);

        return http.build();
    }

}
