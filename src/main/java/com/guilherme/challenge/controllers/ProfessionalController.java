package com.guilherme.challenge.controllers;

import com.guilherme.challenge.dtos.ProfessionalDTO;
import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.exception.APIException;
import com.guilherme.challenge.service.ProfessionalService;
import com.guilherme.challenge.util.ProfessionalDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @Autowired
    private ProfessionalDTOConverter converter;

    @GetMapping(value = "/")
    public ResponseEntity<List<ProfessionalDTO>> listAllProfessionals() {
        List<ProfessionalDTO> dtos = new ArrayList<>();
        professionalService.findAllProfessional().forEach(p -> dtos.add(converter.convertToDTO(p)));
        return ResponseEntity.ok(dtos);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ProfessionalDTO> findProfessionalById(@PathVariable("id") String id) {
        long idProfessional = Long.valueOf(id);
        Professional p = professionalService.findProfessionalByID(idProfessional);
        //validate result found
        if (p != null) {
            return new ResponseEntity<>(converter.convertToDTO(p), HttpStatus.OK);
        } else {
            throw new IllegalArgumentException("No Professional was found with this ID.");
        }
    }

    @PostMapping
    public ResponseEntity<ProfessionalDTO> saveProfessional(
            @Valid @RequestBody ProfessionalDTO professionalDTO, BindingResult result) throws APIException {
        if (result.hasErrors()) {
            throw new APIException("Validation error found", new BindException(result));
        }
        ProfessionalDTO professionalReturned = converter.convertToDTO(
                professionalService.saveProfessional(converter.convertToEntity(professionalDTO)));
        return ResponseEntity.ok(professionalReturned);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteProfessional(@PathVariable("id") String id) {
        Long idProfessional = Long.valueOf(id);
        professionalService.deleteProfessionalById(idProfessional);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}