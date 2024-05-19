package com.project.quizservice.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.quizservice.Service.QuizService;
import com.project.quizservice.entity.QuestionWrapper;
import com.project.quizservice.entity.QuizDto;
import com.project.quizservice.entity.Response;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	QuizService quizSer;
	
	@PostMapping("/create")
	public ResponseEntity<List<Integer>> createQuiz(@RequestBody QuizDto quizDto){
		return quizSer.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
		return quizSer.getQuizQuestions(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id,@RequestBody List<Response> responses){
		return quizSer.calculateResult(id,responses);
	}
}
