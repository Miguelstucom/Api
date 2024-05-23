package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.DishDao;
import dao.ReservationDao;
import dao.ReservationJpaSpring;
import models.Reservation;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	ReservationDao dao;
	@Autowired
	ReservationJpaSpring reservationRepository;

	@Override
	public List<Reservation> getReservationByResId(Integer idRes) {
		return dao.getReservationByResId(idRes);
	}

	@Override
	public List<Reservation> getReservationByUserId(Integer idUser) {
		// TODO Auto-generated method stub
		return dao.getReservationByUserId(idUser);
	}
	
    @Override
    public boolean updateUserInReservation(Integer reservationId, Integer newUserId) {
        Optional<Reservation> reservationOpt = reservationRepository.findById(reservationId);
        if (reservationOpt.isPresent()) {
            Reservation reservation = reservationOpt.get();
            reservation.setIdUser(newUserId);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

}
