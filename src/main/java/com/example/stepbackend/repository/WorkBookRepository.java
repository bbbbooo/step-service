package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.WorkBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkBookRepository extends JpaRepository<WorkBook, Long> {
    WorkBook findByMemberNoAndWorkBookNo(Long memberNo, Long workBookNo);

    Page<WorkBook> findByMemberNo(Long memberNo, Pageable pageable);

    @Query("SELECT DISTINCT q.questionViewType FROM Question q WHERE q.questionNo IN :questionNos")
    List<String> findDistinctQuestionTypes(@Param("questionNos") List<Long> questionNos);
}

