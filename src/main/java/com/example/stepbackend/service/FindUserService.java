package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.user.FindUserDTO;
import com.example.stepbackend.aggregate.entity.User;
import com.example.stepbackend.global.exception.assertion.ExceptionAssert;
import com.example.stepbackend.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindUserService {

    private final UserMapper userMapper;

    @Autowired
    public FindUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    public FindUserDTO findByUID(String uid) {
        User findUser = userMapper.findByUID(uid);
        if(findUser == null) {
            return null;
        } else {
            return new FindUserDTO(
                    findUser.getId(),
                    findUser.getNickname(),
                    findUser.getEmail(),
                    findUser.getProfileImage(),
                    findUser.getRole().name(),
                    findUser.getProvider().name()
            );
        }
    }

    public FindUserDTO findById(long userId) {
        User findUser = userMapper.findById(userId);

        ExceptionAssert.isUserExist(findUser);

        return new FindUserDTO(
                findUser.getId(),
                findUser.getNickname(),
                findUser.getEmail(),
                findUser.getProfileImage(),
                findUser.getRole().name(),
                findUser.getProvider().name()
        );
    }

    public FindUserDTO findByEmail(String email) {
        User findUser = userMapper.findByEmail(email);
        ExceptionAssert.isUserExist(findUser);

        return new FindUserDTO(
                findUser.getId(),
                findUser.getNickname(),
                findUser.getEmail(),
                findUser.getProfileImage(),
                findUser.getRole().name(),
                findUser.getProvider().name()
        );
    }
}
