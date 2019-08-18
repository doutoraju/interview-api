package com.guilherme.challenge.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.challenge.dtos.ProfessionalDTO;
import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.responses.Response;
import com.guilherme.challenge.services.ProfessionalService;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {
	
	
	@Autowired
	public ProfessionalService professionalService;
	
	  @GetMapping(value = "/{id}") 
	  public/* ResponseEntity<ProfessionalDTO> */ ResponseEntity<Response<ProfessionalDTO>>  findProfessionalById(@PathVariable("id") String id) {
		  Response<ProfessionalDTO> response = new Response<ProfessionalDTO>();
		  
		  long idProfessional;
		  try {
			  idProfessional = Long.valueOf(id);  
		  } catch (NumberFormatException e) {
			  response.getErrors().add("The Id provided is not a valid number.");
			  return ResponseEntity.badRequest().body(response);
			  
			//  return response;
		  }
		  
		  Professional p = professionalService.findProfessionalByID(idProfessional);
		  
		  if( p != null) {
			  response.setData(convertToDTO(p));
			  return ResponseEntity.ok(response);
		  }else {
			  
			  response.getErrors().add("No Professional was found with this ID.");
			  return ResponseEntity.badRequest().body(response);
		  }
	  
	  }
	 

	@PostMapping
	public ResponseEntity<Response<ProfessionalDTO>> saveProfessional(@Valid @RequestBody ProfessionalDTO professionalDTO, BindingResult result) {
		Response<ProfessionalDTO> response = new Response<ProfessionalDTO>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		ProfessionalDTO professionalReturned;
		professionalReturned = convertToDTO(professionalService.saveProfessional(convertToEntity(professionalDTO)));
		
		response.setData(professionalReturned);
		
		return ResponseEntity.ok(response);
	}
	
	
	  @DeleteMapping(value = "/{id}") 
	  public ResponseEntity<Response<ProfessionalDTO>>  deleteProfessional(@PathVariable("id") String id) {
		  Response<ProfessionalDTO> response = new Response<ProfessionalDTO>();
		  
		  long idProfessional;
		  try {
			  idProfessional = Long.valueOf(id);  
			  professionalService.deleteProfessionalById(idProfessional);
		  } catch (NumberFormatException e) {
			  response.getErrors().add("The Id provided is not a valid number.");
			  return ResponseEntity.badRequest().body(response);
			  
			//  return response;
		  }catch (IllegalArgumentException e) {
			  response.getErrors().add("Somehow a null entyty was given.");
			  return ResponseEntity.badRequest().body(response);
			  
		  }catch(EmptyResultDataAccessException e) {
			  response.getErrors().add("No Professional with the given ID was found.");
			  return ResponseEntity.badRequest().body(response);
		  }
		  
		 
			return ResponseEntity.ok(response);
	  }
	
	
	
	private ProfessionalDTO convertToDTO(Professional professional) {
		
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
	
	private Professional convertToEntity(ProfessionalDTO professional) {
		
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
