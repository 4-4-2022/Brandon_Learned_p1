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
@Table(name="reservation")
public class Reservation {

	@Id
	@Column(name="reservation_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "reservation_id_seq")
	@SequenceGenerator(allocationSize = 1, name = "reservation_id_seq")
	private long id;
//	@Column(name = "hotel_id")
//	private long hotelId;
	@Column(name = "customer_id")
	private long customerId;
	@Column(name="room_name")
	private String roomName;
	@Column(name="days_booked")
	private int daysBooked;
	@Column(name = "price")
	private float price;
	@Column(name="total_price")
	private float total;
//	@ManyToMany
//	@JoinTable
//	private Set<ReservationTime> reservationTime;
	
	
	public Reservation() {
		super();
	}


	public Reservation(long id, long customerId, String roomName, int daysBooked, float price) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.roomName = roomName;
		this.daysBooked = daysBooked;
		this.price = price;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getCustomerId() {
		return customerId;
	}


	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public int getDaysBooked() {
		return daysBooked;
	}


	public void setDaysBooked(int daysBooked) {
		this.daysBooked = daysBooked;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	@Override
	public int hashCode() {
		return Objects.hash(customerId, daysBooked, id, price, roomName, total);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reservation other = (Reservation) obj;
		return customerId == other.customerId && daysBooked == other.daysBooked && id == other.id
				&& Float.floatToIntBits(price) == Float.floatToIntBits(other.price)
				&& Objects.equals(roomName, other.roomName)
				&& Float.floatToIntBits(total) == Float.floatToIntBits(other.total);
	}


	@Override
	public String toString() {
		return "Reservation [id=" + id + ", customerId=" + customerId + ", roomName=" + roomName + ", daysBooked="
				+ daysBooked + ", price=" + price + ", total=" + total + "]";
	}


	
}
