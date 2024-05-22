package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DishDao;
import dao.ReservationDao;
import models.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationDao dao;

	@Override
	public List<Reservation> getReservationByResId(Integer idRes) {
		return dao.getReservationByResId(idRes);
	}

	@Override
	public List<Reservation> getReservationByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return dao.getReservationByUserId(idUser);
	}

}
