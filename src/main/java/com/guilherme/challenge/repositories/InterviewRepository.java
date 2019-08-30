package com.guilherme.challenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.challenge.entities.Interview;
import com.guilherme.challenge.entities.Professional;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

	public List<Interview> findInterviewByIdProfessional(Professional idProfessional);

}
