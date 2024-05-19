package com.project.questionservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.questionservice.entity.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Integer>{
	
	List<Question> findByCategory(String category);
	
	@Query(value ="SELECT q.quenum FROM springboot_quizproject.question q where q.category=:category  LIMIT :numQ ", nativeQuery = true)
	List<Integer> findRandomQuestionByCategory(String category, int numQ);
	
}

