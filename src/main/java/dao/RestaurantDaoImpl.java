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
	@Override
	public boolean addRestaurant(Restaurant res) {
		if(restaurant.findById(res.getId()) == null) {
			restaurant.save(res);
			return true;
		}
		return false;
	}
	@Override
	public void updateRestaurant(Restaurant res) {
		if(restaurant.findById(res.getId())!=null) {
			restaurant.save(res);
		}
		
	}
	@Override
	public boolean deleteRestaurant(int idRes) {
		if(restaurant.findById(idRes) != null) {
			restaurant.deleteById(idRes);
			return true;
		}
		return false;
	}

}
