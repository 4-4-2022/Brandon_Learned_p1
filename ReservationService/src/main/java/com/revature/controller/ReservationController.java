package com.revature.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Customer;
import com.revature.model.Reservation;
import com.revature.model.Room;
import com.revature.service.ReservationService;

@RestController("reservationController")
@RequestMapping("/reservation")
public class ReservationController {

	private ReservationService reservationService;
	private CustomerController customerController;
	@Autowired
	private MessageController messageController;
	
	@Autowired
	public void setReservationService(ReservationService reservationService, CustomerController customerController) {
		this.reservationService = reservationService;
		this.customerController = customerController;
	}
	
	@GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Reservation>> findAll(){
		ResponseEntity<List<Reservation>> httpResponse = new ResponseEntity<>(this.reservationService.findAll(), HttpStatus.OK);
		return httpResponse;
	}
	
	@GetMapping("/{id}")
	public String findReservationById(@PathVariable long id) {
		Reservation reservation = this.reservationService.findReservationById(id);
		Customer customer = customerController.getCustomer(reservation.getCustomerId());
		Room room = messageController.findById(reservation.getRoomId());
		return "Customer ID: " + customer.getId() + "\n"
				+ "Customer: " + customer.getName() + "\n"
				+ "Room: " + room.getRoomName() + "\n"
				+ "Days Booked: " + reservation.getDaysBooked() + "\n"
				+ "Total Price of Stay: $" + reservation.getTotal();
	}
	
	
	@PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String save(@RequestBody Reservation reservation) {
		Room room = this.messageController.findById(reservation.getRoomId());
		Customer customer = this.customerController.getCustomer(reservation.getCustomerId());
		if(room.getRoomsAvailable() > 0) {
			
			if(customer.getReservationId() != 0) {
				return "You already have a reservation.";
			} else {
				reservation.setTotal(room.getRoomCost()* reservation.getDaysBooked());
				this.messageController.decrementRoomCount(room.getId());	
				this.reservationService.save(reservation);
				this.customerController.update(reservation.getCustomerId(), reservation.getId(), reservation.getTotal());
				
				return "Your reservation has been successfully created!\n"
						+ "Reservation Id: " + reservation.getId() + "\n"
						+ "Customer: " + customer.getName() + "\n"
						+ "Room Booked: " + room.getRoomName() + "\n"
						+ "Room Price Per Night: $" + room.getRoomCost() + "\n"
						+ "Days Booked: " + reservation.getDaysBooked() + "\n"
						+ "Total Stay Price: $" + reservation.getTotal();
			}

		} else {
			return "There are no vacancies available for that room.";
		}
			
	}
	
	@GetMapping(value="/message")
	public String contactRoomService() {
		String message = "Ah, general Kenobi";
		return this.reservationService.contactRoomService(message);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		Reservation reservation = this.reservationService.findReservationById(id);
		this.messageController.incrementRoomCount(reservation.getRoomId());	
		this.customerController.update(reservation.getCustomerId(), 0, 0);
		this.reservationService.delete(reservation);
		return "Reservation " + reservation.getId() + " successfully deleted.";
	}
	
	@PostMapping(value="/receive", consumes = MediaType.APPLICATION_XML_VALUE)
	public String save(@RequestBody Room room) {
		return room.toString();
	}
	
	
}
