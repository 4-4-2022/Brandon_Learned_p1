package com.revature.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.stereotype.Component;

import com.revature.model.Room;

@WebService(serviceName = "room-service", targetNamespace = "http://service.revature.com/")
@Component
public interface RoomService {

	@WebMethod
	public List<Room> findAll();
	@WebMethod
	public Room findById(long id);
	@WebMethod
	public void incrementRoomCount(long id);
	@WebMethod
	public void decrementRoomCount(long id);
	
}
