package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.board.CreateBoardRequestDTO;
import com.example.stepbackend.aggregate.dto.board.CreateBoardResponseDTO;
import com.example.stepbackend.aggregate.dto.board.ReadBoardPageDTO;
import com.example.stepbackend.aggregate.entity.Board;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.BoardRepository;
import com.example.stepbackend.repository.WorkBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final WorkBookRepository workBookRepository;

    /* 공유한 문제집 저장 */
    @Transactional
    public CreateBoardResponseDTO createBoard(CreateBoardRequestDTO createBoardRequestDTO, Long memberNo) {
        WorkBook workBook = workBookRepository.findByMemberNoAndWorkBookNo(memberNo, Long.valueOf(createBoardRequestDTO.getWorkBookNo()));
        Board board = Board.toEntity(memberNo, workBook, createBoardRequestDTO);

        boardRepository.save(board);

        CreateBoardResponseDTO createBoardResponseDTO = CreateBoardResponseDTO.fromEntity(board);

        return createBoardResponseDTO;
    }

    /* 공유한 문제집 전체 출력 */
    @Transactional(readOnly = true)
    public Page<ReadBoardPageDTO> findAll(Pageable pageable) {
        Page<Board> boards = boardRepository.findAll(pageable);

        Page<ReadBoardPageDTO> readBoardPageDTOPage = ReadBoardPageDTO.toEntity(boards);

        return readBoardPageDTOPage;
    }
}
