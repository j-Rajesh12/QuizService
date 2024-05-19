package com.project.questionservice.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.project.questionservice.entity.Question;
import com.project.questionservice.entity.QuestionWrapper;
import com.project.questionservice.entity.Response;

public interface QuestionService {


	public ResponseEntity<Question> addQuestion(Question question);
	public ResponseEntity<List<Question>> getAllQuestions();
	public ResponseEntity<List<Question>> getQueByCategory(String category);
	public Question updateQuestion(int quenum, Question question);
	public String deleteQuestion(int quenum);
	
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numQuestions);
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds);
	public ResponseEntity<Integer> getScore(List<Response> responses);
}
