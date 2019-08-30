package com.guilherme.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.challenge.entities.Professional;

public interface ProfessionalRepository extends JpaRepository<Professional, Long> {

	Professional findByName(String name);

	Professional findByCity(String city);

}
