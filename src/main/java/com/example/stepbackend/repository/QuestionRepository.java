package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findTop10ByQuestionNoNotInAndQuestionViewType(List<Long> questions, String type);

    List<Question> findTop10ByQuestionViewType(String type);

    Question findByQuestionNo(Long questionNo);

    List<Question> findByQuestionNoIn(List<Long> questionNos);
}
