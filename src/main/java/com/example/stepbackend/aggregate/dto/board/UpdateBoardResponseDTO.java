package com.example.stepbackend.aggregate.dto.board;

import com.example.stepbackend.aggregate.entity.Board;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateBoardResponseDTO {

    private Long boardNo;

    private String message;

    public static UpdateBoardResponseDTO toEntity(Board board) {
        return UpdateBoardResponseDTO.builder()
                .boardNo(board.getBoardNo())
                .message("수정하는데 성공하였습니다.")
                .build();
    }
}
