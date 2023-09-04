package com.example.quizapi.controller;


import com.example.quizapi.entity.questions;
import com.example.quizapi.service.questionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/questions")
public class questionsController {

    @Autowired
    questionsService QuestionService;
    @GetMapping("all")
    public ResponseEntity<List<questions>> getAllQuestions() {
        return QuestionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<questions>> getQuestionsByCategory(@PathVariable String category){
        return  QuestionService.getQuestionsByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<String> addQuestions(@RequestBody questions ques){
        return QuestionService.addQuestions(ques);
    }

    @PostMapping("delete")
    public ResponseEntity<String> deleteQuestionById(@RequestBody Map<String, Integer> requestBody){
        System.out.println(requestBody.get("id"));
        Integer id = requestBody.get("id");
        return QuestionService.deleteQuestionById(id);
    }

    @PostMapping("update/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id, @RequestBody Map<String, String> requestBody){
        System.out.println(requestBody);
        System.out.println(id);
        return QuestionService.updateQuestionsById(id, requestBody);
    }

}
