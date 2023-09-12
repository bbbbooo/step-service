package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.dto.scrap.ScrapListDTO;
import com.example.stepbackend.aggregate.entity.Question;
import com.example.stepbackend.aggregate.entity.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScrapRepository extends JpaRepository<Scrap, Long> {
    @Query("SELECT q FROM Question q INNER JOIN Scrap s ON q.questionNo = s.questionNo WHERE s.memberNo = :memberNo")
    Page<Question> findAllByMemberNo(@Param("memberNo")Long memberNo, Pageable pageable);

    @Query("SELECT q FROM Question q INNER JOIN Scrap s ON q.questionNo = s.questionNo WHERE s.memberNo = :memberNo")
    List<Question> findAllByMemberNo(Long memberNo);

    void deleteByMemberNoAndScrapNo(Long memberNo, Long questionNo);

    List<Scrap> findByMemberNoAndQuestionNoIn(Long memberNo, List<Long> questionNos);

    Scrap findByScrapNo(Long scrapNo);


}
