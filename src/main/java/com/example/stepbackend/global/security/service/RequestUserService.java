package com.example.stepbackend.global.security.service;

import com.example.stepbackend.aggregate.dto.user.FindUserDTO;
import com.example.stepbackend.service.FindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestUserService implements RequestUser{

    private final FindUserService findUserService;

    @Autowired
    public RequestUserService(FindUserService findUserService) {
        this.findUserService = findUserService;
    }


    @Override
    public FindUserDTO getUserById(long userId) {
        return findUserService.findById(userId);
    }
}
