package com.example.quizapi.service;

import com.example.quizapi.entity.questions;
import com.example.quizapi.repository.questionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class questionsService {

    @Autowired
    questionsRepository QuestionRepo;
    public ResponseEntity<List<questions>> getAllQuestions(){
        try {
            if (QuestionRepo.findAll() == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(QuestionRepo.findAll(), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<questions>> getQuestionsByCategory(String category){
        try{
            System.out.println("This is category controller");
            if(QuestionRepo.findByCategory(category) == null ){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(QuestionRepo.findByCategory(category), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> addQuestions(questions ques){
        try{
            QuestionRepo.save(ques);
            return new ResponseEntity<>("Successfully added question", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to add question", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> deleteQuestionById(Integer id){
        try{
            if(QuestionRepo.findById(id).isPresent()){
                QuestionRepo.deleteById(id);
                return new ResponseEntity<>("Successfully deleted question..", HttpStatus.OK);
            }
            return new ResponseEntity<>("Failed to delete question..", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Bad Request...", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateQuestionsById(Integer id, Map<String, String> updatedQuestion){
        try{
            if(QuestionRepo.findById(id).isPresent()){
                questions existingQuestion = QuestionRepo.findById(id).get();
//                System.out.println(existingQuestion);
                existingQuestion.setQuestionTitle(updatedQuestion.get("questionTitle"));
//                System.out.println(updatedQuestion.get("questionTitle"));
                existingQuestion.setCategory(updatedQuestion.get("category"));
//                System.out.println(updatedQuestion.get("category"));
                existingQuestion.setOption1("option1");
                existingQuestion.setOption2(updatedQuestion.get("option2"));
                existingQuestion.setOption3(updatedQuestion.get("option3"));
                existingQuestion.setOption4(updatedQuestion.get("option4"));
                existingQuestion.setRightAnswer(updatedQuestion.get("rightAnswer"));
                existingQuestion.setDifficultyLevel(updatedQuestion.get("difficultyLevel"));
                QuestionRepo.save(existingQuestion);
                return new ResponseEntity<>("Successfully updated question..", HttpStatus.OK);
            }
            return new ResponseEntity<>("Failed to update question..Question Not found", HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>("Bad Request...", HttpStatus.BAD_REQUEST);
        }
    }

}
