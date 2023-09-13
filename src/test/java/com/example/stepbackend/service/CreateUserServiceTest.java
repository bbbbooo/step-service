package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.user.CreateUserDTO;
import com.example.stepbackend.aggregate.entity.enumType.Provider;
import com.example.stepbackend.aggregate.entity.enumType.Role;
import com.example.stepbackend.global.security.service.CustomTokenService;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
class CreateUserServiceTest {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private CustomTokenService customTokenService;

    private static Stream<Arguments> getUserInfo() {
        return Stream.of(
                Arguments.of(
                        new CreateUserDTO(
                                "123123HI",
                                "팽구리",
                                Role.USER,
                                "profileImage",
                                "pyunghun120@gmail.com",
                                Provider.KAKAO
                        )
                ),
                Arguments.of(
                        new CreateUserDTO(
                                "1231231hello",
                                "푸바오",
                                Role.USER,
                                "profileImage2",
                                "punghun12@naver.com",
                                Provider.KAKAO
                        )
                )
        );
    }

    @DisplayName("사용자 생성 DTO를 통해 생성이 되는 지 TEST")
    @ParameterizedTest
    @MethodSource("getUserInfo")
    void create(CreateUserDTO createUserDTO) {

        Assertions.assertDoesNotThrow(
                () -> createUserService.create(createUserDTO)
        );
    }

}
