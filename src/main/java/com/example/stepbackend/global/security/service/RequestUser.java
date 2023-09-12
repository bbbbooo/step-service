package com.example.stepbackend.global.security.service;

import com.example.stepbackend.aggregate.dto.user.FindUserDTO;
import org.springframework.stereotype.Service;


public interface RequestUser {


    FindUserDTO getUserById(long userId);
}
