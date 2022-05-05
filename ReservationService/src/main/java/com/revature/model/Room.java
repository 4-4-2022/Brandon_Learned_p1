package com.revature.model;

import java.util.Objects;  

public class Room {

	private long id;
	private String roomName;
	private float roomCost;
	private int roomsAvailable;
	private boolean hasBalcony;	
	private int bedCount;
	
	public Room() {
		super();
	}
	public Room(long id, String roomName, float roomCost, int roomsAvailable, boolean hasBalcony, int bedCount) {
		super();
		this.id = id;
		this.roomName = roomName;
		this.roomCost = roomCost;
		this.roomsAvailable = roomsAvailable;
		this.hasBalcony = hasBalcony;
		this.bedCount = bedCount;
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getRoomName() {
		return roomName;
	}
	
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public float getRoomCost() {
		return roomCost;
	}
	
	public void setRoomCost(float roomCost) {
		this.roomCost = roomCost;
	}
	public int getRoomsAvailable() {
		return roomsAvailable;
	}
	
	public void setRoomsAvailable(int roomsAvailable) {
		this.roomsAvailable = roomsAvailable;
	}
	public boolean isHasBalcony() {
		return hasBalcony;
	}
	
	public void setHasBalcony(boolean hasBalcony) {
		this.hasBalcony = hasBalcony;
	}
	public int getBedCount() {
		return bedCount;
	}
	
	public void setBedCount(int bedCount) {
		this.bedCount = bedCount;
	}
	@Override
	public int hashCode() {
		return Objects.hash(bedCount, hasBalcony, id, roomCost, roomName, roomsAvailable);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		return bedCount == other.bedCount && hasBalcony == other.hasBalcony && id == other.id
				&& Float.floatToIntBits(roomCost) == Float.floatToIntBits(other.roomCost)
				&& Objects.equals(roomName, other.roomName) && roomsAvailable == other.roomsAvailable;
	}
	@Override
	public String toString() {
		return "Room [id=" + id + ", roomName=" + roomName + ", roomCost=" + roomCost + ", roomsAvailable="
				+ roomsAvailable + ", hasBalcony=" + hasBalcony + ", bedCount=" + bedCount + "]";
	}

	
	
	
}
