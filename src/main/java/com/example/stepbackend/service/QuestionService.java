package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.question.ReqQuestionDTO;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Object readQuestion(Long questionNo) throws Exception {
        Optional<Question> optionalQuestion = questionRepository.findById(questionNo);

        if(optionalQuestion.isPresent()) {
            ResQuestionDTO resQuestionDTO = optionalQuestion.get().toDto();
            return resQuestionDTO;
        } else {
            return false;
        }
    }

    public void registQuestion(ReqQuestionDTO reqQuestionDto) throws Exception {

        if(reqQuestionDto == null) throw new Exception("등록할 문제가 없습니다.");

        Question question = reqQuestionDto.toEntity();

        questionRepository.save(question);
    }
}
