package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.Heart.PostHeartRequestDTO;
import com.example.stepbackend.aggregate.dto.Heart.PostHeartResponseDTO;
import com.example.stepbackend.aggregate.dto.board.*;
import com.example.stepbackend.aggregate.entity.Board;
import com.example.stepbackend.aggregate.entity.Heart;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.BoardRepository;
import com.example.stepbackend.repository.HeartRepository;
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

    private final HeartRepository heartRepository;

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

    /* 공유한 문제집 수정 */
    @Transactional
    public UpdateBoardResponseDTO updateBoard(UpdateBoardRequestDTO updateBoardRequestDTO) {
        Board board = boardRepository.findByBoardNo(updateBoardRequestDTO.getBoardNo());

        board.update(updateBoardRequestDTO.getTitle(), updateBoardRequestDTO.getDescription());

        UpdateBoardResponseDTO updateBoardResponseDTO = UpdateBoardResponseDTO.toEntity(board);

        return updateBoardResponseDTO;
    }

    @Transactional
    public Long deleteBoard(Long boardNo) {
        boardRepository.deleteById(boardNo);

        return boardNo;
    }

    @Transactional
    public PostHeartResponseDTO postHeart(PostHeartRequestDTO postHeartRequestDTO, Long memberNo) {
        Heart heart = heartRepository.findByBoardNoAndMemberNo(postHeartRequestDTO.getBoardNo(), memberNo);
        Board board = boardRepository.findByBoardNo(postHeartRequestDTO.getBoardNo());

        if (heart == null){
            Heart saveHeart = Heart.toEntity(postHeartRequestDTO, memberNo);
            heartRepository.save(saveHeart);

            board.increaseHeartCount();
            PostHeartResponseDTO postHeartResponseDTO = PostHeartResponseDTO.fromEntity(saveHeart, board);

            return postHeartResponseDTO;
        }

        board.decreaseHeartCount();
        PostHeartResponseDTO postHeartResponseDTO = PostHeartResponseDTO.fromEntity(heart, board);

        return postHeartResponseDTO;
    }
}
