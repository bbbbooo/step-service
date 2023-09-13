package com.example.stepbackend.aggregate.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class WorkBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Comment("나만의 문제 번호")
    private Long workBookNo;

    @Column
    @Comment("회원 번호")
    private Long memberNo;

    @Column
    @Comment("문제 번호")
    private Long questionNo;

    @Column
    @Comment("문제 번호")
    private Boolean isShared;


    public static WorkBook toEntity(Long memberNo, Long questionNo) {
        return WorkBook.builder()
                .memberNo(memberNo)
                .questionNo(questionNo)
                .isShared(false)
                .build();
    }

    public void updateIsShared(Boolean isShared) {
        this.isShared = isShared;
    }
}
