package com.example.stepbackend.aggregate.dto.board;

import com.example.stepbackend.aggregate.entity.Board;
import com.example.stepbackend.aggregate.entity.Heart;
import com.example.stepbackend.aggregate.entity.Question;
import lombok.*;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReadBoardQuestionResponseDTO {
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

    public static List<ReadBoardQuestionResponseDTO> fromEntity(List<Question> questions) {
        List<ReadBoardQuestionResponseDTO> responseDTOList = questions.stream().map(ReadBoardQuestionResponseDTO::fromEntity).collect(Collectors.toList());
        return responseDTOList;
    }

    public static ReadBoardQuestionResponseDTO fromEntity(Question question){
        return ReadBoardQuestionResponseDTO.builder()
                .questionNo(question.getQuestionNo())
                .questionBody(question.getQuestionBody())
                .questionCorrectAnswer(question.getQuestionCorrectAnswer())
                .view1(question.getView1())
                .view2(question.getView2())
                .view3(question.getView3())
                .view4(question.getView4())
                .view5(question.getView5())
                .questionSubject(question.getQuestionSubject())
                .questionSubject(question.getQuestionSubject())
                .questionLargeClassification(question.getQuestionLargeClassification())
                .questionMiddleClassification(question.getQuestionMiddleClassification())
                .questionSmallClassification(question.getQuestionSmallClassification())
                .questionSource(question.getQuestionSource())
                .questionSourcePublishYear(question.getQuestionSourcePublishYear())
                .questionViewType(question.getQuestionViewType())
                .build();
    }
}
