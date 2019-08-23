package com.guilherme.challenge.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.guilherme.challenge.enums.Level;

@Entity
@Table(name="interview")
public class Interview implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4258560216872048523L;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_interview")
	private long idInterview;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Professional idProfessional;
	
	
	@Column(name="name_of_interviewer", nullable=false)
	private String interviewer;
	
	@Column(name="date_of_interview", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date interviewDate;
	
	@Column(name="general_description", nullable=true)
	private String generalDescription;
	
	@Column(name="approved")
	private boolean approved;
	
	@Enumerated(EnumType.STRING)
	private Level level;
	
	@OneToMany(mappedBy= "interview", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<SkillMapping> skillMapping;
	
	
	
	public long getIdInterview() {
		return idInterview;
	}

	public void setIdInterview(long idInterview) {
		this.idInterview = idInterview;
	}

	public Professional getidProfessional() {
		return idProfessional;
	}

	public void setidProfessional(Professional professional) {
		this.idProfessional = professional;
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
		return "Interview [idInterview=" + idInterview + ", professional=" + idProfessional + ", interviewer="
				+ interviewer + ", interviewDate=" + interviewDate + ", generalDescription=" + generalDescription
				+ ", approved=" + approved + ", level=" + level + "]";
	}
}
