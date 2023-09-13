package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookDTO;
import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookRequestDTO;
import com.example.stepbackend.repository.WorkBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@Transactional
class WorkbookServiceTest {

    @Autowired
    private WorkbookService workbookService;

    @Autowired
    private WorkBookRepository workBookRepository;


    @DisplayName("나만의 문제집 등록")
    @Test
    void createWorkbook(){
        // given
        Long memberNo = 1L;
        List<Integer> questionNos = Arrays.asList(1,2,3,4);
        String workBookName = "미미스크립트";

        CreateWorkBookRequestDTO createWorkBookRequest = CreateWorkBookRequestDTO.builder()
                .workBookName(workBookName)
                .questionNos(questionNos)
                .build();

        String questionNosToString = questionNos.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        // when
        CreateWorkBookDTO createWorkBookDTO = workbookService.createWorkbook(createWorkBookRequest, memberNo);

        // then
        Assertions.assertTrue(createWorkBookDTO.getMemberNo().equals(memberNo) &&
                createWorkBookDTO.getQuestionNos().equals(questionNosToString) &&
                createWorkBookDTO.getWorkBookName().equals(workBookName)
                );
    }


//    @DisplayName("나만의 문제집 공유 설정")
//    @ParameterizedTest
//    @ValueSource(booleans = {false, true})
//    void isSharedWorkBook(boolean settings){
//        // given
//        Long memberNo = 1L;
//        List<Long> questionNos = Arrays.asList(101L, 102L);
//
//        Boolean isShared = settings;
//
//        // when
//        workbookService.isSharedWorkBook(memberNo, questionNos, isShared);
//
//        // then
//        if(isShared){
//            Assertions.assertTrue(workBookRepository.findByMemberNoAndQuestionNo(memberNo, 102L).getIsShared());
//        }else{
//            Assertions.assertFalse(workBookRepository.findByMemberNoAndQuestionNo(memberNo, 101L).getIsShared());
//        }
//    }


//    @DisplayName("나만의 문제집 마이 페이지 조회")
//    @Test
//    void getWorkBook(){
//        // given
//        Long memberNo = 1L;
//        Pageable pageable = PageRequest.of(0, 10);
//
//        // when
//        Page<ReadWorkBookDTO> readWorkBookDTOS = workbookService.getWorkBookMyPage(memberNo, pageable);
//
//        // then
//    }
}