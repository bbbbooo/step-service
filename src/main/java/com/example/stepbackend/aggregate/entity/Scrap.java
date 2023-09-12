package com.example.stepbackend.aggregate.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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

    @Column
    @Comment("사용자가 입력한 정답 번호")
    private Integer markedNo;

    @Column
    @Comment("정답 여부")
    private Boolean correctedMarkingStatus;

    public static Scrap toEntity(Long questionNo, Long memberNo) {
        return Scrap.builder()
                .questionNo(questionNo)
                .memberNo(memberNo)
                .build();
    }
}
