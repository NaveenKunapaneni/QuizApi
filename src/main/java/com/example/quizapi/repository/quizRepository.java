package com.example.quizapi.repository;

import com.example.quizapi.entity.quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface quizRepository extends JpaRepository<quiz, Integer> {
}
