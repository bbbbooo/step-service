package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.entity.Scrap;
import com.example.stepbackend.repository.ScrapRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScrapService {

    private final ScrapRepository scrapRepository;

    /* 스크랩 저장 */
    @Transactional
    public void createScrap(Long questionNo, Long memberNo) {
        Scrap scrap = Scrap.toEntity(questionNo, memberNo);
        scrapRepository.save(scrap);
    }
}
