package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.question.QuestionDTO;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.global.exception.ResourceNotFoundException;
import com.example.stepbackend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    private RestTemplate restTemplate;

    @PostMapping("{memberNo}")
    public ResponseEntity readQuestion(@PathVariable Long memberNo) throws Exception {
        try {
            // db에 저장된 문제 탐색
            List<ResQuestionDTO> res = questionService.readQuestion(memberNo);
            return ResponseEntity.ok(res);
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();

            String uri = "https://e7c65ef5-9801-4a42-a7e4-694188b45666.mock.pstmn.io/data";
            ResponseEntity<String> responseEntity;
            try {
                responseEntity = restTemplate.getForEntity(uri, String.class);
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody().toString());

            QuestionDTO questionDTO =  questionService.convertToDto(jsonObject);
            questionService.registQuestion(questionDTO);

            return new ResponseEntity<>(questionDTO, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
