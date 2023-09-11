package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.user.CreateUserDTO;
import com.example.stepbackend.aggregate.entity.User;
import com.example.stepbackend.aggregate.entity.enumType.Provider;
import com.example.stepbackend.aggregate.entity.enumType.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
class FindUserServiceTest {

    @Autowired
    private CreateUserService createUserService;

    @Autowired
    private FindUserService findUserService;

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
                )
        );
    }

    @DisplayName("UID를 통해 생성이 되는 지 확인하기")
    @ParameterizedTest
    @MethodSource("getUserInfo")
    void findByUID(CreateUserDTO createUserDTO) {
        createUserService.create(createUserDTO);

        Assertions.assertNotNull(findUserService.findByUID(createUserDTO.getUID()));
    }

    @DisplayName("ID를 통해 생성이 되는 지 확인하기")
    @ParameterizedTest
    @MethodSource("getUserInfo")
    void findById(CreateUserDTO createUserDTO) {
        User createdUser = createUserService.create(createUserDTO);
        Assertions.assertNotNull(findUserService.findById(createdUser.getId()));
    }

}
