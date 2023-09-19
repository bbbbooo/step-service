package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.question.QuestionDTO;
import com.example.stepbackend.aggregate.dto.question.ReqQuestionByMemberDTO;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.aggregate.entity.User;
import com.example.stepbackend.global.common.annotation.CurrentUser;
import com.example.stepbackend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
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

    @GetMapping
    public ModelAndView question(@RequestParam String type,  ModelAndView mv) {
        Long userId = 1L;
        List<ResQuestionDTO> res = questionService.readQuestion(type, userId);

//        JSONObject jsonObject = new JSONObject();

        if(!res.isEmpty()) {
//            jsonObject.appendField("res", res);
            mv.addObject("res", res);
        } else {
            mv.addObject("res", null);
        }

        mv.setViewName("questions/question");

        return mv;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity postQuestionByMember(@CurrentUser User user, @RequestBody ReqQuestionByMemberDTO req) {

        Long memberNo = 1L;
        questionService.registQuestionByMember(req, memberNo);

        return new ResponseEntity<>("true", HttpStatus.OK);
    }

    @PostMapping("/creation-question")
    @ResponseBody
    public ResponseEntity readQuestion(@RequestParam String type, @CurrentUser User user) throws Exception {
        // 모델 서빙서버 url
//        String uri = "http://192.168.0.59:5050/quiz/blank"; // 빈칸추론
//        String uri = "http://192.168.0.59:5050/quiz/title"; // 제목추론

        // mock 서버 url
        String uri = "https://73e6fc73-1c71-424e-a25b-f77760a2e6e9.mock.pstmn.io/data?type=" + type;
        ResponseEntity<String> responseEntity;

        try {
            RestTemplate restTemplate = new RestTemplate();
            responseEntity = restTemplate.getForEntity(uri, String.class);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody().toString());

        JSONArray questions = null;
        String classification = null;

        if(jsonObject.get("blank") != null) {
            questions = (JSONArray) jsonObject.get("blank");
            classification = "blank";
        } else {
            questions = (JSONArray) jsonObject.get("title");
            classification = "title";
        }

        List<QuestionDTO> questionDTOS = new ArrayList<>();
        int count = 1;

        for(Object questionObj : questions) {
            String questionCount = String.valueOf(count);
            JSONObject questionJSON = (JSONObject) questionObj;
            QuestionDTO questionDTO =  questionService.convertToDto(questionJSON, questionCount, classification);
            questionService.registQuestion(questionDTO);
            questionDTOS.add(questionDTO);
            count++;
        }

        HashMap map = new HashMap<>();
        map.put("ques", questionDTOS);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
