package com.example.stepbackend.aggregate.entity.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Embeddable
public class UserVO {

    @Column(nullable = false, name = "user_id")
    private long id;

    public UserVO(long id) {
        this.id = id;
    }

    protected UserVO() {}
}
