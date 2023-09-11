package com.example.stepbackend.aggregate.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FindUserDTO {

    private long id;

    private String nickname;

    private String email;

    private String profileImage;

    private String role;

    private String provider;


    public FindUserDTO() {}

    public FindUserDTO(long id, String nickname, String email, String profileImage, String role, String provider) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
        this.role = role;
        this.provider = provider;
    }
}
