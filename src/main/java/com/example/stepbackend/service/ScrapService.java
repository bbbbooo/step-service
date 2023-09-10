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

    @Transactional
    public void createScrap(Long questionNo) {
        Scrap scrap = scrapRepository.findByProblemNo(questionNo);

        scrapRepository.save(scrap);
    }
}
