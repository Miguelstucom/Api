package service;

import java.util.List;

import models.Restaurant;

public interface RestaurantService {
	List <Restaurant> retrieveRestaurant();
	Restaurant retrieveRestaurant(int restaurant);
}
