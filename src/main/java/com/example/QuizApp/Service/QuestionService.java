package com.example.QuizApp.Service;

import com.example.QuizApp.DAO.QuestionDAO;
import com.example.QuizApp.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDAO.findAll(), HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public List<Question> getQuestionByCategory(String category) {
        return questionDAO.findByCategoryIgnoreCase(category);
    }

    public String addQuestion(Question question) {
        questionDAO.save(question);
        return "success";
    }
}
