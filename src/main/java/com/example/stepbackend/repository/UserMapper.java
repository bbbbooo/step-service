package com.example.stepbackend.repository;
import com.example.stepbackend.aggregate.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

    User findByUID(String uid);

    User findById(long userId);

    User findByEmail(String email);
}
