package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.scrap.CreateScrapDTO;
import com.example.stepbackend.aggregate.dto.scrap.ReadScrapByMemberDTO;
import com.example.stepbackend.aggregate.dto.scrap.ReadScrapDTO;
import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.aggregate.entity.QuestionByMember;
import com.example.stepbackend.aggregate.entity.Scrap;
import com.example.stepbackend.repository.QuestionByMemberRepository;
import com.example.stepbackend.repository.QuestionRepository;
import com.example.stepbackend.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;

    private final QuestionRepository questionRepository;

    private final QuestionByMemberRepository questionByMemberRepository;

    /* 스크랩 저장 */
//    @Transactional
//    public void createScrap(CreateScrapDTO createScrapDTO, Long memberNo, Long questionNo) {
//        Scrap scrap = Scrap.toEntity(createScrapDTO, memberNo, questionNo);
//        scrapRepository.save(scrap);
//    }

    /* 한 회원에 대한 모든 스크랩 조회*/
    @Transactional(readOnly = true)
    public Page<ReadScrapDTO> findAllScrap(Long memberNo, Pageable pageable) {
        Page<Question> questions = scrapRepository.findAllByMemberNo(memberNo, pageable);

        Page<ReadScrapDTO> pagedScraps = ReadScrapDTO.fromEntity(questions);
        return pagedScraps;
    }

    @Transactional(readOnly = true)
    public Page<ReadScrapByMemberDTO> findAllScrapByMember(Long memberNo, Pageable pageable) {
        Page<QuestionByMember> questionByMembers = questionByMemberRepository.findByMemberNo(memberNo, pageable);

        Page<ReadScrapByMemberDTO> readScrapByMemberDTOS = ReadScrapByMemberDTO.fromEntity(questionByMembers);

        return readScrapByMemberDTOS;
    }

    @Transactional(readOnly = true)
    public ReadScrapDTO findScrap(Long memberNo, Long questionNo) {
        QuestionByMember questionByMember = questionByMemberRepository.findByMemberNoAndQuestionNo(memberNo, questionNo);
        Question question = questionRepository.findByQuestionNo(questionByMember.getQuestionNo());

        ReadScrapDTO readScrapDTO = ReadScrapDTO.fromEntity(question);

        return readScrapDTO;
    }

    @Transactional(readOnly = true)
    public ReadScrapByMemberDTO findScrapByMember(Long memberNo, Long questionNo) {
        QuestionByMember question = questionByMemberRepository.findByMemberNoAndQuestionNo(memberNo, questionNo);

        ReadScrapByMemberDTO readScrapByMemberDTO = ReadScrapByMemberDTO.fromEntity(question);

        return readScrapByMemberDTO;
    }

    /* 한 회원에 대한 모든 스크랩 삭제*/
    @Transactional
    public void cancelScrap(Long memberNo, List<Long> questionNos) {
        List<Scrap> scraps = scrapRepository.findByMemberNoAndQuestionNoIn(memberNo, questionNos);

        for(Scrap scrap : scraps){
            scrapRepository.delete(scrap);
        }
    }
}
