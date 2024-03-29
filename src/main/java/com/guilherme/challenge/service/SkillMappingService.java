package com.guilherme.challenge.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.challenge.entities.Interview;
import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.entities.Skill;
import com.guilherme.challenge.entities.SkillMapping;
import com.guilherme.challenge.repositories.SkillMappingRepository;

@Service
public class SkillMappingService {

	@Autowired
	private SkillMappingRepository skillMappingRepository;

	@Autowired
	private InterviewService interviewService;

	public List<SkillMapping> findAll() {

		return skillMappingRepository.findAll();
	}

	public SkillMapping findSkillMappingByID(long skillMappingID) {
		Optional<SkillMapping> skillMappingOpt = skillMappingRepository.findById(skillMappingID);
		if (skillMappingOpt.isPresent()) {
			SkillMapping sm = skillMappingOpt.get();

			return sm;

		}
		return null;
	}

	public List<SkillMapping> findSkillMappingByInterview(Interview interviewId) {
		return skillMappingRepository.findAllByInterview(interviewId);
	}

	public List<SkillMapping> findSkillMappingBySkill(Skill skillId) {
		return skillMappingRepository.findAllBySkill(skillId);
	}

	public List<SkillMapping> findSkillMappingByProfessional(Professional professionalId) {
		List<SkillMapping> skillMappingTempList = new ArrayList<SkillMapping>();

		interviewService.findInterviewByProfessional(professionalId)
				.forEach(interview -> skillMappingTempList.addAll(findSkillMappingByInterview(interview)));

		return skillMappingTempList;
	}

	public SkillMapping saveSkillMapping(SkillMapping skillMapping) {
		return skillMappingRepository.save(skillMapping);
	}

	public void deleteSkillById(long id) {
		skillMappingRepository.deleteById(id);
	}

	public SkillMapping findSkillMappingByInterviewAndSkill(Interview interview, Skill skill) {
		SkillMapping skillMapping = skillMappingRepository.findSkillMappingByInterviewAndSkill(interview, skill);
		return skillMapping;

	}

	public boolean existsById(long id) {
		return skillMappingRepository.existsById(id);
	}

	public boolean existsSkillMappingWithInterviewAndSkill(Interview interview, Skill skill) {
		SkillMapping skillMapping = skillMappingRepository.findSkillMappingByInterviewAndSkill(interview, skill);
		System.out.println(skillMapping);

		return skillMapping != null;

	}

}
