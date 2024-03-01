package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import models.Dishe;
import models.Restaurant;

public interface DishJpaSpring extends JpaRepository<Dishe, Long> {

    @Query("SELECT d FROM Dishe d WHERE d.idRes = :idRes")
    List<Dishe> findByResId(@Param("idRes") Integer idRes);
}
