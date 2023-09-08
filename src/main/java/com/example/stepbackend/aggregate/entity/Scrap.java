package com.example.stepbackend.aggregate.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scrap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("스크랩 번호")
    @Column(name = "scrap_no")
    private Long scrapNo;


    @Comment("회원 번호")
    @Column(name = "member_no")
    private Long memberNo;

    @Comment("문제 번호")
    @Column(name = "problem_no")
    private Long problemNo;
}
