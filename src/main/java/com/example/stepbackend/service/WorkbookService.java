package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.workbook.*;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.QuestionRepository;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkbookService {

    private final WorkBookRepository workBookRepository;

    private final QuestionRepository questionRepository;

    /* 문제집 생성 */
    /* createWorkBookRequestDTO 내부의 문제 번호 리스트를 문자열로 파싱 후 저장합니다. */
    @Transactional
    public CreateWorkBookDTO createWorkbook(CreateWorkBookRequestDTO createWorkBookRequestDTO, Long memberNo) {
        List<String> questionTypes = workBookRepository.findDistinctQuestionTypes(createWorkBookRequestDTO.getQuestionNos());

        WorkBook workBook = WorkBook.toEntity(memberNo, createWorkBookRequestDTO, questionTypes);
        workBookRepository.save(workBook);

        CreateWorkBookDTO createWorkBookDTO = CreateWorkBookDTO.fromEntity(workBook);

        return createWorkBookDTO;
    }

    /* 마이 페이지 내 문제집 전체 보기*/
    @Transactional(readOnly = true)
    public Page<ReadWorkBookDTO> getWorkBookMyPage(Long memberNo, Pageable pageable) {
        Page<WorkBook> workBooks = workBookRepository.findByMemberNo(memberNo, pageable);

        Page<ReadWorkBookDTO> readWorkBookDTOS = ReadWorkBookDTO.fromEntity(workBooks);

        return readWorkBookDTOS;
    }

    /* 문제집 상세 조회 */
    @Transactional(readOnly = true)
    public ReadWorkBookDetailDTO getWorkBookDetailMyPage(Long memberNo, Long workBookNo) {
        WorkBook workBook = workBookRepository.findByMemberNoAndWorkBookNo(memberNo, workBookNo);

        ReadWorkBookDetailDTO readWorkBookDetailDTO = ReadWorkBookDetailDTO.fromEntity(workBook);

        return readWorkBookDetailDTO;
    }

    /* 문제집 제목 수정 */
    @Transactional
    public UpdateWorkBookResponseDTO updateWorkBookName(UpdateWorkBookDTO updateWorkBookDTO) {
        WorkBook workBook = workBookRepository.findByWorkBookNo(updateWorkBookDTO.getWorkBookNo());

        if (workBook == null){
            return null;
        }
        workBook.updateWorkBookName(updateWorkBookDTO.getWorkBookName(), updateWorkBookDTO.getDescription());

        UpdateWorkBookResponseDTO updateWorkBookResponseDTO = UpdateWorkBookResponseDTO.toEntity(workBook);

        return updateWorkBookResponseDTO;
    }
}
