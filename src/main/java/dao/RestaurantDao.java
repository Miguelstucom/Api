package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import models.Restaurant;

@Service
public interface RestaurantDao {
	List<Restaurant>getRestaurants();
	Restaurant retrieveRestaurant(int idContacto);
}
