package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookDTO;
import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookRequestDTO;
import com.example.stepbackend.aggregate.dto.workbook.ReadWorkBookDTO;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    @Transactional
    public void isSharedWorkBook(Long memberNo, Long workBookNo, Boolean isShared) {
        WorkBook workBook = workBookRepository.findByMemberNoAndWorkBookNo(memberNo, workBookNo);
        workBook.updateIsShared(isShared);
    }

    @Transactional(readOnly = true)
    public Page<ReadWorkBookDTO> getWorkBookMyPage(Long memberNo, Pageable pageable) {
        Page<WorkBook> workBooks = workBookRepository.findByMemberNo(memberNo, pageable);

        Page<ReadWorkBookDTO> readWorkBookDTOS = ReadWorkBookDTO.fromEntity(workBooks);

        return readWorkBookDTOS;
    }
}
