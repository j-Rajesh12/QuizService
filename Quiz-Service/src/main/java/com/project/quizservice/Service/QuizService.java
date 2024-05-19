
package com.project.quizservice.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.quizservice.entity.QuestionWrapper;
import com.project.quizservice.entity.Response;
@Service
public interface QuizService {

	ResponseEntity<List<Integer>> createQuiz(String categoryName, int numQuestions, String title);

	ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

	ResponseEntity<Integer> calculateResult(int id, List<Response> responses);

}
