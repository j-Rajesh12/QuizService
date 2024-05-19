package com.project.quizservice.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.quizservice.Service.QuizService;
import com.project.quizservice.entity.Question;
import com.project.quizservice.entity.QuestionWrapper;
import com.project.quizservice.entity.Quiz;
import com.project.quizservice.entity.Response;
import com.project.quizservice.feign.QuizInterface;
import com.project.quizservice.repository.QuizRepository;import jakarta.persistence.criteria.CriteriaBuilder.In;
@Service
public class QuizServiceImpl implements QuizService {
//Quiz
	@Autowired
	QuizRepository quizRepo;

	@Autowired
	QuizInterface quizInterface;
	
	@Override
	public ResponseEntity<List<Integer>> createQuiz(String category, int numQ, String title) {
		
	List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
	Quiz quiz = new Quiz();
	quiz.setTitle(title);
	quiz.setQuestionIds(questions);
	
	quizRepo.save(quiz);
	
	
		return new ResponseEntity<>(questions,HttpStatus.CREATED);
		
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Quiz quiz = quizRepo.findById(id).get();
		List<Integer> questionIds=quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
		return questions;
	}

	@Override
	public ResponseEntity<Integer> calculateResult(int id, List<Response> responses) {
		ResponseEntity<Integer> score =quizInterface.getScore(responses);
		return score;
	}

}
