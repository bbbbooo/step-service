package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.question.QuestionDTO;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.aggregate.entity.User;
import com.example.stepbackend.global.common.annotation.CurrentUser;
import com.example.stepbackend.global.exception.ResourceNotFoundException;
import com.example.stepbackend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Array;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping
    public ModelAndView question(ModelAndView mv) {
        Long userId = 1L;
        List<ResQuestionDTO> res = questionService.readQuestion(userId);

        if(!res.isEmpty()) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.appendField("res", res);
            mv.addObject("res", jsonObject);
        }

        mv.setViewName("questions/question");

        return mv;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity readQuestion(@CurrentUser User user) throws Exception {
        String uri = "https://73e6fc73-1c71-424e-a25b-f77760a2e6e9.mock.pstmn.io/data";
        ResponseEntity<String> responseEntity;

        try {
            RestTemplate restTemplate = new RestTemplate();
            responseEntity = restTemplate.getForEntity(uri, String.class);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(responseEntity.getBody().toString());
        JSONArray questions = (JSONArray) jsonObject.get("ques");

        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for(Object questionObj : questions) {
            JSONObject questionJSON = (JSONObject) questionObj;
            QuestionDTO questionDTO =  questionService.convertToDto(questionJSON);
            questionService.registQuestion(questionDTO);
            questionDTOS.add(questionDTO);
        }

        HashMap map = new HashMap<>();
        map.put("ques", questionDTOS);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
