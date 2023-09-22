package com.example.stepbackend.aggregate.dto.board;

import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class SolveQuestionRequestDTO {

    // 문제 번호
    private Long questionNo;

    // 사용자가 체크한 번호
    private Integer markedNo;

    // 정답 여부
    private Boolean correctedMarkingStatus;
}
