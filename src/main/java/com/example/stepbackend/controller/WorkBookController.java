package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.board.ReadBoardQuestionResponseDTO;
import com.example.stepbackend.aggregate.dto.workbook.*;
import com.example.stepbackend.global.common.annotation.CurrentUser;
import com.example.stepbackend.global.security.token.UserPrincipal;
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

    /* 문제집 생성 */
    @PostMapping("create")
    @ResponseBody
    public ResponseEntity<CreateWorkBookDTO> create(@RequestBody CreateWorkBookRequestDTO createWorkBookRequestDTO,
                                                    @CurrentUser UserPrincipal user){
        Long memberNo = user.getId();
        CreateWorkBookDTO createWorkBookDTO = workbookService.createWorkbook(createWorkBookRequestDTO, memberNo);

        return ResponseEntity.ok(createWorkBookDTO);
    }

    /* 문제집 존재 여부 확인 */
    @GetMapping("/find")
    @ResponseBody
    public ResponseEntity<FindWorkBookResponse> find(@RequestParam Long workBookNo){
        FindWorkBookResponse findWorkBookResponse = workbookService.findWorkBook(workBookNo);

        return ResponseEntity.ok(findWorkBookResponse);
    }

    /* 문제집 불러오기 */
    @GetMapping("myPage/myWorkBook")
    public String findAll(@PageableDefault(size = 15, sort = "workBookNo", direction = Sort.Direction.DESC) Pageable pageable,
                          @ModelAttribute FilterWorkBookRequestDTO filterWorkBookRequestDTO,
                          @CurrentUser UserPrincipal user,
                          Model model){
        Long memberNo = user.getId();

        Page<ReadWorkBookDTO> readWorkBookDTOS = workbookService.getWorkBookMyPage(memberNo, pageable, filterWorkBookRequestDTO);

        model.addAttribute("workbooks", readWorkBookDTOS);

        return "workbook/myWorkbookPage";
    }

    /* 문제집 제목, 설명 수정 */
    @PatchMapping("myPage/update")
    @ResponseBody
    public String updateWorkBookName(@RequestBody UpdateWorkBookDTO updateWorkBookDTO){
        UpdateWorkBookResponseDTO updatedWorkBookName = workbookService.updateWorkBookName(updateWorkBookDTO);

        return "success";
    }

    /* 문제집 삭제 */
    @DeleteMapping("myPage/delete")
    @ResponseBody
    public ResponseEntity<DeleteWorkBookResponseDTO> deleteWorkBook(@RequestBody DeleteWorkBookRequestDTO deleteWorkBookRequestDTO){
        DeleteWorkBookResponseDTO deleteWorkBookResponseDTO = workbookService.deleteWorkBook(deleteWorkBookRequestDTO);
        return ResponseEntity.ok(deleteWorkBookResponseDTO);
    }

    /* 문제 풀기 페이지 */
    @GetMapping("/question")
    public String question(@RequestParam("workBookNo")Long workBookNo , Model model){
        List<ReadBoardQuestionResponseDTO> responseDTOList = workbookService.findAllBoardQuestion(workBookNo);

        model.addAttribute("questions", responseDTOList);

        return "board/question";
    }
}
