package com.guilherme.challenge.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="skill")
public class Skill implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5713852525182648816L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long idSkill;
	
	@Column(nullable=false)
	private String name;
	
	private String description;
	
	private boolean enabled;
	
	@OneToMany(mappedBy= "skill", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SkillMapping> skillMapping;
	
	public long getIdSkill() {
		return idSkill;
	}

	public void setIdSkill(long idSkill) {
		this.idSkill = idSkill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Skill [idSkill=" + idSkill + ", name=" + name + ", description=" + description + ", enabled=" + enabled
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (idSkill ^ (idSkill >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Skill other = (Skill) obj;
		if (idSkill != other.idSkill)
			return false;
		return true;
	}

	
	
}
