package com.example.stepbackend.global.provider;

import com.example.stepbackend.aggregate.entity.enumType.Provider;

import java.util.Map;

public class Kakao extends OAuth2UserInfo {

    public Kakao(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getProvider() {
        return Provider.KAKAO.name();
    }

    @Override
    public String getId() {
        return attributes.get("id").toString();
    }

    @Override
    public String getNickname() {
        Map<String, Object> propertise = (Map<String, Object>) attributes.get("propertise");

        if(propertise == null) {
            return null;
        }

        return (String) attributes.get("nickName");
    }

    @Override
    public String getEmail() {
        Map<String, Object> propertise = (Map<String, Object>) attributes.get("kakao_account");

        if(propertise == null) {
            return null;
        }

        return (String) attributes.get("email");
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> propertise = (Map<String, Object>) attributes.get("propertise");

        if(propertise == null) {
            return null;
        }

        return (String) propertise.get("thumbnail_image");
    }
}
