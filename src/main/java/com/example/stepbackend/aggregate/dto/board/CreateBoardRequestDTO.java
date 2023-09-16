package com.example.stepbackend.aggregate.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateBoardRequestDTO {
    private String workBookName;

    private String description;

    private String workBookNo;
}
