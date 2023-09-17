package com.example.stepbackend.aggregate.dto.board;

import com.example.stepbackend.aggregate.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateBoardResponseDTO {
    private Long boardNo;
    private String message;

    public static CreateBoardResponseDTO fromEntity(Board board) {
        return CreateBoardResponseDTO.builder()
                .boardNo(board.getBoardNo())
                .message("성공적으로 생성되었습니다.")
                .build();
    }
}
