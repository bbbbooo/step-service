package com.example.stepbackend.aggregate.dto.user;

import com.example.stepbackend.aggregate.entity.enumType.Provider;
import com.example.stepbackend.aggregate.entity.enumType.Role;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CreateUserDTO {

    private final String UID;
    private final String nickname;
    private final Role role;
    private final String profileImage;
    private final String email;
    private final Provider provider;

    public CreateUserDTO(String UID, String nickname, Role role, String profileImage, String email, Provider provider) {
        this.UID = UID;
        this.nickname = nickname;
        this.role = role;
        this.profileImage = profileImage;
        this.email = email;
        this.provider = provider;
    }
}
