package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Message;
import models.Restaurant;
import service.MessagesService;
import service.RestaurantService;


@RestController
@RequestMapping("/api")
public class RestaurantController {
	
	@Autowired
	RestaurantService wsrestaurant;
	@Autowired
	MessagesService message;
	
	@GetMapping(value="restaurant",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurant>retrieveUsers(){
		return wsrestaurant.retrieveRestaurant();
	}
	
	@GetMapping(value="restaurant/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Restaurant retrieveRestaurant(@PathVariable("id") int id) {
		return wsrestaurant.retrieveRestaurant(id);
	}
	
	@GetMapping(value="message/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Message retrieveMessages(@PathVariable("name") String name) {
		return message.retrieveMessages(name);
	}
	
	@GetMapping(value="message",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Message>getMessages(){
		return message.getMessages();
	}
	

}
