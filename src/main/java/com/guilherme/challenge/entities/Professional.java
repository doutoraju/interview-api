package com.guilherme.challenge.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.guilherme.challenge.enums.State;

@Entity
@Table(name="professional")
public class Professional{
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_professional")
	private Long idProfessional;
	@Column(nullable=false)
	private String phone;
	@Column(nullable=false)
	private String name;
	private String description;
	private Date birthday;
	@Column(nullable=false)
	private String email;
	private String city;
	@Enumerated(EnumType.STRING)
	private State state;
	
	

	public Long getIdProfessional() {
		return idProfessional;
	}
	public void setIdProfessional(Long idProfessional) {
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
	@Override
	public String toString() {
		return "Professional [idProfessional=" + idProfessional + ", phone=" + phone + ", name=" + name
				+ ", description=" + description + ", birthday=" + birthday + ", email=" + email + ", city=" + city
				+ ", state=" + state + "]";
	}
	
	
}
