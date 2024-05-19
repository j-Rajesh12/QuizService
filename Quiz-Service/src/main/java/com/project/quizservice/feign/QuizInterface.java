package com.project.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.quizservice.entity.QuestionWrapper;
import com.project.quizservice.entity.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	@GetMapping("questions/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam int numQuestions);
	
	@PostMapping("questions/getquestions")
		public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds);
	
	@PostMapping("questions/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
