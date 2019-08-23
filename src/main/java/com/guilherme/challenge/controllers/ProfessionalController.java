package com.guilherme.challenge.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.challenge.dtos.ProfessionalDTO;
import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.exception.APIException;
import com.guilherme.challenge.service.ProfessionalService;
import com.guilherme.challenge.util.ProfessionalDTOConverter;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {
	
	@Autowired
	private ProfessionalService professionalService;
	
	@Autowired
	private ProfessionalDTOConverter converter;
	
	@GetMapping(value = "") 
	  public ResponseEntity<List<ProfessionalDTO>>  findAllProfessionals() {
		List<ProfessionalDTO> professionalDTOList = new ArrayList<ProfessionalDTO>();
		professionalService.findAll().forEach(professional -> professionalDTOList.add(converter.convertToDTO(professional)));
		return ResponseEntity.ok(professionalDTOList);
	  }
	
	  @GetMapping(value = "/{id}") 
	  public ResponseEntity<ProfessionalDTO>  findProfessionalById(@PathVariable("id") String id) {
		  
		  long idProfessional = Long.valueOf(id);  
		  Professional p = professionalService.findProfessionalByID(idProfessional);
		  
		  if( p != null) {
			  return new ResponseEntity<>(converter.convertToDTO(p), HttpStatus.OK);
		  }else {
			  throw new IllegalArgumentException("No professional was found with this ID");
		  }
	  
	  }
	 
	@PostMapping
	public ResponseEntity<ProfessionalDTO> saveProfessional(@Valid @RequestBody ProfessionalDTO professionalDTO, BindingResult result) throws APIException {
		
		if(result.hasErrors()) {
			throw new APIException("Validation error found", new BindException(result));
		}
		ProfessionalDTO professionalReturned  = converter.convertToDTO(professionalService.saveProfessional(converter.convertToEntity(professionalDTO)));
		return ResponseEntity.ok(professionalReturned);
	}
	
	@PutMapping
	public ResponseEntity<ProfessionalDTO> updateProfessional(@Valid @RequestBody ProfessionalDTO professionalDTO, BindingResult result) throws APIException{
		
		if(result.hasErrors()) {
			throw new APIException("Validation error found", new BindException(result));
		}else if (!professionalService.existsById(professionalDTO.getIdProfessional())){
			
			throw new APIException("No Professional was found with the given ID, if you want to save a new professional, try using the POST method", new BindException(result));
		}
		ProfessionalDTO professionalReturned  = converter.convertToDTO(professionalService.saveProfessional(converter.convertToEntity(professionalDTO)));
		return ResponseEntity.ok(professionalReturned);
	}
	
	  @DeleteMapping(value = "/{id}") 
	  public ResponseEntity<ProfessionalDTO>  deleteProfessional(@PathVariable("id") String id) {
		  
		  long idProfessional = Long.valueOf(id);
		  professionalService.deleteProfessionalById(idProfessional);
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  }		 
}
