package com.example.quizapi.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class quizResponse {

    private Integer id;
    private String response;
}
