package com.guilherme.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.challenge.entities.Interview;

public interface InterviewRepository  extends JpaRepository<Interview, Long> {
	
	

}
