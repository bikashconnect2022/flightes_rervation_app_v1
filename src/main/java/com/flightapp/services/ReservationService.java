package com.flightapp.services;

import com.flightapp.dto.ReservationRequest;
import com.flightapp.entities.Reservation;

public interface ReservationService {
	Reservation bookFlight(ReservationRequest request);
}
