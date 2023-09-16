package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.board.CreateBoardRequestDTO;
import com.example.stepbackend.aggregate.entity.Board;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.BoardRepository;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final WorkBookRepository workBookRepository;

    /* 공유한 문제집 저장 */
    @Transactional
    public void createBoard(CreateBoardRequestDTO createBoardRequestDTO, Long memberNo) {
        WorkBook workBook = workBookRepository.findByMemberNoAndWorkBookNo(memberNo, createBoardRequestDTO.getWorkBookNo());

        Board board = Board.toEntity(memberNo, workBook, createBoardRequestDTO);

        boardRepository.save(board);
    }
}
