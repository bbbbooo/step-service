package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookDTO;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkbookService {

    private final WorkBookRepository workBookRepository;

    @Transactional
    public List<CreateWorkBookDTO> createWorkbook(Long memberNo, List<Long> questionNos) {
        List<CreateWorkBookDTO> createWorkBookDTOList = new ArrayList<>();
        for (Long questionNo : questionNos){
            WorkBook workBook = WorkBook.toEntity(memberNo, questionNo);
            workBookRepository.save(workBook);

            CreateWorkBookDTO createWorkBookDTO = CreateWorkBookDTO.builder()
                    .workBookNo(workBook.getWorkBookNo())
                    .memberNo(workBook.getMemberNo())
                    .questionNo(workBook.getQuestionNo())
                    .build();

            createWorkBookDTOList.add(createWorkBookDTO);
        }
        return createWorkBookDTOList;
    }

    @Transactional
    public void isSharedWorkBook(Long memberNo, List<Long> questionNos, Boolean isShared) {
        for (Long questionNo : questionNos){
            WorkBook workBook = workBookRepository.findByMemberNoAndQuestionNo(memberNo, questionNo);
            workBook.updateIsShared(isShared);
        }
    }
}
