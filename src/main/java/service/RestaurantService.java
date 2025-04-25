package service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import models.Restaurant;

public interface RestaurantService {
	boolean addRestaurant(Restaurant res);
	void updateRestaurant(Restaurant res);
	boolean deleteRestaurant(int idRes);
	Page <Restaurant> retrieveRestaurant(Pageable pageRest);
	List <Restaurant> restaurantFiltered(String name);
	Restaurant retrieveRestaurant(int restaurant);
}
