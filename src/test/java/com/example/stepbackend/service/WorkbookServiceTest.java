package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.WorkBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

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
        List<Long> questionNos = Arrays.asList(1L, 2L, 3L);
        Long memberNo = 1L;

        WorkBookRepository workBookRepository = mock(WorkBookRepository.class);
        WorkbookService workbookService = new WorkbookService(workBookRepository);

        // when
        workbookService.createWorkbook(memberNo, questionNos);

        // then
        for (Long questionNo : questionNos){
            verify(workBookRepository).save(argThat(workbook ->
                    workbook.getMemberNo().equals(memberNo) &&
                    workbook.getQuestionNo().equals(questionNo)
            ));
        }
    }


    @DisplayName("나만의 문제집 공유 설정")
    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void isSharedWorkBook(boolean settings){
        // given
        Long memberNo = 1L;
        List<Long> questionNos = Arrays.asList(101L, 102L);

        Boolean isShared = settings;

        // when
        workbookService.isSharedWorkBook(memberNo, questionNos, isShared);

        // then
        if(isShared){
            Assertions.assertTrue(workBookRepository.findByMemberNoAndQuestionNo(memberNo, 102L).getIsShared());
        }else{
            Assertions.assertFalse(workBookRepository.findByMemberNoAndQuestionNo(memberNo, 101L).getIsShared());
        }
    }

//    @DisplayName("나만의 문제집 좋아요 등록")
//    @Test
//    void heartWorkBook(){
//
//    }
}