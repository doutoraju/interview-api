package com.guilherme.challenge.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="skill_mapping", uniqueConstraints={@UniqueConstraint(columnNames={"interview", "skill"})})
public class SkillMapping implements Serializable{
	
	private static final long serialVersionUID = 1357299443004285202L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idSkillMapping;
	
	@ManyToOne
	@JoinColumn(name="interview")
	private Interview interview;

	@ManyToOne
	@JoinColumn(name="skill")
	private Skill skill;
	
	@Column(name="brief_description")
	private String briefDescription;
	
	@Column(name="rate")
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

	@Override
	public String toString() {
		return "SkillMapping [idSkillMapping=" + idSkillMapping + ", interview=" + interview + ", skill=" + skill
				+ ", briefDescription=" + briefDescription + ", rate=" + rate + "]";
	}
}
