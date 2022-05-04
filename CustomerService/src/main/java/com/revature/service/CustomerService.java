package com.revature.service;

import java.util.List;  

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.model.Customer;
import com.revature.repository.CustomerRepository;

@Service("customerService")
public class CustomerService {

	private CustomerRepository customerRepository;
	
	@Autowired
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public List<Customer> findAll() {
		return this.customerRepository.findAll();
	}
	
	public Customer findCustomerById(long id) {
		return this.customerRepository.findCustomerById(id);
	}

	public void save(Customer customer) {
		this.customerRepository.save(customer);		
	}

	public void delete(Customer customer) {
		this.customerRepository.delete(customer);
	}


//	public void updateReservationId(int i, int j) {
//		this.customerRepository.setCustomerInfoById(i, i);		
//	}


	
	
//	public Customer getCustomer(long id) {
//		List<Customer> customers = dummyData.customer;
//		for(Customer customer : customers) {
//			if(customer.getId()==id) {
//				return customer;
//			}
//		}
//		return null;
//	}
//	
}
