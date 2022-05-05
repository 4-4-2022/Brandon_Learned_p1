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
	@Column(name="room_id")
	private long roomId;
	@Column(name="days_booked")
	private int daysBooked;
	@Column(name="total_price")
	private float total;
//	@ManyToMany
//	@JoinTable
//	private Set<ReservationTime> reservationTime;
	
	
	public Reservation() {
		super();
	}


	public Reservation(long id, long customerId, long roomId, int daysBooked) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.roomId = roomId;
		this.daysBooked = daysBooked;
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


	public long getRoomId() {
		return roomId;
	}


	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}


	public int getDaysBooked() {
		return daysBooked;
	}


	public void setDaysBooked(int daysBooked) {
		this.daysBooked = daysBooked;
	}


	public float getTotal() {
		return total;
	}


	public void setTotal(float total) {
		this.total = total;
	}


	@Override
	public int hashCode() {
		return Objects.hash(customerId, daysBooked, id, roomId, total);
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
				&& roomId == other.roomId && Float.floatToIntBits(total) == Float.floatToIntBits(other.total);
	}


	@Override
	public String toString() {
		return "Reservation [id=" + id + ", customerId=" + customerId + ", roomId=" + roomId + ", daysBooked="
				+ daysBooked + ", total=" + total + "]";
	}




	
}
