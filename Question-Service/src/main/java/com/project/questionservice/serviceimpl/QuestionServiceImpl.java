package com.project.questionservice.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.project.questionservice.entity.Question;
import com.project.questionservice.entity.QuestionWrapper;
import com.project.questionservice.entity.Response;
import com.project.questionservice.repository.QuestionRepo;
import com.project.questionservice.service.QuestionService;
@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionRepo questionrepo;
	// TO add questions
	@Override
	public ResponseEntity<Question> addQuestion(Question question) {

		return new ResponseEntity<Question>(questionrepo.save(question),HttpStatus.OK);
	}

	//	To get All questions

	@Override
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>( questionrepo.findAll(),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>( new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	//	To get question by Category
	@Override
	public ResponseEntity<List<Question>> getQueByCategory(String category) {
		try {
			return new ResponseEntity<List<Question>>(questionrepo.findByCategory(category),HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>( new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	//	To update the question
	@Override
	public Question updateQuestion(int quenum, Question updatequestion) {
		Optional<Question> optional = questionrepo.findById(quenum);

		if(optional.isPresent()) {
			Question que=optional.get();
			questionrepo.save(updatequestion);
		}
		return updatequestion;

	}
	// To delete the Question
	@Override
	public String deleteQuestion(int quenum) {
		Optional<Question> optional = questionrepo.findById(quenum);
		if(optional.isPresent()) {
			Question question = optional.get();
			questionrepo.delete(question);
		}
		else {
			System.out.println("The question is not Present ");
		}
		return "Deleted Successfully.....";
	}

//	getQuiz
	@Override
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(String categoryName, int numQuestions) {
		List<Integer> questions = questionrepo.findRandomQuestionByCategory(categoryName,numQuestions);
		return new ResponseEntity<List<Integer>>(questions,HttpStatus.OK);

	}

//	getQuestions
	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();

		for(Integer quenum:questionIds) {
			questions.add(questionrepo.findById(quenum).get());
		}
		for(Question question:questions) {
			QuestionWrapper wrapper = new QuestionWrapper();
			wrapper.setQuestion(question.getQuestion());
			wrapper.setQuenum(question.getQuenum());
			wrapper.setOption1(question.getOption1());
			wrapper.setOption2(question.getOption2());
			wrapper.setOption3(question.getOption3());
			wrapper.setOption4(question.getOption4());
			wrappers.add(wrapper);
		}

		return new ResponseEntity<List<QuestionWrapper>>(wrappers,HttpStatus.OK);
	}
//getScore
	@Override
	public ResponseEntity<Integer> getScore(List<Response> responses) {
		int right=0;
		for(Response response:responses) {
			Question question = questionrepo.findById(response.getId()).get();
			if(response.getResponse().equals(question.getCorrectans())) 
				right++;
		}
		return new ResponseEntity<>(right,HttpStatus.OK);
	}



}
