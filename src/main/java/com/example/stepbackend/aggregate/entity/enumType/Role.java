package com.example.stepbackend.aggregate.entity.enumType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    GUEST("ROLE_GUEST", "게스트"),
    ADMIN("ROLE_ADMIN", "관리자"),
    USER("ROLE_USER", "유저");

    private String key;
    private String value;
}
