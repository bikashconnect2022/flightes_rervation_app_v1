package com.flightapp.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightapp.dto.ReservationRequest;
import com.flightapp.entities.Flight;
import com.flightapp.entities.Passenger;
import com.flightapp.entities.Reservation;
import com.flightapp.repositories.FlightRepository;
import com.flightapp.repositories.PassengerRepository;
import com.flightapp.repositories.ReservationRepository;
import com.flightapp.utilities.EmailUtil;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	private PassengerRepository passengerRepo;
	
	@Autowired
	private ReservationRepository reservationRepo;

	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		Optional<Flight> findById = flightRepo.findById(request.getId());
		Flight flight = findById.get();
		
		Passenger passenger  = new Passenger();
		passenger.setFirstName(request.getFirstName());
		passenger.setLastName(request.getLastName());
		passenger.setMiddleName(request.getMiddleName());
		passenger.setEmail(request.getEmail());
		passenger.setPhone(request.getPhone());
		
		passengerRepo.save(passenger);
		
		Reservation reservation  = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(passenger);
		reservation.setCheckedIn(false);
		reservation.setNumberOfBags(0);
		
		reservationRepo.save(reservation);
		
		return reservation;
	}

}
