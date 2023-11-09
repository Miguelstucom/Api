package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.RestaurantDao;
import models.Restaurant;
@Service
public class RestaurantServiceImpl  implements RestaurantService {

	@Autowired
	RestaurantDao dao;
	@Override
	public boolean addUser(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Restaurant> retrieveUser() {
		// TODO Auto-generated method stub
		return dao.getRestaurants();
	}

	@Override
	public boolean updateUser(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(int restaurant) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Restaurant retrieveRestaurant(int id) {
		// TODO Auto-generated method stub
		return dao.retrieveRestaurant(id);
	}

}
