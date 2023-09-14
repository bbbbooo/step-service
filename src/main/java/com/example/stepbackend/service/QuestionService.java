package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.question.QuestionDTO;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.global.exception.ResourceNotFoundException;
import com.example.stepbackend.repository.QuestionRepository;
import com.example.stepbackend.repository.QuestionByMemberRepository;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionByMemberRepository QuestionByMemberRepository;
    private final ModelMapper modelMapper;

    public List<ResQuestionDTO> readQuestion(Long userId) throws ResourceNotFoundException {
        List<Long> questionsBymember = QuestionByMemberRepository.findQuestionByMemberByQuestionByMemberNo(userId);
        List<Question> questions = questionRepository.findTop20ByQuestionNoNotIn(questionsBymember);

        if(!questions.isEmpty()) {

            List<ResQuestionDTO> results = questions.stream().map(question -> modelMapper.map(question, ResQuestionDTO.class)).collect(Collectors.toList());

            return results;
        } else {
            throw new ResourceNotFoundException("회원에게 제공할 만한 문제가 없습니다.");
        }
    }

    public void registQuestion(QuestionDTO reqQuestionDto) throws Exception {

        if(reqQuestionDto == null) throw new Exception("등록할 문제가 없습니다.");

        Question question = reqQuestionDto.toEntity();

        questionRepository.save(question);
    }

    public QuestionDTO convertToDto(JSONObject jsonObject) {

        String main = (String) jsonObject.get("main");
        String classification = (String) jsonObject.get("class");
        Integer answer = (Integer) jsonObject.get("answer");
        String subject = (String) jsonObject.get("subject");
        String view1 = (String) jsonObject.get("view1");
        String view2 = (String) jsonObject.get("view2");
        String view3 = (String) jsonObject.get("view3");
        String view4 = (String) jsonObject.get("view4");
        String view5 = (String) jsonObject.get("view5");

        QuestionDTO result = new QuestionDTO();
        result.setQuestionBody(main);
        result.setQuestionViewType(classification);
        result.setQuestionCorrectAnswer(answer);
        result.setQuestionSubject(subject);
        result.setView1(view1);
        result.setView2(view2);
        result.setView3(view3);
        result.setView4(view4);
        result.setView5(view5);

        return result;
    }
}
