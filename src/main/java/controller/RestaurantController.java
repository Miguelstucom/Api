package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Restaurant;
import service.RestaurantService;


@RestController
public class RestaurantController {
	
	@Autowired
	RestaurantService wsrestaurant;
	
	@GetMapping(value="restaurant",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurant>retrieveUsers(){
		return wsrestaurant.retrieveUser();
	}

}
