package com.example.stepbackend.aggregate.dto.Heart;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostHeartRequestDTO {
    private Long boardNo;
}
