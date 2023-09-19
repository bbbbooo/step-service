package com.example.stepbackend.aggregate.entity;

import com.example.stepbackend.aggregate.dto.Heart.PostHeartRequestDTO;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("좋아요 번호")
    @Column
    private Long heartNo;

    @Comment("좋아요 누른 회원 번호")
    @Column
    private Long memberNo;

    @Comment("좋아요 누른 게시글 번호")
    @Column
    private Long boardNo;

    public static Heart toEntity(PostHeartRequestDTO postHeartRequestDTO, Long memberNo) {
        return Heart.builder()
                .boardNo(postHeartRequestDTO.getBoardNo())
                .heartNo(postHeartRequestDTO.getBoardNo())
                .memberNo(memberNo)
                .build();
    }
}
