package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.workbook.ReadWorkBookDTO;
import com.example.stepbackend.repository.WorkBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

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
        List<Integer> questionNos = Arrays.asList(1,2,3,4);
        Long memberNo = 1L;

        WorkBookRepository workBookRepository = mock(WorkBookRepository.class);
        WorkbookService workbookService = new WorkbookService(workBookRepository);

        String questionNosToString = questionNos.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        // when
        workbookService.createWorkbook(memberNo, questionNos);

        // then
        verify(workBookRepository).save(argThat(workbook ->
                workbook.getMemberNo().equals(memberNo) &&
                        workbook.getQuestionNos().equals(questionNosToString)
                ));
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