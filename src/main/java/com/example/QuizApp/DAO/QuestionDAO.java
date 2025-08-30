package com.example.QuizApp.DAO;

import com.example.QuizApp.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    List<Question>findByCategoryIgnoreCase(String category);
}
