package com.example.QuizApp.DAO;

import com.example.QuizApp.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<Question, Integer> {
    List<Question>findByCategoryIgnoreCase(String category);

    @Query(value = "SELECT * from question q Where q.category = ?1 ORDER BY RANDOM() LIMIT ?2", nativeQuery = true)
    List<Question> findRandQuestionByCategory(String category, int numQ);

}
