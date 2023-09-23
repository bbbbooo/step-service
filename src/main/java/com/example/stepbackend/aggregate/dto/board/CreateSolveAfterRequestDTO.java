package com.example.stepbackend.aggregate.dto.board;


import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CreateSolveAfterRequestDTO {
    private Long boardNo;
}
