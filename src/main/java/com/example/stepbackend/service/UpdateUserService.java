package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.user.UpdateUserDTO;
import com.example.stepbackend.aggregate.entity.User;
import com.example.stepbackend.repository.UserRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateUserService {

    private final UserRepository userRepository;

    public UpdateUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean update(long userId, UpdateUserDTO updateUserDTO) {
        Optional<User> findUser = userRepository.findById(userId);
        if(findUser.isPresent()) {
            User updateUser = findUser.get();
            if(updateUserDTO.getProfileImage() != null) {
                updateUser.setProfileImage(updateUserDTO.getProfileImage());
            }
            return true;
        } else {
            return false;
        }
    }

}
