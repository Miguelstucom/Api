package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value="reservationbyuser/{idUser}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Reservation>retrieveReservationbyId(@PathVariable("idUser") Integer idUser){
		return reservationservice.getReservationByUserId(idUser);
	}
	
    @PutMapping(value = "updateUserInReservation/{reservationId}")
    public ResponseEntity<String> updateUserInReservation(@PathVariable("reservationId") Integer reservationId, @RequestParam("newUserId") Integer newUserId) {
        boolean updated = reservationservice.updateUserInReservation(reservationId, newUserId);
        if (updated) {
            return ResponseEntity.ok("User ID updated successfully in the reservation.");
        } else {
            return ResponseEntity.badRequest().body("Failed to update User ID in the reservation.");
        }
    }
}
