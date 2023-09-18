package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.board.CreateBoardRequestDTO;
import com.example.stepbackend.aggregate.dto.board.UpdateBoardRequestDTO;
import com.example.stepbackend.aggregate.entity.Board;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.BoardRepository;
import com.example.stepbackend.repository.WorkBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private WorkBookRepository workBookRepository;

    @DisplayName("나만의 문제집 공유 게시글 작성")
    @Test
    void createMyWorkBook(){
        // given
        Long memberNo = 1L;

        CreateBoardRequestDTO createBoardRequestDTO = CreateBoardRequestDTO.builder()
                .description("무무무무")
                .workBookName("미미미미")
                .workBookNo("14")
                .build();

        WorkBook testWorkBook = WorkBook.builder()
                .questionTypes("title")
                .questionNos("1,2,3")
                .build();

        WorkBook workBook = workBookRepository.save(testWorkBook);

        Board board = Board.toEntity(memberNo, workBook, createBoardRequestDTO);

        // when
        Board createdBoard = boardRepository.save(board);

        // then
        Assertions.assertTrue(
                createBoardRequestDTO.getWorkBookName().equals(createdBoard.getBoardName()) &&
                createBoardRequestDTO.getDescription().equals(createdBoard.getDescription()) &&
                workBook.getQuestionTypes().equals(createdBoard.getQuestionTypes()) &&
                        workBook.getQuestionNos().equals(createdBoard.getQuestionNos()) &&
                        memberNo.equals(createdBoard.getMemberNo())
                );
    }

    @DisplayName("제목과 설명 입력받아 수정")
    @Test
    void updateTitleAndDescription(){
        // given
        Long memberNo = 1L;
        String title = "테스트";
        String description = "설명";

        CreateBoardRequestDTO createBoardRequestDTO = CreateBoardRequestDTO.builder()
                .description("무무무무")
                .workBookName("미미미미")
                .workBookNo("14")
                .build();

        WorkBook testWorkBook = WorkBook.builder()
                .questionTypes("title")
                .questionNos("1,2,3")
                .build();

        WorkBook workBook = workBookRepository.save(testWorkBook);

        Board board = Board.toEntity(memberNo, workBook, createBoardRequestDTO);
        boardRepository.save(board);

        UpdateBoardRequestDTO updateBoardRequestDTO = UpdateBoardRequestDTO.builder()
                .title(title)
                .description(description)
                .boardNo(board.getBoardNo())
                .build();

        // when
        Board updateBoard = boardRepository.findByBoardNo(updateBoardRequestDTO.getBoardNo());
        updateBoard.update(updateBoardRequestDTO.getTitle(), updateBoardRequestDTO.getDescription());

        // then
        Assertions.assertTrue(updateBoard.getBoardName().equals(title) &&
                updateBoard.getDescription().equals(description));
    }
}