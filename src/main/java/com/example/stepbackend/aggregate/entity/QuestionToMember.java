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
public class QuestionToMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Comment("회원 맞춤형 문제 번호")
    private Long questionToMemberNo;

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
}
