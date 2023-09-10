package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.user.CreateUserDTO;
import com.example.stepbackend.aggregate.entity.User;
import com.example.stepbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateUserService {

    private final UserRepository userRepository;

    @Autowired
    public CreateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(CreateUserDTO createUserDTO) {
        User createUser = new User(
                createUserDTO.getNickname(),
                createUserDTO.getEmail(),
                createUserDTO.getProfileImage(),
                createUserDTO.getUID(),
                createUserDTO.getProvider(),
                createUserDTO.getRole()
        );
        return userRepository.save(createUser);
    }
}
