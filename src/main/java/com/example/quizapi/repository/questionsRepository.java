package com.example.quizapi.repository;

import com.example.quizapi.entity.questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.w3c.dom.ls.LSException;

import java.util.List;

public interface questionsRepository extends JpaRepository<questions, Integer> {

    List<questions> findByCategory(String category);

    @Query(value="SELECT * FROM questions q WHERE q.category=:category ORDER BY RAND() LIMIT :numOfquestions",nativeQuery = true)
    List<questions> findRandomQuestionsByCategory(String category, Integer numOfquestions);
}
