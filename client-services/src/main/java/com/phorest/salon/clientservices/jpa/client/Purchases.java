package com.phorest.salon.clientservices.jpa.client;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * This entity is created for Purchases made by clients during an appointment
 *
 */
@Entity
@Table(name = "purchases")
public class Purchases {

	@Id
	@NotNull
	private String id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "appointment_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Appointments appointment;

	private String name;

	private Double price;

	private Integer loyalty_points;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Appointments getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointments appointment) {
		this.appointment = appointment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getLoyalty_points() {
		return loyalty_points;
	}

	public void setLoyalty_points(Integer loyalty_points) {
		this.loyalty_points = loyalty_points;
	}

}
