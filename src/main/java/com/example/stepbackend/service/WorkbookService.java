package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkbookService {

    private final WorkBookRepository workBookRepository;

    @Transactional
    public void createWorkbook(Long memberNo, List<Long> questionNos) {
        for (Long questionNo : questionNos){
            WorkBook workBook = WorkBook.toEntity(memberNo, questionNo);
            workBookRepository.save(workBook);
        }
    }
}
