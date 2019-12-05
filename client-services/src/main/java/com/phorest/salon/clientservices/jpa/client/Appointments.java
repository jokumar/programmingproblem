package com.phorest.salon.clientservices.jpa.client;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This entity is created for Appointments
 *
 */
@Entity
@Table(name = "appointments")
public class Appointments {

	@Id
	@NotNull
	private String id;

	private LocalDateTime start_time;

	private LocalDateTime end_time;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "client_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Client client;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appointment")
	private Set<Purchases> purchases = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "appointment")
	private Set<Services> services = new HashSet<>();



	public Set<Purchases> getPurchases() {
		return purchases;
	}

	public void setPurchases(Set<Purchases> purchases) {
		this.purchases = purchases;
	}

	public Set<Services> getServices() {
		return services;
	}

	public void setServices(Set<Services> services) {
		this.services = services;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalDateTime start_time) {
		this.start_time = start_time;
	}

	public LocalDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalDateTime end_time) {
		this.end_time = end_time;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}
