package com.example.quizapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class questions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String questionTitle;
    private String difficultyLevel;
    private String category;
    private String rightAnswer;
    private String option1;
    private String option2;
    private String option3;
    private String option4;

}
