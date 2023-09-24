package com.example.stepbackend.aggregate.dto.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class SignUpDTO {

    private final String nickname;
    private final String profileImage;
    private final String email;

    public SignUpDTO(String nickname, String profileImage, String email) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.email = email;
    }
}
