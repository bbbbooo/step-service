package com.example.stepbackend.aggregate.entity;

import com.example.stepbackend.aggregate.dto.question.ReqQuestionDTO;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@org.hibernate.annotations.Table(appliesTo = "question", comment = "문제 테이블")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    @Comment("문제 인덱스 번호")
    private Long questionNo;

    @Column(name = "subject")
    @Comment("문제 제목")
    private String questionSubject;

    @Column(name = "main")
    @Comment("문제 내용")
    @Lob
    private String questionBody;

    @Column(name = "class")
    @Comment("문제 유형")
    private String questionViewType;

    @Column(name = "view1")
    @Comment("1번 보기")
    private String view1;

    @Column(name = "view2")
    @Comment("2번 보기")
    private String view2;

    @Column(name = "view3")
    @Comment("3번 보기")
    private String view3;

    @Column(name = "view4")
    @Comment("4번 보기")
    private String view4;

    @Column(name = "view5")
    @Comment("5번 보기")
    private String view5;

    @Column
    @Comment("대분류")
    private String questionLargeClassification;

    @Column
    @Comment("중분류")
    private String questionMiddleClassification;

    @Column
    @Comment("소분류")
    private String questionSmallClassification;

    @Column(name = "answer")
    @Comment("정답")
    private Integer questionCorrectAnswer;

    @Column
    @Comment("문제 출처")
    private String questionSource;

    @Column
    @Comment("문제 출처 발행 연도")
    private Integer questionSourcePublishYear;

    public ResQuestionDTO toDto() {
        ResQuestionDTO resQuestionDto = new ResQuestionDTO();
        resQuestionDto.setQuestionSubject(this.questionSubject);
        resQuestionDto.setQuestionBody(this.questionBody);
        resQuestionDto.setView1(this.view1);
        resQuestionDto.setView2(this.view2);
        resQuestionDto.setView3(this.view3);
        resQuestionDto.setView4(this.view4);
        resQuestionDto.setView5(this.view5);
        resQuestionDto.setQuestionViewType(this.questionViewType);
        resQuestionDto.setQuestionLargeClassification(this.questionLargeClassification);
        resQuestionDto.setQuestionMiddleClassification(this.questionMiddleClassification);
        resQuestionDto.setQuestionSmallClassification(this.questionSmallClassification);
        resQuestionDto.setQuestionCorrectAnswer(this.questionCorrectAnswer);
        resQuestionDto.setQuestionSource(this.questionSource);
        resQuestionDto.setQuestionSourcePublishYear(this.questionSourcePublishYear);
        return resQuestionDto;
    }
}
