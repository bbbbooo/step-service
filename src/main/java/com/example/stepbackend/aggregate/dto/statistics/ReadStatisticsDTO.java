package com.example.stepbackend.aggregate.dto.statistics;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReadStatisticsDTO {

    // 문제 유형
    private String questionType;

    // 회원 번호
    private Long memberNo;
    // 총 시도 횟수
    private Integer totalAttempts;

    // 정답 횟수
    private Integer correctAttempts;

    // 정답률
    private Double answerAccuracy;
}
