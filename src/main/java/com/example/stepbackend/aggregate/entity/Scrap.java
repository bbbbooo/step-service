package com.example.stepbackend.aggregate.entity;

import lombok.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("스크랩 번호")
    @Column
    private Long scrapNo;


    @Comment("회원 번호")
    @Column
    private Long memberNo;

    @Comment("문제 번호")
    @Column
    private Long questionNo;

    public static Scrap toEntity(Long questionNo, Long memberNo) {
        return Scrap.builder()
                .questionNo(questionNo)
                .memberNo(memberNo)
                .build();
    }
}
