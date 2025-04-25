package dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import models.Restaurant;

@Service
public interface RestaurantDao {
	boolean addRestaurant(Restaurant res);
	void updateRestaurant(Restaurant res);
	boolean deleteRestaurant(int idRes);
	Page<Restaurant>getRestaurants(Pageable pageRest);
	Restaurant retrieveRestaurant(int id);
}
