package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Reservation;
@Repository
public class ReservationDaoImpl implements ReservationDao{
	@Autowired
	ReservationJpaSpring reservation;

	@Override
	public List<Reservation> getReservationByResId(Integer idRes) {
		// TODO Auto-generated method stub
		return reservation.findByResId(idRes);
	}

}
