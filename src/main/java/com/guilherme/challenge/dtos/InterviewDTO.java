package com.guilherme.challenge.dtos;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.enums.Level;

public class InterviewDTO {
	
	
	private long idInterview;
	
	
	@NotNull(message="Please select a professional")
	private Professional professional;
	
	@NotEmpty(message="Please specify the interviewer's name")
	private String interviewer;
	
	@NotNull(message="Please set up a date for the interview")
	private Date interviewDate;
	
	private String generalDescription;
	
	private boolean approved;
	
	private Level level;

	public long getIdInterview() {
		return idInterview;
	}

	public void setIdInterview(long idInterview) {
		this.idInterview = idInterview;
	}

	public Professional getProfessional() {
		return professional;
	}

	public void setProfessional(Professional professional) {
		this.professional = professional;
	}

	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getGeneralDescription() {
		return generalDescription;
	}

	public void setGeneralDescription(String generalDescription) {
		this.generalDescription = generalDescription;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}
	
	
	
	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public String toString() {
		return "InterviewDTO [idInterview=" + idInterview + ", professional=" + professional + ", interviewer="
				+ interviewer + ", interviewDate=" + interviewDate + ", generalDescription=" + generalDescription
				+ ", approved=" + approved + ", level=" + level + "]";
	}
	
}
