package dao;

import java.util.List;

import org.springframework.stereotype.Service;

import models.Restaurant;

@Service
public interface RestaurantDao {
	boolean addRestaurant(Restaurant res);
	void updateRestaurant(Restaurant res);
	boolean deleteRestaurant(int idRes);
	List<Restaurant>getRestaurants();
	Restaurant retrieveRestaurant(int id);
}
