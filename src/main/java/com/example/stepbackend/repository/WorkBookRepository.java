package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.WorkBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkBookRepository extends JpaRepository<WorkBook, Long> {
//    WorkBook findByMemberNoAndQuestionNo(Long memberNo, Long questionNo);
}
