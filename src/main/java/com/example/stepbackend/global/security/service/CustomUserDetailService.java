package com.example.stepbackend.global.security.service;

import com.example.stepbackend.aggregate.dto.user.FindUserDTO;
import com.example.stepbackend.global.security.token.UserPrincipal;
import com.example.stepbackend.service.FindUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final FindUserService findUserService;

    @Autowired
    public CustomUserDetailService(FindUserService findUserService) {
        this.findUserService = findUserService;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        FindUserDTO user = findUserService.findByEmail(email);
        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        FindUserDTO user = findUserService.findById(id);

        return UserPrincipal.create(user);
    }
}
