package com.revature.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.revature.model.Customer;
import com.revature.model.CustomerDTO;


@RestController
@RequestMapping("/customer")
public class CustomerController {

	
	@Autowired
	private RestTemplate restTemplate1;
	
	@GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Customer> findAll(){		
		ResponseEntity<List<Customer>> httpResponse = restTemplate1.exchange("http://localhost:8082/customer/all", 
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {
				});
		return httpResponse.getBody();
	}	
	
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable long id) {
		ResponseEntity<Customer> customer = restTemplate1.getForEntity("http://localhost:8082/customer/" + id, Customer.class);
		return customer.getBody();
	}
	
	@PostMapping("/new")
	public String createCustomer(@RequestBody CustomerDTO customer) {
		ResponseEntity<String> response = restTemplate1.postForEntity("http://localhost:8082/customer/new", customer, String.class);
		return response.getBody();
		
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		Customer customer = getCustomer(id);
		restTemplate1.delete("http://localhost:8082/customer/delete/" + id);
		return "Customer " + customer.getName() + " successfully deleted.";
	}
	
	@PutMapping("/update/{id}/{id2}")
	public String update(@PathVariable long id, @PathVariable long id2) {
		Customer customer = getCustomer(id);
		restTemplate1.put("http://localhost:8082/customer/update/" + id + "/" + id2, customer);
		return "Customer " + customer.getName() + " Reservation id set to: " + customer.getReservationId();
	}
	
	@GetMapping("/findbyreservation/{id}")
	public Customer findCustomerByReservationId(@PathVariable long id) {
		List<Customer> customerList = findAll();
		for(Customer customer : customerList) {
			if (customer.getReservationId() == id) {
				return customer;
			}
		}
		return null;
	}
	
	
	
	
	
}
