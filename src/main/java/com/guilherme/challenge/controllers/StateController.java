package com.guilherme.challenge.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.challenge.enums.State;

@RestController
@RequestMapping("/api/states")
public class StateController {

	@GetMapping(value = "") 
	  public ResponseEntity<List<String>>  findInterview() {
		List<String> estados = new ArrayList<>();
		for(State s: State.values()) {
			estados.add(s.toString());
		}
		
		return ResponseEntity.ok(estados);
		
	  }
	
}
