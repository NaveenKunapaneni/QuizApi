package com.example.quizapi.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.id.factory.internal.AutoGenerationTypeStrategy;

import java.security.PrivateKey;
import java.util.List;

@Entity
@Data
public class quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String quizName;
    @ManyToMany
    private List<questions> questions;
}
