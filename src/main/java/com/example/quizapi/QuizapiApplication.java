package com.example.quizapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuizapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizapiApplication.class, args);
		System.out.println("The server is running....");
	}

}
