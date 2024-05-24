package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import models.Dishe;
import models.Reservation;

public interface ReservationJpaSpring extends JpaRepository<Reservation, Integer> {
	
    @Query("SELECT d FROM Reservation d WHERE d.idRes = :idRes and d.idUser = 0")
    List<Reservation> findByResId(@Param("idRes") Integer idRes);
    
    @Query("SELECT d FROM Reservation d WHERE d.idUser = :idUser")
    List<Reservation> findByUserId(@Param("idUser") Integer idUser);
    
    

}
