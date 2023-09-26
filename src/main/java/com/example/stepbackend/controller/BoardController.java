package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.Heart.PostHeartRequestDTO;
import com.example.stepbackend.aggregate.dto.Heart.PostHeartResponseDTO;
import com.example.stepbackend.aggregate.dto.board.*;
import com.example.stepbackend.global.common.annotation.CurrentUser;
import com.example.stepbackend.global.security.token.UserPrincipal;
import com.example.stepbackend.service.BoardService;
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
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    /* 문제집 리그 페이지 */
    @GetMapping
    public String getBoardPage(@PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, Model model,
                               @CurrentUser UserPrincipal user){
        Long memberNo = user.getId();

        Page<ReadBoardPageDTO> readBoardPageDTOPage = boardService.findAll(pageable, memberNo);

        model.addAttribute("boards", readBoardPageDTOPage);
        model.addAttribute("memberNo", memberNo);

        return "board/board";
    }

    /* 문제집 공유 */
    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<CreateBoardResponseDTO> create(@RequestBody CreateBoardRequestDTO createBoardRequestDTO,
                                                         @CurrentUser UserPrincipal user){
        Long memberNo = user.getId();

        CreateBoardResponseDTO createBoardResponseDTO = boardService.createBoard(createBoardRequestDTO, memberNo);

        return ResponseEntity.ok(createBoardResponseDTO);
    }

    /* 문제집 리그 수정 */
    @PatchMapping("/update")
    @ResponseBody
    public ResponseEntity<UpdateBoardResponseDTO> update(@RequestBody UpdateBoardRequestDTO updateBoardRequestDTO){
        UpdateBoardResponseDTO updateBoardResponseDTO = boardService.updateBoard(updateBoardRequestDTO);

        return ResponseEntity.ok(updateBoardResponseDTO);
    }

    /* 문제집 리그 내 문제집 삭제 */
    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<Void> delete(@RequestBody DeleteBoardDTO deleteBoardDTO){
        boardService.deleteBoard(deleteBoardDTO.getBoardNo());

        return ResponseEntity.noContent().build();
    }

    /* 좋아요 증감 */
    @PostMapping("/heart")
    @ResponseBody
    public ResponseEntity<PostHeartResponseDTO> post(@RequestBody PostHeartRequestDTO postHeartRequestDTO,
                                                     @CurrentUser UserPrincipal user){
        Long memberNo = user.getId();

        PostHeartResponseDTO postHeartResponseDTO = boardService.postHeart(postHeartRequestDTO, memberNo);

        return ResponseEntity.ok(postHeartResponseDTO);
    }

    /* 문제 풀기 페이지 이동 */
    @GetMapping("/question")
    public String question(@RequestParam("boardNo")Long boardNo , Model model){
        List<ReadBoardQuestionResponseDTO> responseDTOList = boardService.findAllBoardQuestion(boardNo);

        model.addAttribute("questions", responseDTOList);

        return "board/question";
    }

    /* 문제 풀이 기록 저장 */
    @PostMapping("/solve")
    @ResponseBody
    public ResponseEntity<SolveQuestionResponseDTO> solve(@RequestBody SolveQuestionRequestDTO solveQuestionRequestDTO,
                                                          @CurrentUser UserPrincipal user){
        Long memberNo = user.getId();

        SolveQuestionResponseDTO solveQuestionResponseDTO = boardService.saveHistory(solveQuestionRequestDTO, memberNo);

        return ResponseEntity.ok(solveQuestionResponseDTO);
    }

    /* 문제 다 풀고 나만의 문제집으로 이동 */
    @PostMapping("/after/solve")
    @ResponseBody
    public ResponseEntity<CreateSolveAfterResponseDTO> solveAfter(@RequestBody CreateSolveAfterRequestDTO createSolveAfterRequestDTO,
                                                                  @CurrentUser UserPrincipal user){
        Long memberNo = user.getId();

        CreateSolveAfterResponseDTO createSolveAfterResponseDTO = boardService.createSolveAfter(createSolveAfterRequestDTO, memberNo);

        return ResponseEntity.ok(createSolveAfterResponseDTO);
    }
}
