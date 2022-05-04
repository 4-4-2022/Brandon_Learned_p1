package com.revature.service;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.revature.model.Customer;
import com.revature.model.Reservation;
import com.revature.model.Room;
import com.revature.repository.ReservationRepository;


@Service("reservationService")
public class ReservationService {
	
	
	@Autowired
	private RestTemplate restTemplate2;
	
	private ReservationRepository reservationRepository;
	
	@Autowired
	public void setReservationRepository(ReservationRepository reservationRepository) {
		this.reservationRepository = reservationRepository;
	}
	

	public List<Reservation> findAll() {
		return this.reservationRepository.findAll();
	}
	
	public Reservation findCustomerById(long id) {
		return this.reservationRepository.findReservationById(id);
	}

	public void save(Reservation reservation) {
		this.reservationRepository.save(reservation);		
	}

	public void delete(Reservation reservation) {
		this.reservationRepository.delete(reservation);
	}

	public String contactRoomService(String message) {
		final String URI = "/room-service?wsdl";
		final String SOAP_MESSAGE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.revature.com/\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <ser:receiveMessage>\r\n"
				+ "         <!--Optional:-->\r\n"
				+ "         <arg0>"+message+"</arg0>\r\n"
				+ "      </ser:receiveMessage>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> request = new HttpEntity<>(SOAP_MESSAGE, header);
		this.restTemplate2.postForLocation(URI, request);
		return "Success";			
	}
	
	//currently working on
	public Room getRoomById(long id) {
		final String URI = "/room-service?wsdl";
		final String SOAP_MESSAGE = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.revature.com/\">\r\n"
				+ "   <soapenv:Header/>\r\n"
				+ "   <soapenv:Body>\r\n"
				+ "      <ser:findById>\r\n"
				+ "         <arg0>"+id+"</arg0>\r\n"
				+ "      </ser:findById>\r\n"
				+ "   </soapenv:Body>\r\n"
				+ "</soapenv:Envelope>";
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_XML);
		HttpEntity<String> request = new HttpEntity<>(SOAP_MESSAGE, header);
		ResponseEntity<Room> httpResponse = restTemplate2.exchange(URI, HttpMethod.GET, request, Room.class);
		return httpResponse.getBody();
					
	}

	
	
	
	
	
}
