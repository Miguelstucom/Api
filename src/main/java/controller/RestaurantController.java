package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Message;
import models.Restaurant;
import models.User;
import service.MessagesService;
import service.RestaurantService;
import service.UserService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class RestaurantController {
	
	@Autowired
	RestaurantService wsrestaurant;
	
	@GetMapping(value="restaurant",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurant>retrieveUsers(){
		return wsrestaurant.retrieveRestaurant();
		
	}

	@GetMapping(value="restaurant/name/{name}",produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurant>searchRestaurant(@PathVariable("name") String name){
		return wsrestaurant.restaurantFiltered(name);
	}
	
	@GetMapping(value="restaurant/{id}",produces=MediaType.APPLICATION_JSON_VALUE)
	public Restaurant retrieveRestaurant(@PathVariable("id") int id) {
		return wsrestaurant.retrieveRestaurant(id);
	}
	
	@PostMapping("/restaurant")
	
	public ResponseEntity<?> create(@RequestBody Restaurant rest) {
		Restaurant newRes = rest;
		System.out.println(rest.toString());
		java.util.Map<String, Object> response = new HashMap<>();
		try {
			wsrestaurant.addRestaurant(rest);
			response.put("message", "El restaurante ha sido creado con éxito");
			response.put("Restaurante", newRes);
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.CREATED);
		}
		catch(DataAccessException e){
			response.put("message","Error");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value = "/restaurant", consumes=MediaType.APPLICATION_JSON_VALUE)
	
	public ResponseEntity<?> update(@RequestBody Restaurant rest) {

		Restaurant newRest = new Restaurant();
		java.util.Map<String, Object> response = new HashMap<>();		
		
		if(wsrestaurant.retrieveRestaurant(rest.getId())==null) {
			response.put("mensaje", "Error: no se pudo editar, el Restaurant ID: " + rest.getId() + "no existe en la base de datos");
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		wsrestaurant.updateRestaurant(rest);
		
		try {
			newRest.setName(rest.getName());
			newRest.setAddress(rest.getAddress());
			newRest.setDescription(rest.getDescription());
			newRest.setEmail(rest.getEmail());
			newRest.setMedianprice(rest.getMedianprice());
			newRest.setPhone(rest.getPhone());
			newRest.setPhoto(rest.getPhoto());
			newRest.setWebsite(rest.getWebsite());
			newRest.setType(rest.getType());

			wsrestaurant.updateRestaurant(rest);
			response.put("message", "El restaurante ha sido actualizado con éxito");
			response.put("Usuario", rest);
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.CREATED);
			
		} catch(DataAccessException e) {
			response.put("message","Error al realizar al actualizar los datos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	
	@DeleteMapping("/restaurant/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {
		
		java.util.Map<String, Object> response = new HashMap<>();
		Restaurant actualRes = wsrestaurant.retrieveRestaurant(id);
		
		if(actualRes==null) {
			response.put("message", "Error al eliminar, el usuario: " + id +" no existe en la base de datos");
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		try {
			wsrestaurant.deleteRestaurant(id);
	
			response.put("mensaje","El usuario " + actualRes.getName() + " ha sido eliminado con exito");
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.CREATED);
		}
		catch(DataAccessException e) {
			response.put("message","Error al eliminar los datos en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<java.util.Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}

}

