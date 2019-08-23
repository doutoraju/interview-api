package com.guilherme.challenge.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.hibernate.exception.ConstraintViolationException;
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

import com.guilherme.challenge.dtos.SkillDTO;
import com.guilherme.challenge.dtos.SkillMappingDTO;
import com.guilherme.challenge.entities.Interview;
import com.guilherme.challenge.entities.Skill;
import com.guilherme.challenge.entities.SkillMapping;
import com.guilherme.challenge.exception.APIException;
import com.guilherme.challenge.service.InterviewService;
import com.guilherme.challenge.service.SkillMappingService;
import com.guilherme.challenge.service.SkillService;
import com.guilherme.challenge.util.SkillMappingDTOConverter;

@RestController
@RequestMapping("/api/skillMapping")
public class SkillMappingController {
	
	@Autowired
	private SkillMappingService skillMappingService;
	
	@Autowired
	private InterviewService interviewService;
	
	@Autowired
	private SkillService skillService;
	
	@Autowired
	private SkillMappingDTOConverter converter;
	
	
	
	@GetMapping(value = "") 
	  public ResponseEntity<List<SkillMappingDTO>>  findAllSkills() {
		List<SkillMappingDTO> skillMappingDTOList = new ArrayList<SkillMappingDTO>();
		skillMappingService.findAll().forEach(skillMapping -> skillMappingDTOList.add(converter.convertToDTO(skillMapping)));
		return ResponseEntity.ok(skillMappingDTOList);
		
	  }
	
	 @GetMapping(value = "/{id}") 
	  public ResponseEntity<SkillMappingDTO>  findSkillMappingById(@PathVariable("id") String id) {
		  
		 long idSkillMapping  = Long.valueOf(id);  
		 SkillMapping sm = skillMappingService.findSkillMappingByID(idSkillMapping);
		  
		  if( sm != null) {
			  return new ResponseEntity<>(converter.convertToDTO(sm), HttpStatus.OK);
		  }else {
			  throw new IllegalArgumentException("No Skill Mapping was found with this ID.");
			  
		  }
	  
	  }
	
	 
	 @GetMapping(value = "/interview/{id}") 
	  public ResponseEntity<List<SkillMappingDTO>>  findSkillMappingByInterviewId(@PathVariable("id") String id) {
		  
		 long idInterview  = Long.valueOf(id);
		 List<SkillMappingDTO> skillMappingDTOList = new ArrayList<SkillMappingDTO>();
		 Interview interview = interviewService.findInterviewByID(idInterview);
		 
		skillMappingService.findSkillMappingByInterview(interview).forEach(skillMapping -> skillMappingDTOList.add(converter.convertToDTO(skillMapping)));
			return ResponseEntity.ok(skillMappingDTOList);
	  }
	 
	 
	 @GetMapping(value = "/skill/{id}") 
	  public ResponseEntity<List<SkillMappingDTO>>  findSkillMappingBySkillId(@PathVariable("id") String id) {
		  
		 long idSkill = Long.valueOf(id);
		 List<SkillMappingDTO> skillMappingDTOList = new ArrayList<SkillMappingDTO>();
		 Skill skill= skillService.findSkillByID(idSkill);
		 
		skillMappingService.findSkillMappingBySkill(skill).forEach(skillMapping -> skillMappingDTOList.add(converter.convertToDTO(skillMapping)));
			return ResponseEntity.ok(skillMappingDTOList);
	  }
	 
	 
	 @PostMapping
		public ResponseEntity<SkillMappingDTO> saveSkill(@Valid @RequestBody SkillMappingDTO skillMappingDTO, BindingResult result) throws APIException{
			
			if(result.hasErrors()) {
				throw new APIException("Validation error found", new BindException(result)) ;
			}
			
			
			SkillMapping tempSkillMapping = converter.convertToEntity(skillMappingDTO);
			if(skillMappingService.existsSkillMappingWithInterviewAndSkill(tempSkillMapping.getInterview(), tempSkillMapping.getSkill())) {
				throw new ConstraintViolationException("There is already a combination of Skill and Interview saved with the details provided, try using the PUT method to update it.", null, null);
			}
			
			
			
			SkillMappingDTO skillMappingReturned =  converter.convertToDTO(skillMappingService.saveSkillMapping(tempSkillMapping));
			
			
			return ResponseEntity.ok(skillMappingReturned);
		}
		
		
		@PutMapping
		public ResponseEntity<SkillMappingDTO> updateSkill(@Valid @RequestBody SkillMappingDTO skillMappingDTO, BindingResult result) throws APIException{
			if(result.hasErrors()) {
				result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
				throw new APIException("Validation error found", new BindException(result)) ;
			}
			
			
			
			if (!skillMappingService.existsById(skillMappingDTO.getIdSkillMapping())){
				
				throw new APIException("No Skill was found with the given ID, if you want to save a new professional, try using the POST method", new BindException(result));
			}
			
			SkillMappingDTO skillMappingReturned =  converter.convertToDTO(skillMappingService.saveSkillMapping(converter.convertToEntity(skillMappingDTO)));
			
			
			return ResponseEntity.ok(skillMappingReturned);
		}
		
		
		
		  @DeleteMapping(value = "/{id}") 
		  public ResponseEntity<SkillDTO>  deleteSkill(@PathVariable("id") String id) {
			  
			  long idSkill = Long.valueOf(id);  
			  skillMappingService.deleteSkillById(idSkill);
			  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		  }
	 
/*		
	  @GetMapping(value = "/{id}") 
	  public ResponseEntity<SkillDTO>  findSkillById(@PathVariable("id") String id) {
		  
		 long idSkill  = Long.valueOf(id);  
		 Skill s = skillService.findSkillByID(idSkill);
		  
		  if( s != null) {
			  return new ResponseEntity<>(converter.convertToDTO(s), HttpStatus.OK);
		  }else {
			  throw new IllegalArgumentException("No Skill was found with this ID.");
			  
		  }
	  
	  }
	 

	
	
	*/
	
}
