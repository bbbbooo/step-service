package com.example.stepbackend.aggregate.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import org.hibernate.annotations.Table;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(appliesTo = "question_by_member", comment = "회원별 문제 테이블")
public class QuestionByMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Comment("회원 맞춤형 문제 번호")
    private Long QuestionByMemberNo;

    @Column
    @Comment("회원 번호")
    private Long memberNo;

    @Column
    @Comment("문제 번호")
    private Long questionNo;

    @Column
    @Comment("사용자가 입력한 정답 번호")
    private Integer markedNo;

    @Column
    @Comment("정답 여부")
    private Boolean correctedMarkingStatus;

    @Column
    @Comment("등록 시간")
    private LocalDateTime createdTime;
}
