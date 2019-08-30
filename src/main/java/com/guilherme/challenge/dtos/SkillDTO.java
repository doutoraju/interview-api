package com.guilherme.challenge.dtos;

public class SkillDTO {

	private long idSkill;

	private String name;

	private String description;

	private boolean enabled;

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

}
