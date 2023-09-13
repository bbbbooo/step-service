package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookDTO;
import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookRequestDTO;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkbookService {

    private final WorkBookRepository workBookRepository;

    /* createWorkBookRequestDTO 내부의 문제 번호 리스트를 문자열로 파싱 후 저장합니다. */
    @Transactional
    public CreateWorkBookDTO createWorkbook(CreateWorkBookRequestDTO createWorkBookRequestDTO, Long memberNo) {
        WorkBook workBook = WorkBook.toEntity(memberNo, createWorkBookRequestDTO);
        workBookRepository.save(workBook);

        CreateWorkBookDTO createWorkBookDTO = CreateWorkBookDTO.fromEntity(workBook);

        return createWorkBookDTO;
    }

//    @Transactional
//    public void isSharedWorkBook(Long memberNo, List<Long> questionNos, Boolean isShared) {
//        for (Long questionNo : questionNos){
//            WorkBook workBook = workBookRepository.findByMemberNoAndQuestionNo(memberNo, questionNo);
//            workBook.updateIsShared(isShared);
//        }
//    }

//    @Transactional(readOnly = true)
//    public Page<ReadWorkBookDTO> getWorkBookMyPage(Long memberNo, Pageable pageable) {
//        Page<WorkBook> workBooks = workBookRepository.findByMemberNoOrderByGroupNo(memberNo, pageable);
//
//        Page<ReadWorkBookDTO> readWorkBookDTOS = ReadWorkBookDTO.fromEntity(workBooks);
//
//        return readWorkBookDTOS;
//    }
}
