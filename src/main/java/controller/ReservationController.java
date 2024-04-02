package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Dishe;
import models.Reservation;
import service.ReservationService;

@RestController
@RequestMapping("/api")
public class ReservationController {
	
	@Autowired
	ReservationService reservationservice;
	
	@GetMapping(value="reservation/{idRes}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Reservation>retrieveDishesbyId(@PathVariable("idRes") Integer idRes){
		return reservationservice.getReservationByResId(idRes);
	}

}
