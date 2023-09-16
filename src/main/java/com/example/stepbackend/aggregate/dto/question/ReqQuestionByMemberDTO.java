package com.example.stepbackend.aggregate.dto.question;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReqQuestionByMemberDTO {

    private Long questionNo;

    private Integer markedNo;

    // true : 문제 맞춤 , false : 문제 틀림
    private Boolean correctedMarkingStatus;
}
