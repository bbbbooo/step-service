package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.Heart.PostHeartRequestDTO;
import com.example.stepbackend.aggregate.dto.Heart.PostHeartResponseDTO;
import com.example.stepbackend.aggregate.dto.board.*;
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

    @GetMapping
    public String getBoardPage(@PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, Model model){
        Long memberNo = 1L;

        Page<ReadBoardPageDTO> readBoardPageDTOPage = boardService.findAll(pageable, memberNo);

        model.addAttribute("boards", readBoardPageDTOPage);

        return "board/board";
    }

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<CreateBoardResponseDTO> create(@RequestBody CreateBoardRequestDTO createBoardRequestDTO){
        Long memberNo = 1L;

        CreateBoardResponseDTO createBoardResponseDTO = boardService.createBoard(createBoardRequestDTO, memberNo);

        return ResponseEntity.ok(createBoardResponseDTO);
    }

    @PatchMapping("/update")
    @ResponseBody
    public ResponseEntity<UpdateBoardResponseDTO> update(@RequestBody UpdateBoardRequestDTO updateBoardRequestDTO){
        UpdateBoardResponseDTO updateBoardResponseDTO = boardService.updateBoard(updateBoardRequestDTO);

        return ResponseEntity.ok(updateBoardResponseDTO);
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public ResponseEntity<Void> delete(@RequestBody DeleteBoardDTO deleteBoardDTO){
        boardService.deleteBoard(deleteBoardDTO.getBoardNo());

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/heart")
    @ResponseBody
    public ResponseEntity<PostHeartResponseDTO> post(@RequestBody PostHeartRequestDTO postHeartRequestDTO){
        Long memberNo = 1L;

        PostHeartResponseDTO postHeartResponseDTO = boardService.postHeart(postHeartRequestDTO, memberNo);

        return ResponseEntity.ok(postHeartResponseDTO);
    }

    @GetMapping("/question")
    public String question(@RequestParam("boardNo")Long boardNo , Model model){
        List<ReadBoardQuestionResponseDTO> responseDTOList = boardService.findAllBoardQuestion(boardNo);

        model.addAttribute("questions", responseDTOList);

        return "board/question";
    }

    @PostMapping("/solve")
    @ResponseBody
    public ResponseEntity<SolveQuestionResponseDTO> solve(@RequestBody SolveQuestionRequestDTO solveQuestionRequestDTO){
        Long memberNo = 1L;

        SolveQuestionResponseDTO solveQuestionResponseDTO = boardService.saveHistory(solveQuestionRequestDTO, memberNo);

        return ResponseEntity.ok(solveQuestionResponseDTO);
    }

    @PostMapping("/after/solve")
    @ResponseBody
    public ResponseEntity<CreateSolveAfterResponseDTO> solveAfter(@RequestBody CreateSolveAfterRequestDTO createSolveAfterRequestDTO){
        CreateSolveAfterResponseDTO createSolveAfterResponseDTO = boardService.createSolveAfter(createSolveAfterRequestDTO);

        return ResponseEntity.ok(createSolveAfterResponseDTO);
    }
}
