package com.revature.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {

	private long id;
	private String name;
	private float bill;
	private long reservationId;
	
	public Customer() {
		super();
	}
	
	
	public Customer(long id, String name, float bill, long reservationId) {
		super();
		this.id = id;
		this.name = name;
		this.bill = bill;
		this.reservationId = reservationId;
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getReservationId() {
		return reservationId;
	}
	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
	}
	

	public float getBill() {
		return bill;
	}


	public void setBill(float bill) {
		this.bill = bill;
	}


	@Override
	public int hashCode() {
		return Objects.hash(bill, id, name, reservationId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(bill, other.bill) && id == other.id && Objects.equals(name, other.name)
				&& reservationId == other.reservationId;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", bill=" + bill + ", reservationId=" + reservationId + "]";
	}



	
}
