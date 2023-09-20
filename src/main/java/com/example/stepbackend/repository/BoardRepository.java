package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByBoardNo(Long boardNo);

    @Query("SELECT b.questionNos FROM Board b WHERE b.boardNo =:boardNo")
    String findQuestionNos(Long boardNo);
}
