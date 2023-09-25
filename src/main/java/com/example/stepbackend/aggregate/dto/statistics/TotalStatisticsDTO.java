package com.example.stepbackend.aggregate.dto.statistics;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TotalStatisticsDTO {
    private Integer totalSolvedQuestion;
    private Float answerAccuracy;
}
