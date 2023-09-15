package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.question.QuestionDTO;
import com.example.stepbackend.aggregate.dto.question.ReqQuestionByMemberDTO;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.aggregate.entity.QuestionByMember;
import com.example.stepbackend.global.exception.ResourceNotFoundException;
import com.example.stepbackend.repository.QuestionRepository;
import com.example.stepbackend.repository.QuestionByMemberRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionByMemberRepository questionByMemberRepository;
    private final ModelMapper modelMapper;

    public List<ResQuestionDTO> readQuestion(String type, Long userId) throws ResourceNotFoundException {
        List<Long> questionsByMember = questionByMemberRepository.findQuestionByMemberByQuestionByMemberNo(userId);

        List<Question> questions;

        if(questionsByMember.isEmpty()) {
            questions = questionRepository.findTop10ByQuestionViewType(type);
        } else {
            questions = questionRepository.findTop10ByQuestionNoNotInAndQuestionViewType(questionsByMember, type);
        }

        List<ResQuestionDTO> results = new ArrayList<>();

        if(!questions.isEmpty()) {

            results = questions.stream().map(question -> modelMapper.map(question, ResQuestionDTO.class)).collect(Collectors.toList());

            return results;
        }

        return results;
    }

    @Transactional
    public void registQuestion(QuestionDTO reqQuestionDto) throws Exception {

        if(reqQuestionDto == null) throw new Exception("등록할 문제가 없습니다.");

        Question question = reqQuestionDto.toEntity();

        Question foundQuestion =  questionRepository.save(question);
    }

    public QuestionDTO convertToDto(JSONObject jsonObject, String questionCount, String classification) {

        JSONObject json = (JSONObject) jsonObject.get("Q"+questionCount);

        String main = (String) json.get("main");
        Integer answer = (Integer) json.get("answer");
        String subject = null;
        String view1 = (String) json.get("view1");
        String view2 = (String) json.get("view2");
        String view3 = (String) json.get("view3");
        String view4 = (String) json.get("view4");
        String view5 = (String) json.get("view5");

        QuestionDTO result = new QuestionDTO();
        result.setQuestionBody(main);
        result.setQuestionViewType(classification);
        result.setQuestionCorrectAnswer(answer);

        if(classification.equals("title")) {
            subject = "다음 글의 제목으로 가장 적절한 것은?";
        } else {
            subject = "다음 빈칸에 들어갈 말로 가장 적절한 것을 고르시오.";
        }

        result.setQuestionSubject(subject);
        result.setView1(view1);
        result.setView2(view2);
        result.setView3(view3);
        result.setView4(view4);
        result.setView5(view5);

        return result;
    }

    @Transactional
    public Long registQuestionByMember(ReqQuestionByMemberDTO req, Long memberNo) {

        QuestionByMember questionByMember = new QuestionByMember();
        questionByMember.setQuestionNo(req.getQuestionNo());
        questionByMember.setMemberNo(memberNo);
        questionByMember.setMarkedNo(req.getMarkedNo());
        questionByMember.setCorrectedMarkingStatus(req.getCorrectedMarkingStatus());

        QuestionByMember foundMemberHistory = questionByMemberRepository.save(questionByMember);

        return foundMemberHistory.getQuestionByMemberNo();
    }
}
