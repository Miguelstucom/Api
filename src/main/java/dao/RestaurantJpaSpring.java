package dao;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Restaurant;

public interface RestaurantJpaSpring extends JpaRepository<Restaurant, Integer> {

}
