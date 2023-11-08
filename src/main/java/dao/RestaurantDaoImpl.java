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
		// TODO Auto-generated method stub
		return restaurant.findAll();
	}

}
