package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.board.ReadBoardQuestionResponseDTO;
import com.example.stepbackend.aggregate.dto.workbook.*;
import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.QuestionRepository;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
//    @Transactional(readOnly = true)
//    public Page<ReadWorkBookDTO> getWorkBookMyPage(Long memberNo, Pageable pageable, FilterWorkBookRequestDTO filterWorkBookRequestDTO) {
//        Page<WorkBook> workBooks = workBookRepository.findByMemberNo(memberNo, pageable);
//
//        Page<ReadWorkBookDTO> readWorkBookDTOS = ReadWorkBookDTO.fromEntity(workBooks);
//
//        return readWorkBookDTOS;
//    }

    /* 마이 페이지 내 문제집 전체 보기 */
    /* 필터링이 있을 경우, 조사하여 각각의 조건에 맞추어 검색함. 기본적으로 or 연산 */
    @Transactional(readOnly = true)
    public Page<ReadWorkBookDTO> getWorkBookMyPage(Long memberNo, Pageable pageable, FilterWorkBookRequestDTO filterWorkBookRequestDTO) {
        List<WorkBook> filteredWorkBooks = new ArrayList<>();

        if (filterWorkBookRequestDTO != null) {
            if (filterWorkBookRequestDTO.getTitleOption() != null && filterWorkBookRequestDTO.getTitleOption()) {
                Page<WorkBook> titleWorkBooks = workBookRepository.findByMemberNoAndQuestionTypesContaining(memberNo, "title", pageable);
                filteredWorkBooks.addAll(titleWorkBooks.getContent());
            }

            if (filterWorkBookRequestDTO.getBlankOption() != null && filterWorkBookRequestDTO.getBlankOption()) {
                Page<WorkBook> blankWorkBooks = workBookRepository.findByMemberNoAndQuestionTypesContaining(memberNo, "blank", pageable);
                filteredWorkBooks.addAll(blankWorkBooks.getContent());
            }

            if (filterWorkBookRequestDTO.getOrderOption() != null && filterWorkBookRequestDTO.getOrderOption()) {
                Page<WorkBook> blankWorkBooks = workBookRepository.findByMemberNoAndQuestionTypesContaining(memberNo, "shuffle1", pageable);
                filteredWorkBooks.addAll(blankWorkBooks.getContent());
            }

            if (filterWorkBookRequestDTO.getSentenceOption() != null && filterWorkBookRequestDTO.getSentenceOption()) {
                Page<WorkBook> blankWorkBooks = workBookRepository.findByMemberNoAndQuestionTypesContaining(memberNo, "shuffle2", pageable);
                filteredWorkBooks.addAll(blankWorkBooks.getContent());
            }

            if (filterWorkBookRequestDTO.getTopicOption() != null && filterWorkBookRequestDTO.getTopicOption()) {
                Page<WorkBook> blankWorkBooks = workBookRepository.findByMemberNoAndQuestionTypesContaining(memberNo, "topic", pageable);
                filteredWorkBooks.addAll(blankWorkBooks.getContent());
            }

            if (filterWorkBookRequestDTO.getSharedOption() != null && filterWorkBookRequestDTO.getSharedOption()) {
                Page<WorkBook> sharedWorkBooks = workBookRepository.findByMemberNoAndIsShared(memberNo, true, pageable);
                filteredWorkBooks.addAll(sharedWorkBooks.getContent());
            }

            if (filterWorkBookRequestDTO.getReceivedOption() != null && filterWorkBookRequestDTO.getReceivedOption()) {
                Page<WorkBook> receivedWorkBooks = workBookRepository.findByMemberNoAndHadShared(memberNo, true, pageable);
                filteredWorkBooks.addAll(receivedWorkBooks.getContent());
            }
        }

        if (filteredWorkBooks.isEmpty()) {
            // 필터링된 결과가 비어 있는 경우, 모든 워크북을 조회한 결과를 반환
            Page<WorkBook> allWorkBooks = workBookRepository.findByMemberNo(memberNo, pageable);
            return ReadWorkBookDTO.fromEntity(allWorkBooks);
        }

        Page<ReadWorkBookDTO> readWorkBookDTOS = ReadWorkBookDTO.fromEntity(new PageImpl<>(filteredWorkBooks, pageable, filteredWorkBooks.size()));
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

    /* 문제집 삭제 */
    @Transactional
    public DeleteWorkBookResponseDTO deleteWorkBook(DeleteWorkBookRequestDTO deleteWorkBookRequestDTO) {
        for (Long workBookNo : deleteWorkBookRequestDTO.getWorkBookNos()){
            workBookRepository.deleteById(workBookNo);
        }

        DeleteWorkBookResponseDTO deleteWorkBookResponseDTO = DeleteWorkBookResponseDTO.toRequest(deleteWorkBookRequestDTO);
        return deleteWorkBookResponseDTO;
    }

    /* 문제집 내 리스트 불러오기 */
    @Transactional(readOnly = true)
    public List<ReadBoardQuestionResponseDTO> findAllBoardQuestion(Long workBookNo) {
        String questionNoStr = workBookRepository.findQuestionNosbyWorkBookNo(workBookNo);

        List<Long> questionNos = Arrays.stream(questionNoStr.split(", "))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<Question> questions = questionRepository.findByQuestionNoIn(questionNos);

        List<ReadBoardQuestionResponseDTO> responseDTOList = ReadBoardQuestionResponseDTO.fromEntity(questions);

        return responseDTOList;
    }

    @Transactional(readOnly = true)
    public FindWorkBookResponse findWorkBook(Long workBookNo) {
        WorkBook workBook = workBookRepository.findByWorkBookNo(workBookNo);

        FindWorkBookResponse findWorkBookResponse = FindWorkBookResponse.fromEntity(workBook);

        return findWorkBookResponse;
    }


//    public FilterWorkBookResponseDTO filterOption(FilterWorkBookRequestDTO filterWorkBookRequestDTO, Long memberNo) {
//    }
}
