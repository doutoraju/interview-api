package com.guilherme.challenge.util;

import org.springframework.stereotype.Component;

import com.guilherme.challenge.dtos.SkillDTO;
import com.guilherme.challenge.entities.Skill;

@Component
public class SkillDTOConverter {

	public SkillDTO convertToDTO(Skill skill) {

		SkillDTO tempSkill = new SkillDTO();

		tempSkill.setDescription(skill.getDescription());
		tempSkill.setEnabled(skill.isEnabled());
		tempSkill.setIdSkill(skill.getIdSkill());
		tempSkill.setName(skill.getName());

		return tempSkill;
	}

	public Skill convertToEntity(SkillDTO skill) {

		Skill tempSkill = new Skill();

		tempSkill.setDescription(skill.getDescription());
		tempSkill.setEnabled(skill.isEnabled());
		tempSkill.setIdSkill(skill.getIdSkill());
		tempSkill.setName(skill.getName());

		return tempSkill;
	}

}
