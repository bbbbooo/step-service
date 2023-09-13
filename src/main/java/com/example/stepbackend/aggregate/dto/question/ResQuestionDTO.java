package com.example.stepbackend.aggregate.dto.question;

import com.example.stepbackend.aggregate.entity.Question;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResQuestionDTO {

    // 문제 번호
    private Long questionNo;
    // 문제 제목
    private String questionSubject;

    // 문제 내용
    private String questionBody;

    // 문제 유형(빈칸 추론 : blank, 제목 추론 : title)
    private String questionViewType;

    // 1번 보기
    private String view1;

    // 2번 보기
    private String view2;

    // 3번 보기
    private String view3;

    // 4번 보기
    private String view4;

    // 5번 보기
    private String view5;

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
