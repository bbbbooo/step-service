package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.QuestionToMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface QuestionToMemberRepository extends JpaRepository<QuestionToMember, Long> {

    @Query(value = "SELECT A.question_no FROM question_to_member A WHERE A.member_no = :memberNo", nativeQuery = true)
    List<Long> findQuestionToMemberByQuestionToMemberNo(@Param("memberNo") Long memberNo);
}
