package com.guilherme.challenge.util;

import org.springframework.stereotype.Component;

import com.guilherme.challenge.dtos.ProfessionalDTO;
import com.guilherme.challenge.entities.Professional;

@Component
public class ProfessionalDTOConverter {

	public ProfessionalDTO convertToDTO(Professional professional) {

		ProfessionalDTO tempProfessional = new ProfessionalDTO();

		tempProfessional.setBirthday(professional.getBirthday());
		tempProfessional.setCity(professional.getCity());
		tempProfessional.setDescription(professional.getDescription());
		tempProfessional.setEmail(professional.getEmail());
		tempProfessional.setIdProfessional(professional.getIdProfessional());
		tempProfessional.setName(professional.getName());
		tempProfessional.setState(professional.getState());
		tempProfessional.setPhone(professional.getPhone());

		return tempProfessional;
	}

	public Professional convertToEntity(ProfessionalDTO professional) {

		Professional tempProfessional = new Professional();

		tempProfessional.setBirthday(professional.getBirthday());
		tempProfessional.setCity(professional.getCity());
		tempProfessional.setDescription(professional.getDescription());
		tempProfessional.setEmail(professional.getEmail());
		tempProfessional.setIdProfessional(professional.getIdProfessional());
		tempProfessional.setName(professional.getName());
		tempProfessional.setState(professional.getState());
		tempProfessional.setPhone(professional.getPhone());

		return tempProfessional;
	}

}
