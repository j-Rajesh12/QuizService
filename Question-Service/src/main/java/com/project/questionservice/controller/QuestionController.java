package com.project.questionservice.controller;

import java.util.List;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.questionservice.entity.Question;
import com.project.questionservice.entity.QuestionWrapper;
import com.project.questionservice.entity.Response;
import com.project.questionservice.service.QuestionService;

@RestController
@RequestMapping(value = "/questions")
public class QuestionController {
	@Autowired
	private QuestionService questionserv;
	
//	@Autowired
//	Environment environment;
	
	@PostMapping
	public ResponseEntity<Question> quiz(@RequestBody Question question) {
		return questionserv.addQuestion(question);

	}
	@GetMapping("/{category}")
	public ResponseEntity<List<Question>> getQueByCategory(@PathVariable String category) {
		return questionserv.getQueByCategory(category);

	}
	
//	@GetMapping
//	public ResponseEntity<List<Question>> getAllQuestions() {
//		return questionserv.getAllQuestions();
//
//	}
//	
	@PutMapping
	public Question updateQuestion(@PathVariable int quenum,@RequestBody Question question) {
		return questionserv.updateQuestion(quenum,question);
		
	}
	@DeleteMapping
	public String deleteQuestion(@RequestParam int quenum) {
		return questionserv.deleteQuestion(quenum);
	}
	
//	generate
//	getQuestion(questionid)
//	getScore
	
	@GetMapping("/generate")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@RequestParam String categoryName,@RequestParam int numQuestions){
		return questionserv.getQuestionsForQuiz(categoryName,numQuestions);
	}
	
	@PostMapping("/getquestions")
		public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		return questionserv.getQuestionsFromId(questionIds);
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return questionserv.getScore(responses);
	}
	
}
