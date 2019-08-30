package com.guilherme.challenge.util;

import org.springframework.stereotype.Component;

import com.guilherme.challenge.dtos.InterviewDTO;
import com.guilherme.challenge.entities.Interview;

@Component
public class InterviewDTOConverter {

	public InterviewDTO convertToDTO(Interview interview) {

		InterviewDTO tempInterview = new InterviewDTO();

		tempInterview.setApproved(interview.isApproved());
		tempInterview.setGeneralDescription(interview.getGeneralDescription());
		tempInterview.setIdInterview(interview.getIdInterview());
		tempInterview.setInterviewDate(interview.getInterviewDate());
		tempInterview.setInterviewer(interview.getInterviewer());
		tempInterview.setLevel(interview.getLevel());
		tempInterview.setProfessional(interview.getidProfessional());

		return tempInterview;
	}

	public Interview convertToEntity(InterviewDTO interview) {

		Interview tempInterview = new Interview();

		tempInterview.setApproved(interview.isApproved());
		tempInterview.setGeneralDescription(interview.getGeneralDescription());
		tempInterview.setIdInterview(interview.getIdInterview());
		tempInterview.setInterviewDate(interview.getInterviewDate());
		tempInterview.setInterviewer(interview.getInterviewer());
		tempInterview.setLevel(interview.getLevel());
		tempInterview.setidProfessional(interview.getProfessional());

		return tempInterview;
	}

}
