package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("{questionNo}")
    public ResponseEntity readQuestion(@PathVariable Long questionNo) {
        try {
            // db에 저장된 문제 탐색
            Object res = questionService.readQuestion(questionNo);
            return ResponseEntity.ok(res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
