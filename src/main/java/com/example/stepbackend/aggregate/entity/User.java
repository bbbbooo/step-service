package com.example.stepbackend.aggregate.entity;

import com.example.stepbackend.aggregate.entity.enumType.Provider;
import com.example.stepbackend.aggregate.entity.enumType.Role;
import lombok.Builder;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("회원 식별 번호")
    private Long id;

    @Comment("카카오에서 받아오는 회원의 닉네임")
    @Column(nullable = false)
    private String nickname;

    @Email
    @Comment("회원가입 시 이메일")
    @Column(nullable = false, unique = true)
    private String email;

    @Comment("유저의 프로필 사진")
    private String profileImage;

    @Comment("다른 플랫폼에서 접속 하는 유저들의 아이디(이메일) 식별")
    @Column(nullable = false)
    private String UID;

    @Comment("Oauth 제공 플랫폼")
    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Comment("회원의 역할")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Comment("회원 가입 일시")
    @CreatedDate
    @Column(name = "created_date" , nullable = false)
    private LocalDateTime createdDate;

    public User() {}



    public User(String nickname, String email, String profileImage, String UID, Provider provider, Role role) {
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
        this.UID = UID;
        this.provider = provider;
        this.role = role;
        this.createdDate = createdDate;
    }
}