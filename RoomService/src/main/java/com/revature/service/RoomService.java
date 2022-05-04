package com.revature.service;

import java.util.List;  

import javax.jws.WebService;

import com.revature.model.Room;

//this connects to wsdl
@WebService
public interface RoomService {

	//These are the methods that will show up in the WSDL
	public List<Room> findAll();
	public void save(Room room);
	public void receiveMessage(String message);
	public Room findById(long id);
	public Room findByRoomName(String roomName);	
	public void deleteById(long id);
	public void decrementRoomCount(long id);
	public void incrementRoomCount(long id);

}
