package com.guilherme.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.challenge.entities.Interview;
import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.repositories.InterviewRepository;

@Service
public class InterviewService {

	@Autowired
	private InterviewRepository interviewRepository;

	public List<Interview> findAll() {

		return interviewRepository.findAll();
	}

	public Interview findInterviewByID(Long id) {
		Optional<Interview> interviewOpt = interviewRepository.findById(id);

		if (interviewOpt.isPresent()) {
			Interview p = interviewOpt.get();

			return p;

		}
		return null;
	}

	public List<Interview> findInterviewByProfessional(Professional professional) {

		return interviewRepository.findInterviewByIdProfessional(professional);
	}

	public Interview saveInterview(Interview interview) {

		return interviewRepository.save(interview);
	}

	public void deleteInterviewById(Long id) {

		interviewRepository.deleteById(id);
	}

	public boolean existsById(long id) {

		return interviewRepository.existsById(id);
	}

}
