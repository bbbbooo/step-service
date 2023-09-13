package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.scrap.ReadScrapDTO;
import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookDTO;
import com.example.stepbackend.aggregate.dto.workbook.ReadWorkBookDTO;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkbookService {

    private final WorkBookRepository workBookRepository;

    @Transactional
    public CreateWorkBookDTO createWorkbook(Long memberNo, List<Integer> questionNos) {

        String questionNosToString = questionNos.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));

        WorkBook workBook = WorkBook.toEntity(memberNo, questionNosToString);
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
