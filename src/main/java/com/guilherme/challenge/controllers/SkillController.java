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

import com.guilherme.challenge.dtos.SkillDTO;
import com.guilherme.challenge.entities.Skill;
import com.guilherme.challenge.exception.APIException;
import com.guilherme.challenge.service.SkillService;
import com.guilherme.challenge.util.SkillDTOConverter;

@RestController
@RequestMapping("/api/skills")
public class SkillController {

	@Autowired
	private SkillService skillService;

	@Autowired
	private SkillDTOConverter converter;

	@GetMapping(value = "")
	public ResponseEntity<List<SkillDTO>> findAllSkills() {
		List<SkillDTO> skillDTOList = new ArrayList<SkillDTO>();
		skillService.findAll().forEach(skill -> skillDTOList.add(converter.convertToDTO(skill)));
		return ResponseEntity.ok(skillDTOList);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<SkillDTO> findSkillById(@PathVariable("id") String id) {

		long idSkill = Long.valueOf(id);
		Skill s = skillService.findSkillByID(idSkill);

		if (s != null) {
			return new ResponseEntity<>(converter.convertToDTO(s), HttpStatus.OK);
		} else {
			throw new IllegalArgumentException("No Skill was found with this ID.");
		}
	}

	@PostMapping
	public ResponseEntity<SkillDTO> saveSkill(@Valid @RequestBody SkillDTO skillDTO, BindingResult result)
			throws APIException {
		if (result.hasErrors()) {
			throw new APIException("Validation error found", new BindException(result));
		}
		SkillDTO skillReturned = converter.convertToDTO(skillService.saveSkill(converter.convertToEntity(skillDTO)));
		return ResponseEntity.ok(skillReturned);
	}

	@PutMapping
	public ResponseEntity<SkillDTO> updateSkill(@Valid @RequestBody SkillDTO skillDTO, BindingResult result)
			throws APIException {
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> System.out.println(error.getDefaultMessage()));
			throw new APIException("Validation error found", new BindException(result));
		} else if (!skillService.existsById(skillDTO.getIdSkill())) {
			throw new APIException(
					"No Skill was found with the given ID, if you want to save a new Skill, try using the POST method",
					new BindException(result));
		}

		SkillDTO skillReturned = converter.convertToDTO(skillService.saveSkill(converter.convertToEntity(skillDTO)));
		return ResponseEntity.ok(skillReturned);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<SkillDTO> deleteSkill(@PathVariable("id") String id) {

		long idSkill = Long.valueOf(id);
		skillService.deleteSkillById(idSkill);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
