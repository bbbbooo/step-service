package com.example.stepbackend.aggregate.dto.scrap;

import com.example.stepbackend.aggregate.entity.Question;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ReadScrapDTO {
    private Long questionNo;

    private String questionSubject;

    private String questionBody;

    private String questionViewType;

    private String questionLargeClassification;

    private String questionMiddleClassification;

    private String questionSmallClassification;

    private Integer questionCorrectAnswer;

    private String questionSource;

    private String[] view;

    public static Page<ReadScrapDTO> fromEntity(Page<Question> questions) {
        List<ReadScrapDTO> dtoList = questions.getContent().stream().map(ReadScrapDTO::fromEntity).collect(Collectors.toList());
        return new PageImpl<>(dtoList, questions.getPageable(), questions.getTotalElements());
    }

    public static ReadScrapDTO fromEntity(Question question) {
        return ReadScrapDTO.builder()
                .questionNo(question.getQuestionNo())
                .questionSubject(question.getQuestionSubject())
                .questionBody(question.getQuestionBody())
                .questionViewType(question.getQuestionViewType())
                .questionLargeClassification(question.getQuestionLargeClassification())
                .questionMiddleClassification(question.getQuestionMiddleClassification())
                .questionSmallClassification(question.getQuestionSmallClassification())
                .questionCorrectAnswer(question.getQuestionCorrectAnswer())
                .questionSource(question.getQuestionSource())
                .view(new String[]{question.getView1(), question.getView2(), question.getView3(), question.getView4(), question.getView5()})
                .build();
    }
}
