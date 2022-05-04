package com.revature.dto;

public class RoomReservationDTO { //things needed to send over
	
	private long id;

	public RoomReservationDTO() {
		super();
	}

	public RoomReservationDTO(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}
