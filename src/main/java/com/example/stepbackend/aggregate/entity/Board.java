package com.example.stepbackend.aggregate.entity;

import com.example.stepbackend.aggregate.dto.board.CreateBoardRequestDTO;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Comment("게시글 번호")
    private Long boardNo;

    @Column
    @Comment("게시글명")
    private String boardName;

    @Column
    @Comment("회원 번호")
    private Long memberNo;

    @Column
    @Comment("나만의 문제로 만든 문제 번호들")
    private String questionNos;

    @Column
    @Comment("나만의 문제에 존재하는 문제 타입들")
    private String questionTypes;

    @Column
    @Comment("문제집 설명")
    private String description;

    @Column
    @Comment("만들어진 날짜")
    private LocalDate createdAt;

    @Column
    @Comment("좋아요 수")
    private Long heartCount;

    public static Board toEntity(Long memberNo, WorkBook workBook, CreateBoardRequestDTO createBoardRequestDTO) {
        return Board.builder()
                .memberNo(memberNo)
                .boardName(createBoardRequestDTO.getWorkBookName())
                .description(createBoardRequestDTO.getDescription())
                .questionNos(workBook.getQuestionNos())
                .questionTypes(workBook.getQuestionTypes())
                .createdAt(LocalDate.now())
                .heartCount(0L)
                .build();
    }

    public void update(String title, String description) {
        this.boardName = title;
        this.description = description;
    }

    public void increaseHeartCount() {
        this.heartCount += 1L;
    }

    public void decreaseHeartCount() {
        this.heartCount = Math.max(this.heartCount - 1L, 0L);

    }
}
