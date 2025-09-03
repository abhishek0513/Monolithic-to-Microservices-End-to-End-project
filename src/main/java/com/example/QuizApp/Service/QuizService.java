package com.example.QuizApp.Service;

import com.example.QuizApp.DAO.QuestionDAO;
import com.example.QuizApp.DAO.QuizDAO;
import com.example.QuizApp.models.Question;
import com.example.QuizApp.models.QuestionWrapper;
import com.example.QuizApp.models.Quiz;
import com.example.QuizApp.models.Responses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizService {
    @Autowired
    QuizDAO quizDAO;
    @Autowired
    QuestionDAO questionDAO;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<Question> questions = questionDAO.findRandQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizDAO.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question>questionfromDb= quiz.get().getQuestions();
        List<QuestionWrapper>questionForUsers = new ArrayList<>();

        for(Question q : questionfromDb){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),
                    q.getQuestionTitle(), q.getOption1(), q.getOption2()
                    ,q.getOption3(), q.getOption4()
            );
            questionForUsers.add(qw);

        }
        return new ResponseEntity<>(questionForUsers, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Responses> responses) {
        Optional<Quiz> quiz = quizDAO.findById(id);
        List<Question> questions = quiz.get().getQuestions();

        // Prepare a lookup map: questionId -> Question
        Map<Integer, Question> questionMap = questions.stream()
                .collect(Collectors.toMap(Question::getId, q -> q));

        int correct = 0;
        for (Responses r : responses) {
            Question q = questionMap.get(r.getId());
            if (q != null && r.getSelected() != null
                    && q.getRightAnswer() != null
                    && r.getSelected().trim().equalsIgnoreCase(q.getRightAnswer().trim())) {
                correct++;
            }
        }

//        int correct = 0;
//        int i = 0;
//        for(Responses r : responses){
//            if (r.getSelected() != null && questions.get(i).getRightAnswer() != null &&
//                    r.getSelected().trim().equalsIgnoreCase(questions.get(i).getRightAnswer().trim()))
//            {
//                correct++;
//            }
//
//
//            i++;
//        }

        return new ResponseEntity<>(correct, HttpStatus.OK);

    }
}
