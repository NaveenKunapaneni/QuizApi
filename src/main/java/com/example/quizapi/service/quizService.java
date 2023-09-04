package com.example.quizapi.service;

import com.example.quizapi.entity.questionWrapper;
import com.example.quizapi.entity.questions;
import com.example.quizapi.entity.quiz;
import com.example.quizapi.entity.quizResponse;
import com.example.quizapi.repository.questionsRepository;
import com.example.quizapi.repository.quizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class quizService {

    @Autowired
    quizRepository QuizRepo;
    @Autowired
    questionsRepository QuesRepo;
    quiz Quiz = new quiz();

    public ResponseEntity<String> createQuiz(Map<String, String> quizData) {
        try {
            Integer numOfQuestions = Integer.valueOf(quizData.get("questions"));
            String category = quizData.get("category");
            String quizTitle = quizData.get("quizTitle");
            if(numOfQuestions.equals(null) || quizTitle.isEmpty() || category.isEmpty() || category.isBlank() || quizTitle.isBlank()){
                return new ResponseEntity<>("Invalid Values", HttpStatus.NOT_ACCEPTABLE);
            }
            if(QuesRepo.findByCategory(category).isEmpty()){
                return new ResponseEntity<>("No questions available for "+category+" category", HttpStatus.NOT_FOUND);
            }
            List<questions> questions = QuesRepo.findRandomQuestionsByCategory(category, numOfQuestions);
            System.out.println(questions);
            System.out.println(quizData);
            Quiz.setQuizName(quizData.get("quizTitle"));
            Quiz.setQuestions(questions);
            System.out.println(Quiz);
            QuizRepo.save(Quiz);
            return new ResponseEntity<>("Quiz created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Unable to create quiz..", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<questionWrapper>> getQuiz(Integer id) {
        try{
            Optional<quiz> Quiz = QuizRepo.findById(id);
            if( Quiz.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<questions> completeQuestions = Quiz.get().getQuestions();
            List<questionWrapper> quizQuestions = new ArrayList<>();
            for(questions q: completeQuestions){
                questionWrapper qwrap = new questionWrapper(q.getId(), q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
                quizQuestions.add(qwrap);
            }
            return new ResponseEntity<>(quizQuestions, HttpStatus.OK);
        }catch (Exception e){
            //e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Integer> calucalteScore(Integer id, List<quizResponse> reqBody) {
        try {
            Optional<quiz> Quiz = QuizRepo.findById(id);
            if(Quiz.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<questions> ques = Quiz.get().getQuestions();
            System.out.println(ques);
            int right = 0;
            int i = 0;
            for(quizResponse resp : reqBody){
                System.out.println(resp);
                if(resp.getResponse().equals(ques.get(i).getRightAnswer())){
                    System.out.println(ques.get(i).getRightAnswer());
                    right++;
                }
                i++;
            }
            return new ResponseEntity<>(right,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
