package com.example.stepbackend.aggregate.dto.workbook;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class FilterWorkBookRequestDTO {
    private Boolean titleOption;

    private Boolean blankOption;

    private Boolean sharedOption;

    private Boolean receivedOption;

    private Boolean orderOption;

    private Boolean sentenceOption;

    private Boolean topicOption;
}
