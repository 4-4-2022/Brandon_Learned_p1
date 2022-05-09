package com.revature.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Room;
import com.revature.soap.RoomService;
 
@RestController("messageController")
@RequestMapping("/room")
public class MessageController {

	@Autowired
	private RoomService roomService;
	
	@GetMapping("/all")
	public List<Room> getSoapMessage() {
		return this.roomService.findAll();
	}
	
	@GetMapping("/{id}")
	public Room findById(@PathVariable long id) {
		return this.roomService.findById(id);
	}
	
	@GetMapping("/increment/{id}")
	public String incrementRoomCount(@PathVariable long id) {
		this.roomService.incrementRoomCount(id);
		return "Incremented room count";
		
	}
	
	@GetMapping("/decrement/{id}")
	public String decrementRoomCount(@PathVariable long id) {
		this.roomService.decrementRoomCount(id);
		return "Decremented room count";
		
	}
	
}
