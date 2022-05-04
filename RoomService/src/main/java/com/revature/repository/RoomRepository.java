package com.revature.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Room;

//This connects to db
@Repository("roomRepository")
public interface RoomRepository extends JpaRepository<Room, String>{

	public List<Room> findAll();
	public <S extends Room> S save(Room room); //defined a generic S extending Customer class
	public Room findByRoomName(String roomName);
	public void delete(Room room);
	public Room findById(long id);
}
