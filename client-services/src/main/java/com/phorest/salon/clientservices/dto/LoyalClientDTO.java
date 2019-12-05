package com.phorest.salon.clientservices.dto;

public class LoyalClientDTO implements Comparable<LoyalClientDTO> {

	

	private String first_name;

	private String last_name;

	private String email;

	private String phone;

	private String gender;

	private Integer loyalty_points;

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

	public Integer getLoyalty_points() {
		return loyalty_points;
	}

	public void setLoyalty_points(Integer loyalty_points) {
		this.loyalty_points = loyalty_points;
	}

	@Override
	public int compareTo(LoyalClientDTO o) {
		return (int)o.getLoyalty_points()-this.loyalty_points;
	}
	
	

}
