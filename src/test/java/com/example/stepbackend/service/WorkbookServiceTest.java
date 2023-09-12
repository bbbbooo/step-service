package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WorkbookServiceTest {

    @Autowired
    private WorkbookService workbookService;

    @DisplayName("나만의 문제집 등록")
    @Test
    void createWorkbook(){
        // given
        List<Long> questionNos = Arrays.asList(1L, 2L, 3L);
        Long memberNo = 1L;

        // when
        workbookService.createWorkbookTest(memberNo, questionNos);

        // then
    }
}