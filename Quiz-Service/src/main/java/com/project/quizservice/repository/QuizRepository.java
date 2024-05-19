package com.project.quizservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.quizservice.entity.Quiz;
@Repository
public interface QuizRepository extends JpaRepository<Quiz,Integer>{

	

}
