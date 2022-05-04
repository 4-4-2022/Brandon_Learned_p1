package com.revature.repository;


import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Customer;
/*
 * When using Spring Data JPA, you are not responsible for implementing methods that connect to the DB
 * and return records. This eleminates the need to write boilerplate code. Spring Data JPA provides pre-
 * implemented methods that perform most of the basis CRUD needed in an enterprise application. 
 * 
 * To take advantage of thos implementations, you must extend the JpaRepository interface. 
 */
@Repository("customerRepository") //This is now a bean!
public interface CustomerRepository extends JpaRepository<Customer, Integer>{ 
	//must pass a type and ID, type is model being passed in,  (Customer here), 
	//ID is the unique identifier for the table
	
	public List<Customer> findAll();
	public Customer findCustomerById(long id);
	public <S extends Customer> S save(Customer customer); //defined a generic S extending Customer class
	public void delete(Customer customer);

}