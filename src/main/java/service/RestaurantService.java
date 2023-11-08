package service;

import java.util.List;

import models.Restaurant;

public interface RestaurantService {
	boolean addUser(Restaurant restaurant);
	List <Restaurant> retrieveUser();
	boolean updateUser(Restaurant restaurant);
	boolean deleteUser(int restaurant);
	Restaurant retrieveUser(int restaurant);
}
