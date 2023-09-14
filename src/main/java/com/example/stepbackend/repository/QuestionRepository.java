package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByQuestionNoNotIn(List<Long> questions);

    Question findByQuestionNo(Long questionNo);
}
