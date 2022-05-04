package com.revature.service;

import java.util.List;  

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.revature.model.Room;
import com.revature.repository.RoomRepository;

@WebService(endpointInterface = "com.revature.service.RoomService")
@RequestMapping("/room")
public class RoomServiceImpl implements RoomService{

	private RoomRepository roomRepository; //instance field
	
	public RoomServiceImpl() {//constructor no fields
	}
	
	public RoomServiceImpl(RoomRepository roomRepository) { //constructor with fields
		this.roomRepository = roomRepository;
	}
	
	@Autowired
	public void setRoomRepository(RoomRepository roomRepository) { //autowired setter method
		this.roomRepository = roomRepository;
	}

	@WebMethod
	@GetMapping("/all")
	public List<Room> findAll() {
		return this.roomRepository.findAll();
	}

	@WebMethod
	public void save(Room room) {
		this.roomRepository.save(room);
	}
	
	@WebMethod
	public void receiveMessage(String message) {
		System.out.println(message);
	}

	@Override
	@WebMethod
	
	public Room findByRoomName(String roomName) {
		return this.roomRepository.findByRoomName(roomName);

	}

	@Override
	public void deleteById(long id) {
		List<Room> rooms = this.roomRepository.findAll();
		for(Room room : rooms) {
			if(room.getId()==id) {
				this.roomRepository.delete(room);
			}
		}
	}

	@Override
	public Room findById(long id) {
		return this.roomRepository.findById(id);
	}

	@Override
	public void decrementRoomCount(long id) {
		Room room = this.roomRepository.findById(id);
		if(room.getRoomsAvailable() != 0) {
			room.setRoomsAvailable(room.getRoomsAvailable()-1);
			this.roomRepository.save(room);
		}
	}

	@Override
	public void incrementRoomCount(long id) {
		Room room = this.roomRepository.findById(id);
		room.setRoomsAvailable(room.getRoomsAvailable()+1);
		this.roomRepository.save(room);
		
	}










}
