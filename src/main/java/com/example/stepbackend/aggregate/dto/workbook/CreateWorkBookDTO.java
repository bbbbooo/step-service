package com.example.stepbackend.aggregate.dto.workbook;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CreateWorkBookDTO {
    private Long questionNo;

    private String questionSubject;

    private String questionBody;

    private Integer questionViewType;

    private String questionLargeClassification;

    private String questionMiddleClassification;

    private String questionSmallClassification;

    private Integer questionCorrectAnswer;

    private String questionSource;
}
