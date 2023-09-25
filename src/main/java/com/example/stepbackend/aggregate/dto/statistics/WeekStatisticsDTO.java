package com.example.stepbackend.aggregate.dto.statistics;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WeekStatisticsDTO {

    private LocalDate weekStartDate;
    private LocalDate weekEndDate;
    private Integer totalSolvedQuestion;
    private Float answerAccuracy;
}
