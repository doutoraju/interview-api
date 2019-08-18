package com.guilherme.challenge;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.guilherme.challenge.entities.Professional;
import com.guilherme.challenge.repositories.ProfessionalRepository;

@SpringBootApplication
public class SpringChallengeApplication {

	
	@Autowired
	private ProfessionalRepository repo;
	
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
				
				repo.save(p);
				
				
			}
			
			Professional p= repo.findByName("Guilherme 1");
			
			
			p.setPhone("+55 41 992143637");
			repo.save(p);
			List<Professional> lista = repo.findAll();
			System.out.println("#########################Professional:#################################");
			lista.forEach(System.out::println);
		};
		
	}
	
}
