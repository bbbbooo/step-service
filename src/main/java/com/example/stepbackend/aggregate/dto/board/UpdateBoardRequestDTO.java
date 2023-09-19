package com.example.stepbackend.aggregate.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateBoardRequestDTO {
    private Long boardNo;

    private String title;

    private String description;
}
