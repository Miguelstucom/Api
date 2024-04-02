package service;

import java.util.List;

import models.Dishe;
import models.Reservation;

public interface ReservationService {
	List<Reservation> getReservationByResId(Integer idRes);

}
