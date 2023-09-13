package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.WorkBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkBookRepository extends JpaRepository<WorkBook, Long> {
    WorkBook findByMemberNoAndWorkBookNo(Long memberNo, Long workBookNo);

    Page<WorkBook> findByMemberNo(Long memberNo, Pageable pageable);
}
