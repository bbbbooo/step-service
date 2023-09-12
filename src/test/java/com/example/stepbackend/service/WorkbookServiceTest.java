package com.example.stepbackend.service;

import com.example.stepbackend.repository.WorkBookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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
}