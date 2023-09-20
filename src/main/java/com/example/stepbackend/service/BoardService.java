package com.example.stepbackend.service;

import com.example.stepbackend.aggregate.dto.Heart.PostHeartRequestDTO;
import com.example.stepbackend.aggregate.dto.Heart.PostHeartResponseDTO;
import com.example.stepbackend.aggregate.dto.board.*;
import com.example.stepbackend.aggregate.entity.*;
import com.example.stepbackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final WorkBookRepository workBookRepository;

    private final HeartRepository heartRepository;

    private final QuestionRepository questionRepository;

    private final QuestionByMemberRepository questionByMemberRepository;

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
    public Page<ReadBoardPageDTO> findAll(Pageable pageable, Long memberNo) {
        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Heart> hearts = heartRepository.findAllByMemberNo(memberNo, pageable);

        Page<ReadBoardPageDTO> readBoardPageDTOPage = ReadBoardPageDTO.toEntity(boards, hearts);

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

    /* 게시판 삭제*/
    @Transactional
    public Long deleteBoard(Long boardNo) {
        boardRepository.deleteById(boardNo);

        return boardNo;
    }

    /* 좋아요 증감 */
    @Transactional
    public PostHeartResponseDTO postHeart(PostHeartRequestDTO postHeartRequestDTO, Long memberNo) {
        Heart heart = heartRepository.findByBoardNoAndMemberNo(postHeartRequestDTO.getBoardNo(), memberNo);
        Board board = boardRepository.findByBoardNo(postHeartRequestDTO.getBoardNo());

        if (heart == null){
            Heart saveHeart = Heart.toEntity(postHeartRequestDTO, memberNo);
            heartRepository.save(saveHeart);

            board.increaseHeartCount();
            saveHeart.isClickedTrue();
            PostHeartResponseDTO postHeartResponseDTO = PostHeartResponseDTO.fromEntity(saveHeart, board);

            return postHeartResponseDTO;
        }

        if (heart.getIsClicked() == true){
            board.decreaseHeartCount();
            heart.isClickedFalse();
            PostHeartResponseDTO postHeartResponseDTO = PostHeartResponseDTO.fromEntity(heart, board);

            return postHeartResponseDTO;
        }

        board.increaseHeartCount();
        heart.isClickedTrue();
        PostHeartResponseDTO postHeartResponseDTO = PostHeartResponseDTO.fromEntity(heart, board);

        return postHeartResponseDTO;

    }

    /* 문제집 내 문제 불러오기 */
    @Transactional(readOnly = true)
    public List<ReadBoardQuestionResponseDTO> findAllBoardQuestion(Long boardNo) {
        String questionNosString = boardRepository.findQuestionNos(boardNo);
        List<Long> questionNos = Arrays.stream(questionNosString.split(", "))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<Question> questions = questionRepository.findByQuestionNoIn(questionNos);

        List<ReadBoardQuestionResponseDTO> responseDTOList = ReadBoardQuestionResponseDTO.fromEntity(questions);

        return responseDTOList;
    }

    /* 문제 풀때마다 기록 */
    @Transactional
    public SolveQuestionResponseDTO saveHistory(SolveQuestionRequestDTO solveQuestionRequestDTO, Long memberNo) {
        QuestionByMember questionByMember = questionByMemberRepository.findByMemberNoAndQuestionNo(memberNo,
                solveQuestionRequestDTO.getQuestionNo());

        if (questionByMember == null){
            QuestionByMember history = QuestionByMember.toEntity(solveQuestionRequestDTO, memberNo);
            questionByMemberRepository.save(history);

            SolveQuestionResponseDTO solveQuestionResponseDTO = SolveQuestionResponseDTO.fromEntity(history);

            return solveQuestionResponseDTO;
        }

        SolveQuestionResponseDTO solveQuestionResponseDTO = SolveQuestionResponseDTO.fromEntity(questionByMember);
        return solveQuestionResponseDTO;
    }
}
