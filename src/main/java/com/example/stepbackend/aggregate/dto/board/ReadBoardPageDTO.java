package com.example.stepbackend.aggregate.dto.board;

import com.example.stepbackend.aggregate.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class ReadBoardPageDTO {
    private Long boardNo;

    private String[] questionNos;

    private String[] questionTypes;

    private String questionName;

    private LocalDate createdAt;

    private String description;

    public static Page<ReadBoardPageDTO> toEntity(Page<Board> boards) {
        return boards.map(board -> ReadBoardPageDTO.builder()
                .boardNo(board.getBoardNo())
                .questionNos(board.getQuestionNos().split(", "))
                .questionTypes(board.getQuestionTypes().split(", "))
                .questionName(board.getBoardName())
                .createdAt(board.getCreatedAt())
                .description(board.getDescription())
                .build());
    }
}
