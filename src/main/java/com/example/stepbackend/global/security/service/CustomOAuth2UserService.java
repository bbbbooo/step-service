package com.example.stepbackend.global.security.service;

import com.example.stepbackend.aggregate.dto.user.CreateUserDTO;
import com.example.stepbackend.aggregate.dto.user.FindUserDTO;
import com.example.stepbackend.aggregate.dto.user.UpdateUserDTO;
import com.example.stepbackend.aggregate.entity.User;
import com.example.stepbackend.aggregate.entity.enumType.Provider;
import com.example.stepbackend.aggregate.entity.enumType.Role;
import com.example.stepbackend.global.provider.OAuth2UserInfo;
import com.example.stepbackend.global.provider.OAuth2UserInfoFactory;
import com.example.stepbackend.global.security.token.UserPrincipal;
import com.example.stepbackend.service.CreateUserService;
import com.example.stepbackend.service.FindUserService;
import com.example.stepbackend.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final FindUserService findUserService;
    private final CreateUserService createUserService;
    private final UpdateUserService updateUserService;

    @Autowired
    public CustomOAuth2UserService(FindUserService findUserService, CreateUserService createUserService, UpdateUserService updateUserService) {
        this.findUserService = findUserService;
        this.createUserService = createUserService;
        this.updateUserService = updateUserService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase();

        System.out.println("registrationId = " + registrationId);

        String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2UserInfo attributes = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, oAuth2User.getAttributes());
        System.out.println("attributes = " + attributes);

        UserPrincipal socialUser = saveOrUpdate(attributes, registrationId);

        return socialUser;
    }

    private UserPrincipal saveOrUpdate(OAuth2UserInfo attributes, String provider) {
        FindUserDTO user = findUserService.findByUID(attributes.getId());
        System.out.println(" attributes.getEmail() = " +  attributes.getEmail());
        UserPrincipal oauthUser;
        if(user == null) {
            CreateUserDTO createUserDTO = new CreateUserDTO(attributes.getId(), attributes.getNickname(), Role.USER, attributes.getImageUrl(), attributes.getEmail(), Provider.valueOf(provider.toUpperCase()));
            User newUser = createUserService.create(createUserDTO);
            oauthUser = UserPrincipal.create(newUser, attributes.getAttributes());
        } else {
            UpdateUserDTO updateUserDTO = new UpdateUserDTO(attributes.getNickname(), attributes.getImageUrl());
            boolean updateUserResult = updateUserService.update(user.getId(), updateUserDTO);
            oauthUser = UserPrincipal.create(user, attributes.getAttributes());
        }

        return oauthUser;
    }
}
