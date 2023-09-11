package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.scrap.ScrapDTO;
import com.example.stepbackend.aggregate.dto.scrap.ScrapListDTO;
import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.aggregate.entity.Scrap;
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

    /* 스크랩 저장 */
    @Transactional
    public void createScrap(Long questionNo, Long memberNo) {
        Scrap scrap = Scrap.toEntity(questionNo, memberNo);
        scrapRepository.save(scrap);
    }

    /* 한 회원에 대한 모든 스크랩 조회*/
    @Transactional(readOnly = true)
    public Page<ScrapListDTO> findAllScrap(Long memberNo, Pageable pageable) {
        Page<Question> scraps = scrapRepository.findAllByMemberNo(memberNo, pageable);

        Page<ScrapListDTO> pagedScraps = ScrapListDTO.fromEntity(scraps);
        return pagedScraps;
    }
}
