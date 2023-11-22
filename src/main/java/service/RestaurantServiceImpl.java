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
	public List<Restaurant> retrieveRestaurant() {
		// TODO Auto-generated method stub
		return dao.getRestaurants();
	}

	@Override
	public Restaurant retrieveRestaurant(int id) {
		// TODO Auto-generated method stub
		return dao.retrieveRestaurant(id);
	}

	@Override
	public boolean addRestaurant(Restaurant res) {
		return dao.addRestaurant(res);
	}

	@Override
	public void updateRestaurant(Restaurant res) {
		dao.updateRestaurant(res);
		
	}

	@Override
	public boolean deleteRestaurant(int idRes) {
		return dao.deleteRestaurant(idRes);
	}

}
