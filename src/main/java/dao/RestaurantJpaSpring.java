package dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import models.Restaurant;


@Repository
public interface RestaurantJpaSpring extends JpaRepository<Restaurant, Integer> {
	public Page<Restaurant> findAll(Pageable pageRest);
}
