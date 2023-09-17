package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.board.CreateBoardRequestDTO;
import com.example.stepbackend.aggregate.dto.board.CreateBoardResponseDTO;
import com.example.stepbackend.aggregate.dto.board.ReadBoardPageDTO;
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

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String getBoardPage(@PageableDefault(sort = "boardNo", direction = Sort.Direction.DESC)Pageable pageable, Model model){
        Page<ReadBoardPageDTO> readBoardPageDTOPage = boardService.findAll(pageable);

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
}
