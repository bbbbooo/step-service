package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.workbook.*;
import com.example.stepbackend.aggregate.entity.WorkBook;
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
        List<Long> questionNos = Arrays.asList(1L,2L,3L,4L);
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


    @DisplayName("나만의 문제집 마이 페이지 조회")
    @Test
    void getWorkBook(){
        // given
        Long memberNo = 1L;
        Pageable pageable = PageRequest.of(0, 10);

        // when
        Page<ReadWorkBookDTO> readWorkBookDTOS = workbookService.getWorkBookMyPage(memberNo, pageable);

        // then
        Assertions.assertNotNull(readWorkBookDTOS);
    }

    @DisplayName("나만의 문제집 상세 조회")
    @Test
    void getWorkBookDetail(){
        // given
        Long memberNo = 1L;

        List<Long> saveQuestionNos = Arrays.asList(1L,2L,3L);
        List<String> questionTypes = Arrays.asList("blank", "title");
        String workBookName = "미미스크립트";

        CreateWorkBookRequestDTO createWorkBookRequestDTO = CreateWorkBookRequestDTO.builder()
                .workBookName(workBookName)
                .questionNos(saveQuestionNos)
                .build();

        WorkBook saveWorkBook = WorkBook.toEntity(memberNo, createWorkBookRequestDTO, questionTypes);

        WorkBook foundWorkbook =  workBookRepository.save(saveWorkBook);

        WorkBook workBook = workBookRepository.findByMemberNoAndWorkBookNo(memberNo, foundWorkbook.getWorkBookNo());

        List<Integer> questionNos = Arrays.stream(workBook.getQuestionNos().split(", "))
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        // when
        ReadWorkBookDetailDTO readWorkBookDetailDTO = workbookService.getWorkBookDetailMyPage(memberNo, foundWorkbook.getWorkBookNo());

        // then
        Assertions.assertEquals(readWorkBookDetailDTO.getWorkBookName(),workBook.getWorkBookName());
        Assertions.assertEquals(readWorkBookDetailDTO.getQuestionNos(), questionNos);
    }

    @DisplayName("문제집 제목 입력받아 수정")
    @Test
    void updateWorkBook(){
        // given
        String workBookName = "룰루리랄라리";
        String description = "설명입니드아앙";

        WorkBook workBook = WorkBook.builder()
                .workBookName(workBookName)
                .description(description)
                .build();
        workBookRepository.save(workBook);

        UpdateWorkBookDTO updateWorkBookDTO = UpdateWorkBookDTO.builder()
                .workBookNo(workBook.getWorkBookNo())
                .workBookName(workBookName)
                .description(workBook.getDescription())
                .build();

        // when
        workbookService.updateWorkBookName(updateWorkBookDTO);

        // then
        Assertions.assertEquals(workBookName, workBookRepository.findByWorkBookNo(workBook.getWorkBookNo()).getWorkBookName());
        Assertions.assertEquals(description, workBookRepository.findByWorkBookNo(workBook.getWorkBookNo()).getDescription());
    }

    @DisplayName("문제집 삭제")
    @Test
    void deleteWorkBook(){
        // given
        WorkBook workBook = WorkBook.builder().build();
        WorkBook workBook2 = WorkBook.builder().build();

        workBookRepository.save(workBook);
        workBookRepository.save(workBook2);

        List<Long> workBookNos = Arrays.asList(workBook.getWorkBookNo(), workBook2.getWorkBookNo());

        DeleteWorkBookRequestDTO deleteWorkBookRequestDTO = DeleteWorkBookRequestDTO.builder()
                .workBookNos(workBookNos)
                .build();

        // when
        DeleteWorkBookResponseDTO deleteWorkBookResponseDTO = workbookService.deleteWorkBook(deleteWorkBookRequestDTO);

        // then
        Assertions.assertTrue(workBookNos.equals(deleteWorkBookResponseDTO.getWorkBookNos()));
    }
}