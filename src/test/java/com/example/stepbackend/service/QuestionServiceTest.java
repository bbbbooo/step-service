package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.repository.QuestionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class QuestionServiceTest {

    @DisplayName("Question")
    private static Stream<Arguments> getQuestionInfo() {
        return Stream.of(
                Arguments.of(
                        "blank",
                        "다음 글의 제목으로 가장 적절한 것은?",
                        "As a system for transmitting specific factual information without any distortion or ambiguity, the sign system of honey-bees would probably win easily over human language every time. However, language offers something more valuable than mere information exchange. Because the meanings of words are not invariable and because understanding always involves interpretation, the act of communicating is always a joint, creative effort. Words can carry meanings beyond those consciously intended by speakers or writers because listeners or readers bring their own perspectives to the language they encounter. Ideas expressed imprecisely may be more intellectually stimulating for listeners or readers than simple facts. The fact that language is not always reliable for causing precise meanings to be generated in someone else’s mind is a reflection of its powerful strength as a medium for creating new understanding. It is the inherent ambiguity and adaptability of language as a meaning-making system that makes the relationship between language and thinking so special.",
                        "Erase Ambiguity in Language Production!",
                        "Not Creative but Simple: The Way Language Works",
                        "Communication as a Universal Goal in Language Use",
                        "What in Language Creates Varied Understanding?",
                        "Language: A Crystal-Clear Looking Glass",
                        "문법",
                        "문장의 기본구조",
                        "주어와 술어, 구와 절",
                        4,
                        "EBS 고등 예비과정 영어영역 영어",
                        2017
                )
        );
    }

    @Autowired
    private QuestionRepository questionRepository;

    @ParameterizedTest
    @MethodSource("getQuestionInfo")
    @DisplayName("문제 등록")
    void registQuestion(String questionViewType, String questionSubject, String questionBody, String view1, String view2, String view3, String view4, String view5,
                        String questionLargeClassification, String questionMiddleClassification, String questionSmallClassification,
                        Integer questionCorrectAnswer, String questionSource, Integer questionSourcePublishYear) {

        //given
        Question question = new Question();
        question.setQuestionSubject(questionSubject);
        question.setQuestionBody(questionBody);
        question.setView1(view1);
        question.setView2(view2);
        question.setView3(view3);
        question.setView4(view4);
        question.setView5(view5);
        question.setQuestionViewType(questionViewType);
        question.setQuestionLargeClassification(questionLargeClassification);
        question.setQuestionMiddleClassification(questionMiddleClassification);
        question.setQuestionSmallClassification(questionSmallClassification);
        question.setQuestionCorrectAnswer(questionCorrectAnswer);
        question.setQuestionSource(questionSource);
        question.setQuestionSourcePublishYear(questionSourcePublishYear);

        //when
        Question foundQuestion = questionRepository.save(question);

        //then
        Assertions.assertNotNull(foundQuestion);
    }

    @ParameterizedTest
    @MethodSource("getQuestionInfo")
    @DisplayName("문제 조회")
    void readQuestion(String questionSubject, String questionBody, String questionViewType,
                      String questionLargeClassification, String questionMiddleClassification, String questionSmallClassification,
                      Integer questionCorrectAnswer, String questionSource, Integer questionSourcePublishYear) {
        //given
        Question question = new Question();
        question.setQuestionSubject(questionSubject);
        question.setQuestionBody(questionBody);
        question.setQuestionViewType(questionViewType);
        question.setQuestionLargeClassification(questionLargeClassification);
        question.setQuestionMiddleClassification(questionMiddleClassification);
        question.setQuestionSmallClassification(questionSmallClassification);
        question.setQuestionCorrectAnswer(questionCorrectAnswer);
        question.setQuestionSource(questionSource);
        question.setQuestionSourcePublishYear(questionSourcePublishYear);

        question = questionRepository.save(question);

        //when
        Optional<Question> foundQuestion = questionRepository.findById(question.getQuestionNo());

        //then
        assertNotNull(foundQuestion.get());
    }


}