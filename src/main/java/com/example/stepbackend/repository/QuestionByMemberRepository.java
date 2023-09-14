package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.QuestionByMember;
import com.example.stepbackend.aggregate.entity.WorkBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionByMemberRepository extends JpaRepository<QuestionByMember, Long> {

    @Query(value = "SELECT A.question_no FROM question_by_member A WHERE A.member_no = :memberNo", nativeQuery = true)
    List<Long> findQuestionByMemberByQuestionByMemberNo(@Param("memberNo") Long memberNo);
}
