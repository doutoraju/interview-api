package com.guilherme.challenge.dtos;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.guilherme.challenge.enums.State;

public class ProfessionalDTO {

	private long idProfessional;

	@NotEmpty(message = "A phone number should be provided")
	private String phone;
	@NotEmpty(message = "A name should be provided")
	private String name;
	private String description;
	private Date birthday;
	@NotEmpty(message = "An email should be provided")
	@Email(message = "Please enter a valid email")
	private String email;
	private String city;

	private State state;

	public Long getIdProfessional() {
		return idProfessional;
	}

	public void setIdProfessional(long idProfessional) {
		this.idProfessional = idProfessional;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
