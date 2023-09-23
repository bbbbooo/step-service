package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.board.ReadBoardQuestionResponseDTO;
import com.example.stepbackend.aggregate.dto.workbook.*;
import com.example.stepbackend.service.WorkbookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/find")
    @ResponseBody
    public ResponseEntity<FindWorkBookResponse> find(@RequestParam Long workBookNo){
        FindWorkBookResponse findWorkBookResponse = workbookService.findWorkBook(workBookNo);

        return ResponseEntity.ok(findWorkBookResponse);
    }

    @GetMapping("myPage/myWorkBook")
    public String findAll(@PageableDefault(size = 15, sort = "workBookNo", direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Long memberNo = 1L;

        Page<ReadWorkBookDTO> readWorkBookDTOS = workbookService.getWorkBookMyPage(memberNo, pageable);

        model.addAttribute("workbooks", readWorkBookDTOS);

        return "workbook/myWorkbookPage";
    }

    @PatchMapping("myPage/update")
    @ResponseBody
    public String updateWorkBookName(@RequestBody UpdateWorkBookDTO updateWorkBookDTO){
        UpdateWorkBookResponseDTO updatedWorkBookName = workbookService.updateWorkBookName(updateWorkBookDTO);

        return "success";
    }

    @DeleteMapping("myPage/delete")
    @ResponseBody
    public ResponseEntity<DeleteWorkBookResponseDTO> deleteWorkBook(@RequestBody DeleteWorkBookRequestDTO deleteWorkBookRequestDTO){
        DeleteWorkBookResponseDTO deleteWorkBookResponseDTO = workbookService.deleteWorkBook(deleteWorkBookRequestDTO);
        return ResponseEntity.ok(deleteWorkBookResponseDTO);
    }

    @GetMapping("/question")
    public String question(@RequestParam("workBookNo")Long workBookNo , Model model){
        List<ReadBoardQuestionResponseDTO> responseDTOList = workbookService.findAllBoardQuestion(workBookNo);

        model.addAttribute("questions", responseDTOList);

        return "board/question";
    }
}
