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

import com.guilherme.challenge.dtos.InterviewDTO;
import com.guilherme.challenge.entities.Interview;
import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.exception.APIException;
import com.guilherme.challenge.service.InterviewService;
import com.guilherme.challenge.service.ProfessionalService;
import com.guilherme.challenge.util.InterviewDTOConverter;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {

	@Autowired
	private InterviewService interviewService;

	@Autowired
	private InterviewDTOConverter converter;

	@Autowired
	private ProfessionalService professionalService;

	@GetMapping(value = "")
	public ResponseEntity<List<InterviewDTO>> findInterview() {
		List<InterviewDTO> interviewDTOList = new ArrayList<InterviewDTO>();
		interviewService.findAll().forEach(interview -> interviewDTOList.add(converter.convertToDTO(interview)));
		return ResponseEntity.ok(interviewDTOList);

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<InterviewDTO> findInterviewById(@PathVariable("id") String id) {
		long idInterview = Long.valueOf(id);

		Interview i = interviewService.findInterviewByID(idInterview);

		if (i != null) {

			return new ResponseEntity<>(converter.convertToDTO(i), HttpStatus.OK);
		} else {

			throw new IllegalArgumentException("No Interview was found with this ID");
		}

	}

	@GetMapping(value = "/professional/{id}")
	public ResponseEntity<List<InterviewDTO>> findInterviewByProfessionalId(@PathVariable("id") String id) {
		long idProfessional = Long.valueOf(id);

		Professional p = professionalService.findProfessionalByID(idProfessional);

		List<InterviewDTO> interviewDTOList = new ArrayList<InterviewDTO>();
		interviewService.findInterviewByProfessional(p)
				.forEach(interview -> interviewDTOList.add(converter.convertToDTO(interview)));
		return ResponseEntity.ok(interviewDTOList);
	}

	@PostMapping
	public ResponseEntity<InterviewDTO> saveInterview(@Valid @RequestBody InterviewDTO interviewDTO,
			BindingResult result) throws APIException {
		// Response<InterviewDTO> response = new Response<InterviewDTO>();

		if (result.hasErrors()) {
			throw new APIException("Validation Error found", new BindException(result));
		}
		InterviewDTO interviewReturned = converter
				.convertToDTO(interviewService.saveInterview(converter.convertToEntity(interviewDTO)));

		return ResponseEntity.ok(interviewReturned);
	}

	@PutMapping
	public ResponseEntity<InterviewDTO> updateInterview(@Valid @RequestBody InterviewDTO interviewDTO,
			BindingResult result) throws APIException {
		if (result.hasErrors()) {
			throw new APIException("Validation Error found", new BindException(result));
		} else if (!interviewService.existsById(interviewDTO.getIdInterview())) {

			throw new APIException(
					"No Interview was found with the given ID, if you want to save a new Interview, try using the POST method",
					new BindException(result));
		}
		InterviewDTO interviewReturned = converter
				.convertToDTO(interviewService.saveInterview(converter.convertToEntity(interviewDTO)));
		return ResponseEntity.ok(interviewReturned);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<InterviewDTO> deleteInterview(@PathVariable("id") String id) {

		long idInterview = Long.valueOf(id);
		interviewService.deleteInterviewById(idInterview);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}
}
