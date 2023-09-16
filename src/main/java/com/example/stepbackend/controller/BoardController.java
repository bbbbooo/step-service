package com.example.stepbackend.controller;

import com.example.stepbackend.aggregate.dto.board.CreateBoardRequestDTO;
import com.example.stepbackend.aggregate.dto.board.CreateBoardResponseDTO;
import com.example.stepbackend.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseEntity<CreateBoardResponseDTO> create(@RequestBody CreateBoardRequestDTO createBoardRequestDTO){
        Long memberNo = 1L;

        CreateBoardResponseDTO createBoardResponseDTO = boardService.createBoard(createBoardRequestDTO, memberNo);

        return ResponseEntity.ok(createBoardResponseDTO);
    }
}
