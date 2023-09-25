package com.example.stepbackend.aggregate.dto.statistics;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodayStatisticsDTO {
    private LocalDate date;
    private Integer totalSolvedQuestion;
    private Float answerAccuracy;
}
