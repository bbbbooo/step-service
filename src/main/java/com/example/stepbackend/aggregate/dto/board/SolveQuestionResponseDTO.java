package com.example.stepbackend.aggregate.dto.board;

import com.example.stepbackend.aggregate.entity.QuestionByMember;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SolveQuestionResponseDTO {
    private Long questionByMemberNo;

    public static SolveQuestionResponseDTO fromEntity(QuestionByMember history) {
        return SolveQuestionResponseDTO.builder()
                .questionByMemberNo(history.getQuestionByMemberNo())
                .build();
    }
}
