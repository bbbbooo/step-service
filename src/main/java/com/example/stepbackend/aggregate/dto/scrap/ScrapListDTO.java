package com.example.stepbackend.aggregate.dto.scrap;

import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.aggregate.entity.Scrap;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ScrapListDTO {
    private Long questionNo;

    private String questionSubject;

    private String questionBody;

    private String questionViewType;

    private String questionLargeClassification;

    private String questionMiddleClassification;

    private String questionSmallClassification;

    private Integer questionCorrectAnswer;

    private String questionSource;

    public static Page<ScrapListDTO> fromEntity(Page<Question> questions) {
        List<ScrapListDTO> dtoList = questions.getContent().stream().map(ScrapListDTO::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(dtoList, questions.getPageable(), questions.getTotalElements());
    }

    public static ScrapListDTO fromEntity(Question question) {
        return ScrapListDTO.builder()
                .questionNo(question.getQuestionNo())
                .questionSubject(question.getQuestionSubject())
                .questionBody(question.getQuestionBody())
                .questionViewType(question.getQuestionViewType())
                .questionLargeClassification(question.getQuestionLargeClassification())
                .questionMiddleClassification(question.getQuestionMiddleClassification())
                .questionSmallClassification(question.getQuestionSmallClassification())
                .questionCorrectAnswer(question.getQuestionCorrectAnswer())
                .questionSource(question.getQuestionSource())
                .build();
    }
}
