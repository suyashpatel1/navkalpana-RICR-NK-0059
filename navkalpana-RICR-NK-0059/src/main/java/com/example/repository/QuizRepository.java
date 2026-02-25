package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {}