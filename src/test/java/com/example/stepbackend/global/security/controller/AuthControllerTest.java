package com.example.stepbackend.global.security.controller;

import com.example.stepbackend.global.filter.TokenAuthenticationFilter;
import com.example.stepbackend.global.security.service.CustomTokenService;
import com.example.stepbackend.global.security.service.CustomUserDetailService;
import com.example.stepbackend.service.FindUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private FindUserService findMemberService;

    @Autowired
    private CustomTokenService customTokenService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @BeforeEach
    public void setup() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .addFilter(new TokenAuthenticationFilter(customTokenService, customUserDetailService))
                .build();

    }

}
