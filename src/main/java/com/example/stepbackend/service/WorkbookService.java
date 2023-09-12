package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.scrap.ScrapListDTO;
import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookDTO;
import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.aggregate.entity.Scrap;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.ScrapRepository;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkbookService {

    private final ScrapRepository scrapRepository;

    private final WorkBookRepository workBookRepository;

    @Transactional
    public void createWorkbookTest(Long memberNo, List<Long> questionNos) {
        List<Question> questions = scrapRepository.findAllByMemberNo(memberNo);

        List<CreateWorkBookDTO> createWorkBookDTOList = CreateWorkBookDTO.fromEntity(questions);
    }
}
