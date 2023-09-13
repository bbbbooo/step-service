package com.example.stepbackend.repository;

import com.example.stepbackend.aggregate.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
