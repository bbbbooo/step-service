package com.example.stepbackend.aggregate.dto.Heart;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PostHeartRequestDTO {
    private Long boardNo;
}
