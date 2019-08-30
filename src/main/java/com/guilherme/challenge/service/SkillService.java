package com.guilherme.challenge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.challenge.dtos.SkillDTO;
import com.guilherme.challenge.entities.Skill;
import com.guilherme.challenge.repositories.SkillRepository;

@Service
public class SkillService {

	@Autowired
	private SkillRepository skillRepository;

	public List<Skill> findAll() {

		return skillRepository.findAll();

	}

	public Skill findSkillByID(Long id) {
		Optional<Skill> skillOpt = skillRepository.findById(id);

		if (skillOpt.isPresent()) {
			Skill p = skillOpt.get();

			return p;

		}
		return null;
	}

	public Skill saveSkill(Skill skill) {

		return skillRepository.save(skill);
	}

	public void deleteSkillById(Long id) {

		skillRepository.deleteById(id);
	}

	public boolean existsById(long id) {

		return skillRepository.existsById(id);
	}

}
