package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}