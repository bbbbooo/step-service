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
    @Comment("나만의 문제로 만든 문제 번호들")
    private String questionNos;

    @Column
    @Comment("공유 여부")
    private Boolean isShared;


    public static WorkBook toEntity(Long memberNo, String questionNosToString) {
        return WorkBook.builder()
                .memberNo(memberNo)
                .questionNos(questionNosToString)
                .isShared(false)
                .build();
    }

    public void updateIsShared(Boolean isShared) {
        this.isShared = isShared;
    }

}
