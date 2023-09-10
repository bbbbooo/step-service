package com.example.stepbackend.service;

import com.example.stepbackend.repository.ScrapRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ScrapServiceTest {

    @Autowired
    private ScrapService scrapService;
    @Autowired
    private ScrapRepository scrapRepository;

    @DisplayName("스크랩 저장 확인")
    @Test
    void createScrapTest(){
        //given
        Long questionNo = 1L;

        //when
        scrapService.createScrap(questionNo);

        //then
        Assertions.assertEquals(1, scrapRepository.findById(questionNo));
    }
}