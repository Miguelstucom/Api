package dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import models.Dishe;
import models.Review;
import models.User;

public interface ReviewsJpaSpring  extends JpaRepository<Review, Long>{
	@Query("SELECT r FROM Review r WHERE r.idRes = :idRes")
	List<Review> findByResId(@Param("idRes") Integer idRes);
}
