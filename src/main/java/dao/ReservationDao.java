package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import models.Dishe;
import models.Reservation;
@Service
public interface ReservationDao {
	List<Reservation> getReservationByResId(Integer idRes);
}
