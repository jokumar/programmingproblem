package com.phorest.salon.clientservices.jpa.client;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * This entity is created for Client
 *
 */
@Entity
@Table(name = "client")
public class Client {

	@Id
	@NotNull
	private String id;

	private String first_name;

	private String last_name;

	@Email
	private String email;

	private String phone;

	private String gender;

	private Boolean banned;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
	private Set<Appointments> appointments = new HashSet<>();

	
	
	
	public Set<Appointments> getAppointments() {
		return appointments;
	}

	public void setAppointments(Set<Appointments> appointments) {
		this.appointments = appointments;
	}

	public Boolean getBanned() {
		return banned;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean isBanned() {
		return banned;
	}

	public void setBanned(Boolean banned) {
		this.banned = banned;
	}

}
