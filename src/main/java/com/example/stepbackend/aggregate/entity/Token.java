package com.example.stepbackend.aggregate.entity;

import com.example.stepbackend.aggregate.entity.vo.UserVO;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserVO user;

    @Column(name = "access_token", nullable = false)
    private String accessToken;

    public Token() {}

    public Token(UserVO user, String accessToken) {
        this.user = user;
        this.accessToken = accessToken;
    }
}
