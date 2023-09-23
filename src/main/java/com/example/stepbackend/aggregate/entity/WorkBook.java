package com.example.stepbackend.aggregate.entity;

import com.example.stepbackend.aggregate.dto.board.CreateBoardRequestDTO;
import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookRequestDTO;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    @Comment("문제집명")
    private String workBookName;

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
    @Comment("최종 수정 시간")
    private LocalDateTime lastUpdatedTime;

    @Column
    @Comment("내가 공유했는지 확인")
    private Boolean isShared;

    @Column
    @Comment("공유 받았는지 확인")
    private Boolean hadShared;

    public static WorkBook toEntity(Long memberNo, CreateWorkBookRequestDTO createWorkBookRequestDTO, List<String> questionTypes) {
        String questionNosToString = createWorkBookRequestDTO.getQuestionNos().stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        String questionTypesToString = questionTypes.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));


        return WorkBook.builder()
                .memberNo(memberNo)
                .questionNos(questionNosToString)
                .workBookName(createWorkBookRequestDTO.getWorkBookName())
                .questionTypes(questionTypesToString)
                .lastUpdatedTime(LocalDateTime.now())
                .isShared(false)
                .build();
    }

    public static WorkBook toEntityFromBoard(Board board) {
        return WorkBook.builder()
                .memberNo(board.getMemberNo())
                .questionTypes(board.getQuestionTypes())
                .workBookName(board.getBoardName())
                .questionNos(board.getQuestionNos())
                .description(board.getDescription())
                .lastUpdatedTime(LocalDateTime.now())
                .hadShared(true)
                .build();
    }

    public void updateWorkBookName(String workBookName, String description) {
        this.workBookName = workBookName;
        this.description = description;
        this.lastUpdatedTime = LocalDateTime.now();
    }

    public void updateIsShared(CreateBoardRequestDTO createBoardRequestDTO) {
        this.isShared = createBoardRequestDTO.getIsShared();
    }
}
