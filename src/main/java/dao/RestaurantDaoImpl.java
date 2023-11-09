package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import models.Restaurant;
@Repository
public class RestaurantDaoImpl implements RestaurantDao  {
	@Autowired
	RestaurantJpaSpring restaurant;
	@Override
	
	public List<Restaurant> getRestaurants() {
		return restaurant.findAll();
	}
	@Override
	public Restaurant retrieveRestaurant(int id) {
		return restaurant.findById(id).orElse(null);
	}

}
