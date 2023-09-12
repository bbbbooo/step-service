package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.scrap.ScrapListDTO;
import com.example.stepbackend.repository.ScrapRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
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
        Long scrapNo = 12L;

        //when
        scrapService.createScrap(questionNo, memberNo);

        //then
        Assertions.assertEquals(memberNo, scrapRepository.findById(scrapNo).get().getMemberNo());
        Assertions.assertEquals(questionNo, scrapRepository.findById(scrapNo).get().getQuestionNo());
    }

    @DisplayName("한 회원에 대한 스크랩 모두 조회")
    @Test
    void findScrapTest(){
        //given
        Long memberNo = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        //when
        Page<ScrapListDTO> scrapPage = scrapService.findAllScrap(memberNo, pageable);

        //then
        assertNotNull(scrapPage);
    }

    @DisplayName("한 회원에 대한 스크랩 취소")
    @Test
    void cancelScrap(){
        //given
        Long memberNo = 1L;
        List<Long> questionNos = Arrays.asList(1L, 2L, 3L);

        //when
        scrapService.cancelScrap(memberNo, questionNos);

        //then
        Assertions.assertEquals(0, scrapRepository.findByMemberNoAndQuestionNoIn(memberNo, questionNos).size());
    }
}