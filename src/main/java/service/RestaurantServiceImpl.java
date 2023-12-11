package service;

import java.util.ArrayList;
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
	public List<Restaurant> restaurantFiltered(String name){
		List<Restaurant> restaurants = dao.getRestaurants();
		List<Restaurant> restaurantsFilteret = new ArrayList<Restaurant>();
		for(int x = 0; x < restaurants.size(); x++) {
			if(restaurants.get(x).getName().toLowerCase().contains(name.toLowerCase())){
				restaurantsFilteret.add(restaurants.get(x));
			}
		}
		return restaurantsFilteret;
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
