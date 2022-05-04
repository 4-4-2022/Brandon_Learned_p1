package com.revature.model;

import java.util.Objects;

public class CustomerDTO {

	private long id;
	private String name;
	private float bill;
	private long reservationId;
	
	public CustomerDTO(long id, String name, float bill, long reservationId) {
		super();
		this.id = id;
		this.name = name;
		this.bill = bill;
		this.reservationId = reservationId;
	}
	public CustomerDTO() {
		super();
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

	public float getBill() {
		return bill;
	}
	public void setBill(float bill) {
		this.bill = bill;
	}
	public long getReservationId() {
		return reservationId;
	}
	public void setReservationId(long reservationId) {
		this.reservationId = reservationId;
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
		CustomerDTO other = (CustomerDTO) obj;
		return Objects.equals(bill, other.bill) && id == other.id && Objects.equals(name, other.name)
				&& reservationId == other.reservationId;
	}
	@Override
	public String toString() {
		return "CustomerDTO [id=" + id + ", name=" + name + ", bill=" + bill + ", reservationId=" + reservationId + "]";
	}
	
	
	
}
