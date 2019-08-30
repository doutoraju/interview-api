package com.guilherme.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.challenge.dtos.ProfessionalDTO;
import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.entities.Skill;
import com.guilherme.challenge.repositories.ProfessionalRepository;

@Service
public class ProfessionalService {

	@Autowired
	private ProfessionalRepository professionalRepository;

	public List<Professional> findAll() {

		return professionalRepository.findAll();
	}

	public Professional findProfessionalByID(Long id) {
		Optional<Professional> professionalOpt = professionalRepository.findById(id);

		if (professionalOpt.isPresent()) {
			Professional p = professionalOpt.get();

			return p;

		}
		return null;
	}

	public Professional saveProfessional(Professional professional) {

		return professionalRepository.save(professional);
	}

	public void deleteProfessionalById(long id) {

		professionalRepository.deleteById(id);
	}

	public boolean existsById(long id) {

		return professionalRepository.existsById(id);
	}

}
