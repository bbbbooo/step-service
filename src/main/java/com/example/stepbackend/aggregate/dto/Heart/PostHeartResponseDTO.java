package com.example.stepbackend.aggregate.dto.Heart;

import com.example.stepbackend.aggregate.entity.Board;
import com.example.stepbackend.aggregate.entity.Heart;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PostHeartResponseDTO {
    private Long heartNo;

    private Long boardNo;

    private String message;

    private Long heartCount;

    public static PostHeartResponseDTO fromEntity(Heart saveHeart, Board board) {
        return PostHeartResponseDTO.builder()
                .heartNo(saveHeart.getHeartNo())
                .boardNo(saveHeart.getBoardNo())
                .heartCount(board.getHeartCount())
                .build();
    }
}
