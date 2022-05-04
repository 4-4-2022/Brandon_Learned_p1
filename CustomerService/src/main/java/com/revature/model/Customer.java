package com.revature.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Customer {

	@Id
	@Column(name = "customer_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "customer_id_seq")
	@SequenceGenerator(allocationSize = 1, name = "customer_id_seq")
	private long id;
	@Column(name = "customer_name", nullable=false)
	private String name;
	@Column(name="bill")
	private float bill;
	@Column (name="reservation_id")
	private long reservationId;
	
	public Customer() {
		super();
	}

	public Customer(long id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.bill = 0;
		this.reservationId = 0;
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
		Customer other = (Customer) obj;
		return Objects.equals(bill, other.bill) && id == other.id && Objects.equals(name, other.name)
				&& reservationId == other.reservationId;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", bill=" + bill + ", reservationId=" + reservationId + "]";
	}


	


	

}
