package com.example.QuizApp.DAO;

import com.example.QuizApp.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizDAO extends JpaRepository<Quiz, Integer> {

}
