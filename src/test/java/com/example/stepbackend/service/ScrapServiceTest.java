package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.scrap.CancelScrapRequestDTO;
import com.example.stepbackend.aggregate.dto.scrap.CancelScrapResponseDTO;
import com.example.stepbackend.aggregate.dto.scrap.CreateScrapDTO;
import com.example.stepbackend.aggregate.dto.scrap.ReadScrapDTO;
import com.example.stepbackend.aggregate.entity.Scrap;
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

    @DisplayName("요청과 회원 번호를 받아 스크랩을 저장하는지")
    @Test
    void createScrapTest(){
        //given
        Long memberNo = 1L;
        Long questionNo = 1L;
        CreateScrapDTO createScrapDTO = CreateScrapDTO.builder()
                .questionNo(questionNo)
                .markedNo(2)
                .correctedMarkingStatus(false)
                .build();

        //when
        Scrap scrap = Scrap.toEntity(createScrapDTO, memberNo);

        Scrap createdScrap = scrapRepository.save(scrap);

        //then
        Assertions.assertEquals(memberNo, createdScrap.getMemberNo());
        Assertions.assertEquals(questionNo, createdScrap.getQuestionNo());
    }

    @DisplayName("한 회원에 대한 스크랩 모두 조회")
    @Test
    void findScrapTest(){
        //given
        Long memberNo = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        //when
        Page<ReadScrapDTO> scrapPage = scrapService.findAllScrap(memberNo, pageable);

        //then
        assertNotNull(scrapPage);
    }

    @DisplayName("한 회원에 대한 스크랩 취소")
    @Test
    void cancelScrap(){
        //given
        Scrap scrap = new Scrap();
        Scrap scrap2 = new Scrap();

        scrapRepository.save(scrap);
        scrapRepository.save(scrap2);

        List<Long> scrapNos = Arrays.asList(scrap.getScrapNo(), scrap2.getScrapNo());

        CancelScrapRequestDTO cancelScrapRequestDTO = CancelScrapRequestDTO.builder()
                .scrapNos(scrapNos)
                .build();

        //when
        CancelScrapResponseDTO cancelScrapResponseDTO = scrapService.cancelScrap(cancelScrapRequestDTO);

        //then
        Assertions.assertTrue(cancelScrapResponseDTO.getScrapNos().equals(scrapNos));

    }
}