package com.guilherme.challenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.challenge.entities.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}
