package com.example.quizapi.controller;

import com.example.quizapi.entity.questionWrapper;
import com.example.quizapi.entity.quiz;
import com.example.quizapi.entity.quizResponse;
import com.example.quizapi.service.quizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/quiz")
public class quizController {

    @Autowired
    quizService QuizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody Map<String, String> requestBody){
        return QuizService.createQuiz(requestBody);
    }
    @GetMapping("get/{id}")
    public ResponseEntity<List<questionWrapper>> getQuiz(@PathVariable Integer id) {
        return QuizService.getQuiz(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<quizResponse> reqBody){
        return QuizService.calucalteScore(id, reqBody);
    }
}
