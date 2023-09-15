package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookDTO;
import com.example.stepbackend.aggregate.dto.workbook.CreateWorkBookRequestDTO;
import com.example.stepbackend.aggregate.dto.workbook.ReadWorkBookDTO;
import com.example.stepbackend.aggregate.dto.workbook.UpdateWorkBookNameDTO;
import com.example.stepbackend.service.WorkbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/workbook")
public class WorkBookController {

    private final WorkbookService workbookService;

    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<CreateWorkBookDTO> create(@RequestBody CreateWorkBookRequestDTO createWorkBookRequestDTO){
        Long memberNo = 1L;
        CreateWorkBookDTO createWorkBookDTO = workbookService.createWorkbook(createWorkBookRequestDTO, memberNo);

        return ResponseEntity.ok(createWorkBookDTO);
    }

    @GetMapping("myPage/myWorkBook")
    public String findAll(@PageableDefault(size = 15) Pageable pageable, Model model){
        Long memberNo = 1L;

        Page<ReadWorkBookDTO> readWorkBookDTOS = workbookService.getWorkBookMyPage(memberNo, pageable);

        model.addAttribute("workbooks", readWorkBookDTOS);

        return "workbook/myWorkbookPage";
    }

    @PatchMapping("myPage/update")
    @ResponseBody
    public String updateWorkBookName(@RequestBody UpdateWorkBookNameDTO updateWorkBookNameDTO){
        workbookService.updateWorkBookName(updateWorkBookNameDTO);

        return "success";
    }
}
