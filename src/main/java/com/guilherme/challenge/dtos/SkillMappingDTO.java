package com.guilherme.challenge.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.guilherme.challenge.entities.Interview;
import com.guilherme.challenge.entities.Skill;

public class SkillMappingDTO {
	
	
	private long idSkillMapping;
	
	@NotNull(message="Please select an interview")
	private Interview interview;

	@NotNull(message="Please select a skill")
	private Skill skill;

	
	
	private String briefDescription;
	
	@Min(1)
	@Max(5)
	private int rate;


	
	public long getIdSkillMapping() {
		return idSkillMapping;
	}

	public void setIdSkillMapping(long idSkillMapping) {
		this.idSkillMapping = idSkillMapping;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public String getBriefDescription() {
		return briefDescription;
	}

	public void setBriefDescription(String briefDescription) {
		this.briefDescription = briefDescription;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}
	
}
