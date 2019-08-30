package com.guilherme.challenge.util;

import org.springframework.stereotype.Component;

import com.guilherme.challenge.dtos.SkillMappingDTO;
import com.guilherme.challenge.entities.Skill;
import com.guilherme.challenge.entities.SkillMapping;

@Component
public class SkillMappingDTOConverter {

	public SkillMappingDTO convertToDTO(SkillMapping skillMapping) {

		SkillMappingDTO tempSkillMapping = new SkillMappingDTO();
		tempSkillMapping.setIdSkillMapping(skillMapping.getIdSkillMapping());
		tempSkillMapping.setBriefDescription(skillMapping.getBriefDescription());
		tempSkillMapping.setInterview(skillMapping.getInterview());
		tempSkillMapping.setSkill(skillMapping.getSkill());
		tempSkillMapping.setRate(skillMapping.getRate());

		return tempSkillMapping;
	}

	public SkillMapping convertToEntity(SkillMappingDTO skillMapping) {

		SkillMapping tempSkillMapping = new SkillMapping();
		tempSkillMapping.setIdSkillMapping(skillMapping.getIdSkillMapping());
		tempSkillMapping.setBriefDescription(skillMapping.getBriefDescription());
		tempSkillMapping.setInterview(skillMapping.getInterview());
		tempSkillMapping.setSkill(skillMapping.getSkill());
		tempSkillMapping.setRate(skillMapping.getRate());

		return tempSkillMapping;

	}

}
