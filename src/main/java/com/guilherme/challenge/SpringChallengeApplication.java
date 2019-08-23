package com.guilherme.challenge;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.guilherme.challenge.entities.Interview;
import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.entities.Skill;
import com.guilherme.challenge.entities.SkillMapping;
import com.guilherme.challenge.enums.Level;
import com.guilherme.challenge.repositories.InterviewRepository;
import com.guilherme.challenge.repositories.ProfessionalRepository;
import com.guilherme.challenge.repositories.SkillMappingRepository;
import com.guilherme.challenge.repositories.SkillRepository;

@SpringBootApplication
public class SpringChallengeApplication {

	
	@Autowired
	private ProfessionalRepository professionalRepository;
	
	@Autowired
	private InterviewRepository interviewRepository;
	
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private SkillMappingRepository skillMappingRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringChallengeApplication.class, args);
		System.out.println("Oioioi");
	}
	
	@Bean
	public CommandLineRunner commandLinerunner() {
		return args -> {
			for (int i = 0; i < 10; i++) {
				Professional p = new Professional();
				
				p.setName("Guilherme " +i);
				p.setBirthday(new Date());
				p.setEmail("guil"+i+"@maciel.com");
				p.setPhone("+55 41 9 8765 4321");
				professionalRepository.save(p);
				
				
				Interview in = new Interview();
				in.setidProfessional(p);
				in.setInterviewer("Internviewer " + i) ;
				in.setInterviewDate(new Date());
				
				in.setLevel(Level.EXPERT);
				
				
				interviewRepository.save(in);
				
				Skill s = new Skill();
				
				s.setName("Skill "+i);
				s.setDescription("Skill skilloso "+i);
				s.setEnabled(i%2 == 0);
				skillRepository.save(s);
				
				SkillMapping sm = new SkillMapping();
				
				sm.setBriefDescription("Description " + i);
				
				sm.setInterview(in);
				
				sm.setSkill(s);
				sm.setRate(i%5 + 1);
				
				
	
				skillMappingRepository.save(sm);
				
			}
			
			Professional p= professionalRepository.findByName("Guilherme 1");
			
			
			p.setPhone("+55 41 992143637");
			professionalRepository.save(p);
			
			
			/*
			List<Professional> lista = repo.findAll();
			System.out.println("#########################Professional - Start:#################################");
			lista.forEach(System.out::println);
			
			System.out.println("#########################Professional - End:#################################");
			
			System.out.println("#########################Interview - Start:#################################");
			List<Interview> listaInterview = interviewRepository.findAll();
			
			listaInterview.forEach(System.out::println);
			
			System.out.println("#########################Interview -End:#################################");
			
			System.out.println("#########################Skill - Start:#################################");
			skillRepository.findAll().forEach(System.out::println);
			System.out.println("#########################Skill - End:#################################");
			*/
		};
		
	}
	
}
