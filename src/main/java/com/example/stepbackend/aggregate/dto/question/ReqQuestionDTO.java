package com.example.stepbackend.aggregate.dto.question;

import com.example.stepbackend.aggregate.entity.Question;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReqQuestionDTO {

    // 문제 제목
    private String questionSubject;

    // 문제 내용
    private String questionBody;

    // 문제 유형
    private String questionViewType;

    // 대분류
    private String questionLargeClassification;

    // 중분류
    private String questionMiddleClassification;

    // 소분류
    private String questionSmallClassification;

    // 정답
    private Integer questionCorrectAnswer;

    // 문제 출처
    private String questionSource;

    // 문제 출처 발행 연도
    private Integer questionSourcePublishYear;

    public Question toEntity() {
        Question question = new Question();
        question.setQuestionSubject(this.questionSubject);
        question.setQuestionBody(this.questionBody);
        question.setQuestionViewType(this.questionViewType);
        question.setQuestionLargeClassification(this.questionLargeClassification);
        question.setQuestionMiddleClassification(this.questionMiddleClassification);
        question.setQuestionSmallClassification(this.questionSmallClassification);
        question.setQuestionCorrectAnswer(this.questionCorrectAnswer);
        question.setQuestionSource(this.questionSource);
        question.setQuestionSourcePublishYear(this.questionSourcePublishYear);
        return question;
    }
}
