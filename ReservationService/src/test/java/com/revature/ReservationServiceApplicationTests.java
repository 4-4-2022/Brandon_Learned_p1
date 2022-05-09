package com.revature;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.model.Reservation;
import com.revature.repository.ReservationRepository;
import com.revature.service.ReservationService;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class ReservationServiceApplicationTests {

	@InjectMocks
	private ReservationService reservationService;
	
	@Mock
	private ReservationRepository reservationRepository;
	
	private List<Reservation> mockReservations;
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		this.reservationService = new ReservationService();
		this.mockReservations = new ArrayList<Reservation>();
		this.mockReservations.add(new Reservation(1, 5, 8, 5, 875f));
		this.mockReservations.add(new Reservation(2, 6, 8, 5, 600f));
		this.mockReservations.add(new Reservation(3, 9, 6, 5, 790f));
		this.mockReservations.add(new Reservation(4, 7, 8, 5, 200f));

		
	}
	
	@Test
	public void testFindAllTotal() {
		
		Mockito.when(this.reservationRepository.findAll()).thenReturn(this.mockReservations);
		
		List<Reservation> reservations = (this.reservationService.findAll());
		Assertions.assertEquals(875.0, reservations.get(0).getTotal());
		for(Reservation reservation : reservations) {
			System.out.println(reservation);
		}
	}
	
	@Test
	public void testFindAllId() {
		
		Mockito.when(this.reservationRepository.findAll()).thenReturn(this.mockReservations);
		
		List<Reservation> reservations = (this.reservationService.findAll());
		Assertions.assertEquals(2, reservations.get(1).getId());
		}
	
	@Test
	public void testFindAllCustId() {
		
		Mockito.when(this.reservationRepository.findAll()).thenReturn(this.mockReservations);
		
		List<Reservation> reservations = (this.reservationService.findAll());
		Assertions.assertEquals(6, reservations.get(1).getCustomerId());
		}
	
	@Test
	public void testFindAllDaysBooked() {
		
		Mockito.when(this.reservationRepository.findAll()).thenReturn(this.mockReservations);
		
		List<Reservation> reservations = (this.reservationService.findAll());
		Assertions.assertEquals(5, reservations.get(1).getDaysBooked());
		}
	
	@Test
	public void testFindAllRoomId() {
		
		Mockito.when(this.reservationRepository.findAll()).thenReturn(this.mockReservations);
		
		List<Reservation> reservations = (this.reservationService.findAll());
		Assertions.assertEquals(6, reservations.get(2).getRoomId());
		}
	
	@Test
	public void testFindReservationById() {
		int id = 0;
		Mockito.when(this.reservationRepository.findReservationById(id)).thenReturn(this.mockReservations.get(id));
		Reservation reservation = this.reservationRepository.findReservationById(id);
		Assertions.assertEquals(1, reservation.getId());
	}
	
	@Test
	public void testFindReservationById2() {
		int id = 1;
		Mockito.when(this.reservationRepository.findReservationById(id)).thenReturn(this.mockReservations.get(id));
		Reservation reservation = this.reservationRepository.findReservationById(id);
		Assertions.assertEquals(2, reservation.getId());
	}
	
	@Test
	public void testFindReservationById3() {
		int id = 2;
		Mockito.when(this.reservationRepository.findReservationById(id)).thenReturn(this.mockReservations.get(id));
		Reservation reservation = this.reservationRepository.findReservationById(id);
		Assertions.assertEquals(3, reservation.getId());
	}
	
	@Test
	public void testFindReservationById4() {
		int id = 3;
		Mockito.when(this.reservationRepository.findReservationById(id)).thenReturn(this.mockReservations.get(id));
		Reservation reservation = this.reservationRepository.findReservationById(id);
		Assertions.assertEquals(4, reservation.getId());
	}
	
	@Test
	public void testFindReservationByIdGetTotal1() {
		int id = 0;
		Mockito.when(this.reservationRepository.findReservationById(id)).thenReturn(this.mockReservations.get(id));
		Reservation reservation = this.reservationRepository.findReservationById(id);
		Assertions.assertEquals(875.0, reservation.getTotal());
	}
	
	@Test
	public void testFindReservationByIdGetTotal2() {
		int id = 1;
		Mockito.when(this.reservationRepository.findReservationById(id)).thenReturn(this.mockReservations.get(id));
		Reservation reservation = this.reservationRepository.findReservationById(id);
		Assertions.assertEquals(600.0, reservation.getTotal());
	}

	@Test
	public void testFindReservationByIdGetTotal3() {
		int id = 2;
		Mockito.when(this.reservationRepository.findReservationById(id)).thenReturn(this.mockReservations.get(id));
		Reservation reservation = this.reservationRepository.findReservationById(id);
		Assertions.assertEquals(790.0, reservation.getTotal());
	}

	@Test
	public void testFindReservationByIdGetTotal4() {
		int id = 3;
		Mockito.when(this.reservationRepository.findReservationById(id)).thenReturn(this.mockReservations.get(id));
		Reservation reservation = this.reservationRepository.findReservationById(id);
		Assertions.assertEquals(200.0, reservation.getTotal());
	}



}
