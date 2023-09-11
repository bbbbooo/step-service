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
class ScrapServiceTest {

    @Autowired
    private ScrapService scrapService;
    @Autowired
    private ScrapRepository scrapRepository;

    @DisplayName("회원 번호와 문제 번호를 받아 스크랩을 저장하는지")
    @Test
    void createScrapTest(){
        //given
        Long memberNo = 1L;
        Long questionNo = 4L;
        Long scrapNo = 1L;

        //when
        scrapService.createScrap(questionNo, memberNo);

        //then
        Assertions.assertEquals(memberNo, scrapRepository.findById(scrapNo).get().getMemberNo());
        Assertions.assertEquals(questionNo, scrapRepository.findById(scrapNo).get().getQuestionNo());
    }

    @DisplayName("생성된 스크랩 조회")
    @Test
    void readScrapTest(){
        //given


        //when


        //then
    }
}