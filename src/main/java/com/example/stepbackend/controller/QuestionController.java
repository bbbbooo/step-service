package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.question.QuestionDTO;
import com.example.stepbackend.aggregate.dto.question.ReqQuestionByMemberDTO;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.aggregate.entity.User;
import com.example.stepbackend.global.common.annotation.CurrentUser;
import com.example.stepbackend.global.security.token.UserPrincipal;
import com.example.stepbackend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @Value("${ai.model.serving.url}")
    private String uri;

    @GetMapping
    public ModelAndView question(@RequestParam String type, ModelAndView mv, @CurrentUser UserPrincipal user) {
        Long userId = user.getId();
        List<ResQuestionDTO> res = questionService.readQuestion(type, userId);

        if(!res.isEmpty()) {
            mv.addObject("res", res);
        } else {
            mv.addObject("res", null);
        }

        mv.setViewName("questions/question");

        return mv;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity postQuestionByMember(@CurrentUser UserPrincipal user, @RequestBody ReqQuestionByMemberDTO req) {

        Long memberNo = user.getId();
        questionService.registQuestionByMember(req, memberNo);

        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @PostMapping("/creation-question")
    @ResponseBody
    public ResponseEntity readQuestion(@RequestParam String type) throws Exception {
        // 모델 서빙서버 url
         String uri = "http://192.168.0.8:5050/" + type;
        // blank 빈칸 , title 제목, suffle1 순서 바꾸는 문제(a-b-c), suffle2 순서 바꾸는 문제(1,2,3,4,5), topic 주제

        // mock 서버 url
//        String uri = "https://ada0d2bb-3eb6-47f0-aecd-aa8ab92d46de.mock.pstmn.io/" + type;
        ResponseEntity<String> responseEntity;

        try {
            // 문제 요청
            RestTemplate restTemplate = new RestTemplate();
            responseEntity = restTemplate.getForEntity(uri, String.class);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        JSONParser jsonParser = new JSONParser();

        JSONArray questions = (JSONArray) jsonParser.parse(responseEntity.getBody().toString());

        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for(Object questionObj : questions) {
            JSONObject questionJSON = (JSONObject) questionObj;
            // 문제 변환
            QuestionDTO questionDTO =  questionService.convertToDto(questionJSON, type);
            // 문제 저장
            questionService.registQuestion(questionDTO);
            // 응답값에 포함
            questionDTOS.add(questionDTO);
        }

        HashMap map = new HashMap<>();
        map.put("ques", questionDTOS);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
