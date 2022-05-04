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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.model.Customer;
import com.revature.service.CustomerService;

@RestController("customerController")
@RequestMapping("/customer")
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> findAll(){
		ResponseEntity<List<Customer>> httpResponse = new ResponseEntity<>(this.customerService.findAll(), HttpStatus.OK);
		return httpResponse;
	}
	
	@GetMapping("/{id}")
	public Customer findCustomerById(@PathVariable long id) {
		return this.customerService.findCustomerById(id);
	}
	
	
	@PostMapping(value="/new", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String save(@RequestBody Customer customer) {
		this.customerService.save(customer);
		return "Customer: " + customer.getName() + " successfully created.";
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		Customer customer = this.customerService.findCustomerById(id);
		this.customerService.delete(customer);
		return "Customer " + customer.getName() + " successfully deleted.";
	}

	@PutMapping("/update/{id}/{id2}")
	public String update(@PathVariable long id, @PathVariable long id2) {
		Customer customer = this.customerService.findCustomerById(id);
		customer.setReservationId(id2);
		this.customerService.save(customer);
		return "Customer " + customer.getName() + " Reservation id set to: " + customer.getReservationId();
	}
	
	@GetMapping("/findbyreservation/{id}")
	public Customer findCustomerByReservationId(@PathVariable long id) {
		List<Customer> customerList = this.customerService.findAll();
		for(Customer customer : customerList) {
			if (customer.getReservationId() == id) {
				return customer;
			}
		}
		return null;
	}
	
	
//	@GetMapping("/update/{id}")
//	public String updateReservationId(@PathVariable long id) {
//		this.customerService.updateReservationId(1, 5);
//		return "This happened";
//		
//	}

}
