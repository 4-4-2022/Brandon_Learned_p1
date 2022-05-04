package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
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
import org.springframework.web.client.RestTemplate;

import com.revature.model.Reservation;
import com.revature.model.Room;
import com.revature.service.ReservationService;

@RestController("reservationController")
@RequestMapping("/reservation")
public class ReservationController {

	private ReservationService reservationService;
	private CustomerController customerController;
	
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
	public Reservation findCustomerById(@PathVariable long id) {
		return this.reservationService.findCustomerById(id);
	}
	
	
	@PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String save(@RequestBody Reservation reservation) {
		reservation.setTotal(reservation.getPrice() * reservation.getDaysBooked());
		this.reservationService.save(reservation);
		this.customerController.update(reservation.getCustomerId(), reservation.getId());
		
		return "Your reservation has been successfully created!\n"
				+ "Reservation Id: " + reservation.getId() + "\n"
				+ "Customer Id: " + reservation.getCustomerId() + "\n"
				+ "Room Booked: " + reservation.getRoomName() + "\n"
				+ "Days Booked: " + reservation.getDaysBooked() + "\n"
				+ "Total Price: $" + reservation.getTotal();
	}
	
	@GetMapping(value="/message")
	public String contactRoomService() {
		String message = "Ah, general Kenobi";
		return this.reservationService.contactRoomService(message);
	}
	
	//currently working on
	@GetMapping("/room/{id}")
	public Room getRoomById(@PathVariable long id) {
		Room room = this.reservationService.getRoomById(id);
		return room;
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		Reservation reservation = this.reservationService.findCustomerById(id);
		this.reservationService.delete(reservation);
		return "Reservation " + reservation.getId() + " successfully deleted.";
	}
	
	
}
