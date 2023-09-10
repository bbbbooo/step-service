package com.example.stepbackend.aggregate.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@org.hibernate.annotations.Table(appliesTo = "question", comment = "문제 테이블")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Comment("문제 인덱스 번호")
    private Long questionNo;

    @Column
    @Comment("문제 제목")
    private String questionSubject;

    @Column
    @Comment("문제 내용")
    private String questionBody;

    @Column
    @Comment("문제 유형")
    private Integer questionViewType;

    @Column
    @Comment("대분류")
    private String questionLargeClassification;

    @Column
    @Comment("중분류")
    private String questionMiddleClassification;

    @Column
    @Comment("소분류")
    private String questionSmallClassification;

    @Column
    @Comment("정답")
    private Integer questionCorrectAnswer;

    @Column
    @Comment("문제 출처")
    private String questionSource;
}
