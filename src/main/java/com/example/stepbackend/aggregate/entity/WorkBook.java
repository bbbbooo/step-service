package com.example.stepbackend.aggregate.entity;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Table
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class WorkBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("문제집 번호")
    @Column
    private Long workbookNo;

    @Comment("스크랩 번호")
    @Column
    private Long scrapNo;

    @Comment("문제 번호")
    @Column
    private Long questionNo;

    @Comment("좋아요 수")
    @Column
    private Long heartCnt;

    @Comment("공유 여부")
    @Column
    private Boolean isShared;
}
