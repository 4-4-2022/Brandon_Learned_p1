package com.revature.repository;

import java.util.List; 

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.model.Reservation;

@Repository("reservation_repository")
public interface ReservationRepository extends JpaRepository<Reservation, Integer>{
	
	public List<Reservation> findAll();
	public Reservation findReservationById(long id);
	public <S extends Reservation> S save(Reservation reservation); //defined a generic S extending Customer class
	public void delete(Reservation reservation);

}
