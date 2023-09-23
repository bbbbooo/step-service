package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.Heart.PostHeartRequestDTO;
import com.example.stepbackend.aggregate.dto.Heart.PostHeartResponseDTO;
import com.example.stepbackend.aggregate.dto.board.*;
import com.example.stepbackend.aggregate.dto.question.ResQuestionDTO;
import com.example.stepbackend.aggregate.entity.Board;
import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.aggregate.entity.WorkBook;
import com.example.stepbackend.repository.BoardRepository;
import com.example.stepbackend.repository.QuestionRepository;
import com.example.stepbackend.repository.WorkBookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BoardServiceTest {
    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private WorkBookRepository workBookRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @DisplayName("나만의 문제집 공유 게시글 작성")
    @Test
    void createMyWorkBook(){
        // given
        Long memberNo = 1L;

        CreateBoardRequestDTO createBoardRequestDTO = CreateBoardRequestDTO.builder()
                .description("무무무무")
                .workBookName("미미미미")
                .workBookNo("14")
                .isShared(true)
                .build();

        WorkBook testWorkBook = WorkBook.builder()
                .questionTypes("title")
                .questionNos("1,2,3")
                .isShared(false)
                .build();

        WorkBook workBook = workBookRepository.save(testWorkBook);

        workBook.updateIsShared(createBoardRequestDTO);

        Board board = Board.toEntity(memberNo, workBook, createBoardRequestDTO);

        // when
        Board createdBoard = boardRepository.save(board);

        // then
        Assertions.assertTrue(
                createBoardRequestDTO.getWorkBookName().equals(createdBoard.getBoardName()) &&
                createBoardRequestDTO.getDescription().equals(createdBoard.getDescription()) &&
                workBook.getQuestionTypes().equals(createdBoard.getQuestionTypes()) &&
                        workBook.getQuestionNos().equals(createdBoard.getQuestionNos()) &&
                        memberNo.equals(createdBoard.getMemberNo()) &&
                        createBoardRequestDTO.getIsShared().equals(workBook.getIsShared())
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

    @DisplayName("게시글 삭제")
    @Test
    void delete(){
        // given
        Long memberNo = 1L;

        CreateBoardRequestDTO createBoardRequestDTO = CreateBoardRequestDTO.builder()
                .build();

        WorkBook testWorkBook = WorkBook.builder()
                .build();

        WorkBook workBook = workBookRepository.save(testWorkBook);

        Board board = Board.toEntity(memberNo, workBook, createBoardRequestDTO);
        boardRepository.save(board);

        // when
        Long boardNo = boardService.deleteBoard(board.getBoardNo());

        // then
        Assertions.assertTrue(board.getBoardNo().equals(boardNo));

    }

    @DisplayName("게시글 좋아요 증감")
    @Test
    void postHeart(){
        // given
        Long memberNo = 1L;

        CreateBoardRequestDTO createBoardRequestDTO = CreateBoardRequestDTO.builder().build();
        WorkBook testWorkBook = WorkBook.builder().build();
        WorkBook workBook = workBookRepository.save(testWorkBook);
        Board board = Board.toEntity(memberNo, workBook, createBoardRequestDTO);
        boardRepository.save(board);
        PostHeartRequestDTO postHeartRequestDTO = PostHeartRequestDTO.builder().boardNo(board.getBoardNo()).build();

        // when
        PostHeartResponseDTO increaseHeart = boardService.postHeart(postHeartRequestDTO, memberNo);
        PostHeartResponseDTO decreaseHeart = boardService.postHeart(postHeartRequestDTO, memberNo);

        // then
        assertThat(increaseHeart.getHeartCount()).isEqualTo(1);
        assertThat(decreaseHeart.getHeartCount()).isEqualTo(0);
    }

    @DisplayName("문제 풀기 페이지 불러오기")
    @Test
    void getQuestionPage(){
        // given
        Long memberNo = 1L;

        Question question = new Question();
        Question question2 = new Question();

        questionRepository.save(question);
        questionRepository.save(question2);

        CreateBoardRequestDTO createBoardRequestDTO = CreateBoardRequestDTO.builder()
                .description("무무무무")
                .workBookName("미미미미")
                .workBookNo("14")
                .build();

        WorkBook testWorkBook = WorkBook.builder()
                .questionTypes("title")
                .questionNos(question.getQuestionNo() + ", " + question2.getQuestionNo())
                .build();

        WorkBook workBook = workBookRepository.save(testWorkBook);

        Board board = Board.toEntity(memberNo, workBook, createBoardRequestDTO);
        Board createdBoard = boardRepository.save(board);

        // when
        List<ReadBoardQuestionResponseDTO> responseDTOList = boardService.findAllBoardQuestion(createdBoard.getBoardNo());

        // then
        Assertions.assertFalse(responseDTOList.isEmpty(), "문제 목록이 비어있지 않아야 합니다.");
    }

    @DisplayName("문제 풀었을 때 기록 저장")
    @Test
    void saveSolveHistory(){
        // given
        Long memberNo = 1L;
        SolveQuestionRequestDTO solveQuestionRequestDTO = SolveQuestionRequestDTO.builder()
                .questionNo(1L)
                .correctedMarkingStatus(true)
                .markedNo(4)
                .build();

        // when
        SolveQuestionResponseDTO solveQuestionResponseDTO = boardService.saveHistory(solveQuestionRequestDTO, memberNo);

        // then
        Assertions.assertNotNull(solveQuestionResponseDTO);
    }

    @DisplayName("문제 풀고나서 나의 문제집으로 이동")
    @Test
    void solveAfterIntoMyWorkbook(){
        // given
        Board board = Board.builder().build();
        Board createBoard = boardRepository.save(board);

        CreateSolveAfterRequestDTO createSolveAfterRequestDTO = CreateSolveAfterRequestDTO.builder()
                .boardNo(createBoard.getBoardNo())
                .build();

        // when
        CreateSolveAfterResponseDTO createSolveAfterResponseDTO = boardService.createSolveAfter(createSolveAfterRequestDTO);

        // then
        Assertions.assertNotNull(createSolveAfterResponseDTO);
    }
}