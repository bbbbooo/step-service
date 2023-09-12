package com.example.stepbackend.aggregate.dto.user;

import com.example.stepbackend.aggregate.entity.enumType.Provider;
import com.example.stepbackend.aggregate.entity.enumType.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserDTO {

    private String UID;
    private String nickName;
    private Role role;
    private String profileImage;
    private Provider provider;

    public UpdateUserDTO(String UID, String nickName, Role role, String profileImage, Provider provider) {
        this.UID = UID;
        this.nickName = nickName;
        this.role = role;
        this.profileImage = profileImage;
        this.provider = provider;
    }

    public UpdateUserDTO(String nickName, String profileImage) {
        this.nickName = nickName;
        this.profileImage = profileImage;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
