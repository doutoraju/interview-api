package com.guilherme.challenge.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.challenge.entities.Interview;
import com.guilherme.challenge.entities.Skill;
import com.guilherme.challenge.entities.SkillMapping;

public interface SkillMappingRepository extends JpaRepository<SkillMapping,Long> 
{
	public List<SkillMapping> findAllByInterview(Interview interview);
	public List<SkillMapping> findAllBySkill(Skill skill);
	public SkillMapping findSkillMappingByInterviewAndSkill(Interview interview, Skill skill); 
}
