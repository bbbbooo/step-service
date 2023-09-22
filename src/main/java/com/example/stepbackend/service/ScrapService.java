package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.board.ReadScrapBoardDTO;
import com.example.stepbackend.aggregate.dto.scrap.*;
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

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;

    private final QuestionRepository questionRepository;

    private final QuestionByMemberRepository questionByMemberRepository;

    /* 스크랩 저장 */
    @Transactional
    public void createScrap(CreateScrapDTO createScrapDTO, Long memberNo) {
        Scrap scrap = Scrap.toEntity(createScrapDTO, memberNo);
        scrapRepository.save(scrap);
    }

    /* 한 회원에 대한 모든 스크랩 조회*/
    @Transactional(readOnly = true)
    public Page<ReadScrapDTO> findAllScrap(Long memberNo, Pageable pageable) {
        Page<Question> questions = scrapRepository.findAllByMemberNo(memberNo, pageable);

        Page<ReadScrapDTO> pagedScraps = ReadScrapDTO.fromEntity(questions);
        return pagedScraps;
    }

    /* 문제 푼 내역 조사 */
    @Transactional(readOnly = true)
    public Page<ReadScrapByMemberDTO> findAllScrapByMember(Long memberNo, Pageable pageable) {
        Page<QuestionByMember> questionByMembers = questionByMemberRepository.findByMemberNo(memberNo, pageable);

        Page<ReadScrapByMemberDTO> readScrapByMemberDTOS = ReadScrapByMemberDTO.fromEntity(questionByMembers);

        return readScrapByMemberDTOS;
    }

    /* 스크랩 상세 조회 */
    @Transactional(readOnly = true)
    public ReadScrapDTO findScrap(Long memberNo, Long questionNo, Integer markedNo) {
        QuestionByMember questionByMember = questionByMemberRepository.findByMemberNoAndQuestionNoAndMarkedNo(memberNo, questionNo, markedNo);
        Question question = questionRepository.findByQuestionNo(questionByMember.getQuestionNo());

        ReadScrapDTO readScrapDTO = ReadScrapDTO.fromEntity(question);

        return readScrapDTO;
    }

    /* 스크랩 한개에 대한 회원별 내역 조사 */
    @Transactional(readOnly = true)
    public ReadScrapByMemberDTO findScrapByMember(Long memberNo, Long questionNo, Integer markedNo) {
        QuestionByMember question = questionByMemberRepository.findByMemberNoAndQuestionNoAndMarkedNo(memberNo, questionNo, markedNo);

        ReadScrapByMemberDTO readScrapByMemberDTO = ReadScrapByMemberDTO.fromEntity(question);

        return readScrapByMemberDTO;
    }

    /* 한 회원에 대한 모든 스크랩 삭제*/
    @Transactional
    public CancelScrapResponseDTO cancelScrap(CancelScrapRequestDTO cancelScrapRequestDTO) {
        for(Long scrapNo : cancelScrapRequestDTO.getScrapNos()){
            Scrap scrap = scrapRepository.findByScrapNo(scrapNo);
            scrapRepository.delete(scrap);
        }

        CancelScrapResponseDTO cancelScrapResponseDTO = CancelScrapResponseDTO.toDTO(cancelScrapRequestDTO);
        return cancelScrapResponseDTO;
    }

    /* 스크랩 여부 확인 */
    @Transactional(readOnly = true)
    public ReadScrapBoardDTO findScrapBoard(Long memberNo, Long questionNo, Integer markedNo) {
        Scrap scrap = scrapRepository.findByMemberNoAndQuestionNoAndMarkedNo(memberNo, questionNo, markedNo);

        if (scrap == null){
            return null;
        }

        ReadScrapBoardDTO readScrapBoardDTO = ReadScrapBoardDTO.fromEntity(scrap);

        return readScrapBoardDTO;
    }

    /* 스크랩 번호 조사 */
    @Transactional(readOnly = true)
    public Page<ReadScrapListDTO> findScrapNo(Long memberNo, Pageable pageable) {
        Page<Scrap> scraps = scrapRepository.findByMemberNo(memberNo, pageable);
        Page<ReadScrapListDTO> readScrapListDTOS = ReadScrapListDTO.fromEntity(scraps);

        return readScrapListDTOS;
    }
}
